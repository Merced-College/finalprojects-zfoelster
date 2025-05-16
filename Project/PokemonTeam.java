package Project;

import java.util.ArrayList;


//made this class to hold each individual team of Pokemon.
public class PokemonTeam {

    public ArrayList<Pokemon> playerPokemon = new ArrayList<>();

    public void addPokemon(Pokemon pokemon) {
        playerPokemon.add(pokemon);
    }

    public void removePokemon(Pokemon pokemon){
        playerPokemon.remove(pokemon);
    }


    //Prints the player's team in a formatted table.
    //Helped with formatting the printf by GitHub Copilot.
    public void printTeam()
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
