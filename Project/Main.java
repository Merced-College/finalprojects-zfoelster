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

        PokemonTeam playerTeam = new PokemonTeam();

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
        

        System.out.println("Welcome to Pokemon Team Builder!\n\nWhat would you like to do?\n1. Create a new team\n2. Edit an existing team\n3. Exit\n");
        //playerTeam.printTeam();
        //playerTeam.addPokemon(pokedex.get("Charizard"));
        //playerTeam.addPokemon(pokedex.get("Urshifu:Single Strike Style"));
        //playerTeam.printTeam();
        //playerTeam.removePokemon(pokedex.get("Charizard"));
        //playerTeam.printTeam();

    

    }


    //Prints the player's team in a formatted table.
    //Helped with formatting the printf by GitHub Copilot.
    public static void printTeam(ArrayList<Pokemon> playerPokemon)
    {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-35s %-20s %-8s %-8s %-8s %-8s %-8s %-10s%n", 
            "Num",
            "Name",
            "Type",
            "HP",
            "ATK",
            "DEF",
            "SP. ATK",
            "SP. DEF",
            "SPD");
            System.out.println("------------------------------------------------------------------------------------------------------------------");


        //Loop to print all the Pokemon in the player's team.
        for (int i = 0; i < playerPokemon.size(); i++) {
            Pokemon p = playerPokemon.get(i);
            System.out.printf("%-5d %-35s %-20s %-8d %-8d %-8d %-8d %-8d %-10d%n", 
                (i + 1), 
                p.getName(), 
                p.getType(), 
                p.getHealth(), 
                p.getAttack(), 
                p.getDefense(), 
                p.getSpAttack(), 
                p.getSpDefense(),
                p.getSpeed());
        }
    }
}