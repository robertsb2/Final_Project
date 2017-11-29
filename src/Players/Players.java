package Players;

import Gameplay.Utilities;
import Weapons.Weapon;
import lib.ConsoleIO;

import java.util.ArrayList;

public class Players {
    private static String name = "";
    private static int strength = 0;
    private static int defense = 0;
    private static int maxHealth = 0;
    private static int curHealth = 0;
    private static int gold = 0;
    private static int pieces = 0;

    private static ArrayList pack = new ArrayList();

    // Name methods:
    public static void setName(String entry, boolean savedGame){

        if(savedGame){
            name = entry;
        } else {
            name = ConsoleIO.promptForInput("Please enter your name: ",false);
        }
    }
    public static String getName(){
        return name;
    }

    // Strength Stat Methods:
    public static void setStrength(int value){
        strength += value;
    }
    public static int getStrength(){
        return strength;
    }

    //Defense Stat Methods:
    public static void setDefense(int value){
        defense += value;
    }
    public static int getDefense() {
        return defense;
    }

    // Max Health Methods:
    public static void setMaxHealth(int value){
        maxHealth += value;
    }
    public static int getMaxHealth() {
        return maxHealth;
    }

    //Current Health Methods:
    public static void setCurHealth(int value){
        curHealth += value;
    }
    public static int getCurHealth() {
        return curHealth;
    }
    public static void heal(int amount){
        if  (maxHealth-curHealth >= amount) {
            System.out.println("You regained " + amount + " health");
            Utilities.delay(1000);
            curHealth += amount;
        } else {
            System.out.println("You're at full health.");
            Utilities.delay(1000);
            curHealth = maxHealth;
        }
    }

    // Gold Methods:
    public static void setGold(int amount){
        gold += amount;
    }
    public static void addGold(int amount){
        System.out.println(amount + " gold added to pouch.");
        gold += amount;

    }
    public static int getGold() {
        return gold;
    }
    public static void subtractGold(int amount){
        gold -=amount;
    }


    // Pack Methods:
    public static int getPackSize() {
        return pack.size();
    }
    public static void fillPack(int amount){
        for (int i = 0; i < amount ; i++) {
            pack.add("potion");
        }
    }
    public static boolean openPack() {
        String choice = null;
        try {
            if (pack.size() != 0) {

                for (Object item : pack) {
                    System.out.println(item.toString());

                }
            } else {
                System.out.println("Your pack is empty.");
                Utilities.delay(1000);
                return false;
            }
            choice = ConsoleIO.promptForInput("What do you want to use? (Type 'Cancel' to quit)", false);
        } catch (Exception e) {
            System.out.println("pack print error");
        }
        try {
            if ( pack.contains(choice)){
                useItem(choice);
                return true;
            } else if(choice.equalsIgnoreCase("cancel")) {
                return false;
            } else {
                System.out.println("you don't have any of those.");
            }
        }catch (Exception e){
            System.out.println("pack item choice error");
        }
        return false;
    }
    private static void useItem(String item){
        try {
            switch (item){
                case "potion":
                    if(maxHealth - curHealth > 0) {
                        Players.pack.remove(item);
                        heal(maxHealth / 4);
                    } else {
                        System.out.println("You are at full health.");
                    }
                    break;
                default:
                    System.out.println("Use Item Error");

            }
        } catch (NullPointerException e){
            System.out.println("Your pack is empty");
        }
    }


    // Talisman Methods
    public static void setTalismanPieces(int value){
        pieces = value;
    }
    public static int getTalismanPieces() {
        return pieces;
    }
    public static void findPiece(){
        int i = Utilities.getRandom(1,3,1);
        switch (i){
            case 1:
                System.out.println("You found a Talisman Piece!");
                if(pieces == 0){
                    System.out.println("Find three of these Talisman pieces to gain a strength or defense point!");
                }
                pieces ++;
                statUp();
                break;
            default:
                System.out.println("There is nothing of interest here.");
        }
    }



    //Player stats
    private static void statUp() {

        if(pieces == 3){
            pieces = 0;
            System.out.println("You have found 3 Talisman Pieces!");
            switch(ConsoleIO.promptForMenuSelection(new String[]{"1: Increase Strength", "2: Increase Defense"},false)){
                case 1:
                    setStrength(1);
                    System.out.println("Strength increased by 1");
                    Utilities.delay(1000);
                    break;
                case 2:
                    setDefense(1);
                    System.out.println("Defense increased by 1");
                    Utilities.delay(1000);
                    break;
            }
            System.out.println();
            printPlayer();
            System.out.println();
        } else {
            System.out.println("Only " + (3 - pieces) + " left!");
        }
    }
    public static void printPlayer(){
        System.out.println(name);
        System.out.println("--------");
        System.out.println("Strength: " + strength);
        System.out.println("Defense: " + defense);
        System.out.println("Max Health: " + maxHealth);
        System.out.println("Current Health: " + curHealth);
        System.out.println("Gold: " + gold);
        System.out.println("");
        System.out.println("--------");
        System.out.println("Weapon: " + Weapon.getWeaponName());
        System.out.println("Talisman Pieces: " + getTalismanPieces());
        System.out.println("");
    }
}
