package Project;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Used a HashMap because it is faster than an ArrayList for searching.
        HashMap<String, Pokemon> pokedex = new HashMap<>();

        ArrayList<PokemonTeam> playerTeams = new ArrayList<>();

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



        Scanner fileScanner2 = null;
        try{
            fileScanner2 = new Scanner(new File("pokemonTeams.txt"));
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }


        while(fileScanner2.hasNext()){
            String[] fields = fileScanner2.nextLine().split(",");
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

        fileScanner2.close();
        
        //playerTeams.get(0).printTeam();
        

        //playerTeam.printTeam();
        //playerTeam.addPokemon(pokedex.get("Charizard"));
        //playerTeam.addPokemon(pokedex.get("Urshifu:Single Strike Style"));
        //playerTeam.printTeam();
        //playerTeam.removePokemon(pokedex.get("Charizard"));
        //playerTeam.printTeam();
        Scanner userScanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("Welcome to Pokemon Team Builder!\\n");
        clearScreen();
        while(true){
            if (userChoice > 3 || userChoice < 1){
                System.out.println("What would you like to do?\n1. Create a new team\n2. Edit an existing team\n3. Exit\n");
                userChoice = userScanner.nextInt();
                if (userChoice == 1){
                    //Create a new team
                }
                else if (userChoice == 2){
                    editTeamChoice(playerTeams);
                }
                else if (userChoice == 3){
                    System.out.println("Bye!");
                    break;
                }
                else{
                    System.out.println("Invalid choice. Please try again.\n");
                }

            }
        }
        userScanner.close();
    

    }

    //Copilot gave me this function to clear the screen, looks nicer. 
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }


    public static void editTeamChoice(ArrayList<PokemonTeam> teams){
        clearScreen();
        Scanner userScanner = new Scanner(System.in);
        if(teams.isEmpty()){
            System.out.println("You have no teams to edit.");
        }
        else{
            System.out.println("Choose a team or type '0' to go back:");
            for (int i = 0; i < teams.size(); i++){
                System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
                if (i == teams.size() - 1){
                    System.out.println((teams.size() + 1) + ". Go back");
                }
            }
            int userChoice = -1;
            while(true){
                userChoice = userScanner.nextInt();
                if(userChoice > teams.size() + 1 || userChoice < 1){
                    System.out.println("Invalid choice. Please try again.");
                }
                else{
                    break;
                }
            }
            if(userChoice != teams.size() + 1){
                teams.get(userChoice - 1).editTeam(userScanner);
            }
        }
    clearScreen();
    }
}
