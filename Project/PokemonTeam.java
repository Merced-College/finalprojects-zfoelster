package Project;

import java.util.ArrayList;
import java.util.Scanner;


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


    public void editTeam(Scanner userScanner){
        int userChoice = -1;
        while(true){
            System.out.println("What would you like to do?\n1. Add a Pokemon\n2. Remove a Pokemon\n3. Exit");
            userChoice = userScanner.nextInt();
            if (userChoice > 3 || userChoice < 1){
                System.out.println("invalid choice. Please try again.");
            }
            else if (userChoice == 1){
                break;
            }
         }
         if(userChoice == 1){

         }
         else if(userChoice == 2){

         }
         else if(userChoice == 3){

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
