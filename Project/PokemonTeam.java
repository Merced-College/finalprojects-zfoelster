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
        String userPokemon = "";

        //These all determine the Pokemon's stats.
        int userLevel = 0;
        int[] userStats = new int[6];
        int[] userIVs = new int[6];
        int[] userEVs = new int[6];

        //All the while loops are to gain relevant information needed to calculate the stats of the Pokemon.
        while(true){
            System.out.println("Enter the name of the Pokemon you want to add:");
            userPokemon = userScanner.next();
            if(pokedex.containsKey(userPokemon)){
                break;
            }
            else{
                System.out.println("Pokemon not found. Please try again.");
            }
        }
        while(true){
            System.out.println("Enter the level of the Pokemon:");
            userLevel = userScanner.nextInt();
            if(userLevel > 0 && userLevel < 101){
                break;
            }
            else{
                System.out.println("Invalid level. Please try again.");
            }
        }
        while(true){
            System.out.println("Enter the IVs of the Pokemon (HP, ATK, DEF, SP. ATK, SP. DEF, SPD):");
            for(int i = 0; i < userIVs.length; i++){
                userIVs[i] = userScanner.nextInt();
                if(userIVs[i] < 0 || userIVs[i] > 31){
                    System.out.println("Invalid IV. Please try again.");
                    break;
                }
            }
            if(userIVs[5] <= 31 && userIVs[5] >= 0){
                break;
            }
        }
        while(true){
            System.out.println("Enter the EVs of the Pokemon (HP, ATK, DEF, SP. ATK, SP. DEF, SPD):");
            userEVs = new int[6];
            for(int i = 0; i < userEVs.length; i++){
                userEVs[i] = userScanner.nextInt();
                if(userEVs[i] < 0 || userEVs[i] > 255){
                    System.out.println("Invalid EV. Please try again.");
                    break;
                }
            }
            if(userEVs[5] <= 255 && userEVs[5] >= 0){
                break;
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
        Pokemon newPokemon = new Pokemon(pokedex.get(userPokemon).getName(), pokedex.get(userPokemon).getForm(), pokedex.get(userPokemon).getType1(), pokedex.get(userPokemon).getType2(), userStats[0], userStats[1], userStats[2], userStats[3], userStats[4], userStats[5]);
        playerPokemon.add(newPokemon);
    }

    public void editTeam(Scanner userScanner, HashMap<String, Pokemon> pokedex){
        int userChoice = -1;
        while(true){
            printTeam();
            System.out.println("What would you like to do?\n1. Add a Pokemon\n2. Remove a Pokemon\n3. Exit");
            userChoice = userScanner.nextInt();
            if (userChoice > 3 || userChoice < 1){
                System.out.println("invalid choice. Please try again.");
            }
            else if(userChoice == 1){
            userAddPokemon(userScanner, pokedex);
         }
         else if(userChoice == 2){
            while(true){
                System.out.println("Choose a Pokemon to remove:");
                printTeam();
                userChoice = userScanner.nextInt();
                if(userChoice > playerPokemon.size() || userChoice < 1){
                    System.out.println("invalid choice. Please try again.");
                }
                else{
                    break;
                }
            }
            playerPokemon.remove(userChoice - 1);
         }
         else if(userChoice == 3){
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

}
