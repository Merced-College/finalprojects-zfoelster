package Project;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;


public class Main {
    public static void main(String[] args) {
        //Used a HashMap because it is faster than an ArrayList for searching.
        HashMap<String, Pokemon> pokedex = new HashMap<>(2048);

        ArrayList<PokemonTeam> playerTeams = new ArrayList<>();
        readPokemonFromPokedex(pokedex);
        readPokemonTeams(playerTeams);
        
        //System.out.println(pokedex.size());
        //for(String i: pokedex.keySet()){
        //  System.out.println(i);
        //}
        
        //playerTeams.get(0).printTeam();
        

        //playerTeam.printTeam();
        //playerTeam.addPokemon(pokedex.get("Charizard"));
        //playerTeam.addPokemon(pokedex.get("Urshifu:Single Strike Style"));
        //playerTeam.printTeam();
        //playerTeam.removePokemon(pokedex.get("Charizard"));
        //playerTeam.printTeam();
        Scanner userScanner = new Scanner(System.in);
        char userChoice = '0';
        int userChoiceInt = 0;
        clearScreen();
        System.out.println("Welcome to Pokemon Team Builder!\n");

        //True is used to keep the program running until the user chooses to exit.
        while(true){
            if (userChoiceInt > 3 || userChoiceInt < 1){
                System.out.println("What would you like to do?\n1. Create a new team\n2. Edit an existing team\n3. Exit\n");
                userChoice = userScanner.next().charAt(0);
                userScanner.nextLine();
                userChoiceInt = Character.getNumericValue(userChoice);
                //Get team name and then create a new team with that name, the user is then prompted to edit the new team.
                if (userChoiceInt == 1){
                    clearScreen();
                    PokemonTeam newTeam = new PokemonTeam();
                    char confirm = 'q';
                    while(true){
                        System.out.println("Enter a 1 word name for your team:");
                        String teamName = userScanner.next();
                        System.out.println("Do you want your name to be'" + teamName + "'? (type y for yes)");
                        confirm = userScanner.next().charAt(0);
                        if (confirm == 'y'){
                            clearScreen();
                            newTeam.setTeamName(teamName);
                            newTeam.editTeam(userScanner, pokedex);
                            playerTeams.add(newTeam);
                            clearScreen();
                            System.out.println("Your team has been created!");
                            userChoiceInt = 0;
                            break;
                        }
                        else{
                        }
                    }
                }

                //List of teams is shown to the user, and they are prompted to choose a team to edit.
                else if (userChoiceInt == 2){
                    clearScreen();
                    editTeamChoice(playerTeams, pokedex);
                    userChoiceInt = 0;
                }

                //Exit the program.
                else if (userChoiceInt == 3){
                    clearScreen();
                    System.out.println("Bye!");
                    break;
                }
                else{
                    System.out.println("Invalid choice. Please try again.\n");
                }

            }
        }
        userScanner.close();
        

        //Write the teams to the text file pokemonTeams.txt
        //The file is overwritten each time the program is run.
        //Copilot helped me with this code as I was new to writing to files.
        writePokemonTeams(playerTeams);
        

    }
    //end main




    //Copilot gave me this function to clear the screen and upon looking it up it seems that it depends on what you're using as to whether it works. It works here though, and it looks nicer. 
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    //This function is used to edit a team, it shows the user a list of teams and prompts them to choose one. If there are no teams it lets the user know. 
    public static void editTeamChoice(ArrayList<PokemonTeam> teams, HashMap <String, Pokemon> pokedex){
        clearScreen();
        Scanner userScanner = new Scanner(System.in);
        //So the user doesn't get stuck in an infinite loop if they have no teams.
        if(teams.isEmpty()){
            System.out.println("You have no teams to edit.");
        }
        else{
            //Prints the teams in a numbered list with their name and lets the user choose one to edit.
            System.out.println("Choose a team to edit:");
            for (int i = 0; i < teams.size(); i++){
                System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
                if (i == teams.size() - 1){
                    System.out.println((teams.size() + 1) + ". Go back");
                }
            }
            char userChoice = 'q';
            int userChoiceInt = 0;
            while(true){
                userChoice = userScanner.next().charAt(0);
                userScanner.nextLine();
                userChoiceInt = userChoice - '0';
                if(userChoiceInt > teams.size() + 1 || userChoiceInt < 1){
                    System.out.println("Invalid choice. Please try again.");
                }
                else{
                    clearScreen();
                    break;
                }
            }
            if(userChoiceInt != teams.size() + 1){
                //This function is used to edit an indicidual team.
                teams.get(userChoiceInt - 1).editTeam(userScanner, pokedex);
            }
        }
    clearScreen();
    }




    public static void readPokemonFromPokedex(HashMap<String, Pokemon> pokedex){
        //Get the Pokemon data from the text file pokemon.txt
        Scanner fileScanner = null;
        try{
            fileScanner = new Scanner(new File("pokemon.txt"));
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        //I ignore fields[0] because the number is not important information to display.
        //Since two Pokemon can have the same name if they have a different form, I used the name and form as the key for non-base forms of Pokemon.
        //For example, "Charizard" and "Charizard:Mega Charizard X" are different keys.
        while(fileScanner.hasNext()){
            String[] fields = fileScanner.nextLine().split(",");
            Pokemon newPokemon = new Pokemon(fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Integer.parseInt(fields[8]), Integer.parseInt(fields[9]), Integer.parseInt(fields[10]));
            if(fields[2].isEmpty()){
                pokedex.put(fields[1], newPokemon);
            }
            else{
            pokedex.put(fields[1] + ":" +  fields[2], newPokemon);
            }
        }
        fileScanner.close();
    }




    public static void readPokemonTeams(ArrayList<PokemonTeam> playerTeams){
        Scanner fileScanner = null;
        try{
            fileScanner = new Scanner(new File("pokemonTeams.txt"));
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }




        while(fileScanner.hasNext()){
            String[] fields = fileScanner.nextLine().split(",");
            PokemonTeam newTeam = new PokemonTeam();
            newTeam.setTeamName(fields[0]);
            //Have a multiplier to reach the next pokemon in the pokemonTeams.txt file
            int multiplier = 0;
            //Start at 1 because the first field is the team name.
            int n = 1;
            //The last field is a "!" to indicate the end of the team.
            while (!fields[(n)].equals("!")){
                Pokemon newPokemon = new Pokemon(fields[n], fields[n + 1], fields[n + 2], fields[n + 3], Integer.parseInt(fields[n + 4]), Integer.parseInt(fields[n + 5]), Integer.parseInt(fields[n + 6]), Integer.parseInt(fields[n + 7]), Integer.parseInt(fields[n + 8]), Integer.parseInt(fields[n + 9]));
                //System.out.println("Test");
                newTeam.addPokemon(newPokemon);
                multiplier++;
                n = (multiplier * 10) + 1;
            }
        playerTeams.add(newTeam);
        }

        fileScanner.close();

    }




    public static void writePokemonTeams(ArrayList<PokemonTeam> playerTeams){
                try (FileWriter writer = new FileWriter("pokemonTeams.txt")) {
            //For every team in the player's team, write the team name and then...
            for (PokemonTeam team : playerTeams) {
                //If it is empty, we don't want to save the tream.
                if(!team.getPlayerPokemon().isEmpty()){
                    writer.write(team.getTeamName() + ",");
                    //For every Pokemon in the team, write the Pokemon's name, form, type1, type2, health, attack, defense, spAttack, spDefense, speed. Seperate these all with commas and at the end place an exclamation mark as that's how it will be read the next time the user runs the program. 
                    for (Pokemon pokemon : team.getPlayerPokemon()) {
                        writer.write(pokemon.getName() + "," + pokemon.getForm() + "," + pokemon.getType1() + "," + pokemon.getType2() + "," + pokemon.getHealth() + "," + pokemon.getAttack() + "," + pokemon.getDefense() + "," + pokemon.getSpAttack() + "," + pokemon.getSpDefense() + "," + pokemon.getSpeed() + ",");
                    }
                    writer.write("!\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
