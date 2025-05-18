package Project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;


//made this class to hold each individual team of Pokemon.
public class PokemonTeam {

    private ArrayList<Pokemon> playerPokemon = new ArrayList<>();
    private String teamName;


    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public ArrayList<Pokemon> getPlayerPokemon(){
        return playerPokemon;
    }

    public String getTeamName(){
        return teamName;
    }

    public void addPokemon(Pokemon pokemon) {
        playerPokemon.add(pokemon);
    }

    public void removePokemon(Pokemon pokemon){
        playerPokemon.remove(pokemon);
    }


    public void userAddPokemon(Scanner userScanner, HashMap<String, Pokemon> pokedex){
        clearScreen();
        if (playerPokemon.size() >= 6){
            return;
        }

        String userPokemon = "";

        //These all determine the Pokemon's stats.
        int userLevel = 0;
        int[] userStats = new int[6];
        int[] userIVs = {-1, -1, -1, -1, -1, -1};
        int[] userEVs = {-1, -1, -1, -1, -1, -1};

        //All the while loops are to gain relevant information needed to calculate the stats of the Pokemon.
        while(true){
            System.out.println("Enter the name of the Pokemon you want to add (For forms, enter  'Pokemon's name' + ':' + 'form name'. Ex: 'Charizard:Mega Charizard Y' Please remember this is case-sensitive, start each word with a capital letter):");
            userPokemon = userScanner.nextLine();
            if(pokedex.containsKey(userPokemon)){
                break;
            }
            else{
                System.out.println("Pokemon not found. Please try again.");
            }
        }
        //Get level
        while(true){
            System.out.println("Enter the level of the Pokemon:");
            if(userScanner.hasNextInt()){
                userLevel = userScanner.nextInt();
                userScanner.nextLine();
                if(userLevel > 0 && userLevel < 101){
                    break;
                }
                else{
                    System.out.println("Invalid level. Please try again.");
                }
            }
            else{
                System.out.println("Please enter a number.");
                userScanner.nextLine();
            }
        }
// Get IVs
for (int i = 0; i < userIVs.length; i++) {
    while (true) {
        System.out.println("Enter IV for stat " + (i + 1) + " (0-31):");
        if (userScanner.hasNextInt()) {
            userIVs[i] = userScanner.nextInt();
            userScanner.nextLine();
            if (userIVs[i] >= 0 && userIVs[i] <= 31) {
                break; // valid input, move to next stat
            } else {
                System.out.println("Invalid IV. Please enter a number between 0 and 31.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            userScanner.nextLine(); // consume invalid input
        }
    }
}

// Get EVs
for (int i = 0; i < userEVs.length; i++) {
    while (true) {
        System.out.println("Enter EV for stat " + (i + 1) + " (0-255):");
        if (userScanner.hasNextInt()) {
            userEVs[i] = userScanner.nextInt();
            userScanner.nextLine();
            if (userEVs[i] >= 0 && userEVs[i] <= 255) {
                break; // valid input, move to next stat
            } else {
                System.out.println("Invalid EV. Please enter a number between 0 and 255.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            userScanner.nextLine(); // consume invalid input
        }
    }
}
        
        //Got formulas for stats from bulbapedia.bulbagarden.net/wiki/Stat
        //Health
        userStats[0] = 2 * pokedex.get(userPokemon).getHealth();
        userStats[0] += userIVs[0];
        userStats[0] += userEVs[0]/4;
        userStats[0] = userStats[0] * userLevel;
        userStats[0] = userStats[0]/100;
        userStats[0] += userLevel + 10;
        //This specific Pokemon has 1 HP always, so I had to add a special case for it.
        if(pokedex.get(userPokemon).getName().equals("Shedninja")){
            userStats[0] = 1;
        }

        //All other stats the formula is the same, it may of been easier to make a general function to get stats as opposed to seperate ones.
        for(int i = 1; i < userStats.length; i++){
            if(i == 1){    
                userStats[i] = 2 * pokedex.get(userPokemon).getAttack();
            }
            else if (i == 2){
                userStats[i] = 2 * pokedex.get(userPokemon).getDefense();
            }
            else if (i == 3){
                userStats[i] = 2 * pokedex.get(userPokemon).getSpAttack();
            }
            else if (i == 4){
                userStats[i] = 2 * pokedex.get(userPokemon).getSpDefense();
            }
            else if (i == 5){
                userStats[i] = 2 * pokedex.get(userPokemon).getSpeed();
            }
            else{
                System.out.println("Error in stats.");
            }
            userStats[i] += userIVs[i];
            userStats[i] += userEVs[i]/4;
            userStats[i] = userStats[i] * userLevel;
            userStats[i] = userStats[i]/100;
            userStats[i] += 5;
            
        }
        //Add all data to a Pokemon object, add that to the team.
        Pokemon newPokemon = new Pokemon(pokedex.get(userPokemon).getName(), pokedex.get(userPokemon).getForm(), pokedex.get(userPokemon).getType1(), pokedex.get(userPokemon).getType2(), userStats[0], userStats[1], userStats[2], userStats[3], userStats[4], userStats[5]);
        playerPokemon.add(newPokemon);
        clearScreen();
    }


    //This function is used to get the user's choices and edit the team depending on what they choose
    public void editTeam(Scanner userScanner, HashMap<String, Pokemon> pokedex){
        char userChoice = 'q';
        int userChoiceInt = 0;
        while(true){
            clearScreen();
            printTeam();
            System.out.println("What would you like to do?\n1. Add a Pokemon\n2. Remove a Pokemon\n3. Clear team \n4. Exit");
            userChoice = userScanner.next().charAt(0);
            userScanner.nextLine();
            userChoiceInt = userChoice - '0';
            if (userChoiceInt > 4 || userChoiceInt < 1){
                System.out.println("invalid choice. Please try again.");
            }

            //Since there is another function to add a Pokemon, I just call that function here.
            else if(userChoiceInt == 1){
            userAddPokemon(userScanner, pokedex);
         }
         //Removes Pokemon using a while loop to get the user's choice.
         else if(userChoiceInt == 2){
            if(playerPokemon.size() == 0){
                System.out.println("You have no Pokemon to remove.");
                continue;
            }
            while(true){
                clearScreen();
                System.out.println("Choose a Pokemon to remove:");
                printTeam();
                userChoice = userScanner.next().charAt(0);
                userScanner.nextLine();
                userChoiceInt = userChoice - '0';
                if(userChoiceInt > playerPokemon.size() || userChoiceInt < 1){
                    System.out.println("invalid choice. Please try again.");
                }
                else{
                    break;
                }
            }
            playerPokemon.remove(userChoiceInt - 1);
         }

         //This clears the team, if the team is cleared when the program ends the team will be deleted.
         else if(userChoiceInt ==3){
            clearScreen();
            System.out.println("Are you sure you want to clear your team? (type 'y' for yes)");
            char confirm = userScanner.next().charAt(0);
            if(confirm == 'y'){
                clearScreen();
                playerPokemon.clear();
                System.out.println("Your team has been cleared.");
            }
            else{
                clearScreen();
                System.out.println("Your team has not been cleared.");
            }
         }
         else if(userChoiceInt == 4){
            break;
         }
        }
    }


    //Prints the player's team in a formatted table.
    //Helped with formatting the printf by GitHub Copilot.
    public void printTeam()
    {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-35s %-20s %-8s %-8s %-8s %-8s %-8s %-8s %-10s%n", 
            "Num",
            "Name",
            "Type",
            "HP",
            "ATK",
            "DEF",
            "SP. ATK",
            "SP. DEF",
            "SPD",
            "Total");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");


        //Loop to print all the Pokemon in the player's team.
        for (int i = 0; i < playerPokemon.size(); i++) {
            Pokemon p = playerPokemon.get(i);
            System.out.printf("%-5d %-35s %-20s %-8d %-8d %-8d %-8d %-8d %-8d %-10d%n", 
                (i + 1), 
                p.getName(), 
                p.getType(), 
                p.getHealth(), 
                p.getAttack(), 
                p.getDefense(), 
                p.getSpAttack(), 
                p.getSpDefense(),
                p.getSpeed(),
                p.getStatTotal());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

}
