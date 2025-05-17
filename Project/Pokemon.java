package Project;

public class Pokemon {
private String name;
private String form;
private String type1;
private String type2;
private int health;
private int attack;
private int defense;
private int spAttack;
private int spDefense;
private int speed;
private int statTotal;

    public Pokemon(String name, String form, String type1, String type2, int health, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.name = name;
        this.form = form;
        this.type1 = type1;
        this.type2 = type2;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.statTotal = health + attack + defense + spAttack + spDefense + speed;
    }

//Since Pokemon can have two types, I used an if else statement to check if there is a second type.
    public String getType(){
        if(type2.isEmpty()){
            return type1;
        }
        else{
            return type1 + "/" + type2;
        }
    }

    public String getForm() {
        return form;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }
    

    public String getName() {
        if(form.isEmpty()){
            return name;
        }
        else{
            return name + " (" + form + ")";
        }
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }
    
    public int getDefense() {
        return defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStatTotal() {
        return statTotal;
    }
}