package Gameplay;

import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;

public class Town {
    private static String[] townOptions = new String[]{"1: Visit Healer","2: Visit Shops","3: Leave Town","4: Main Menu"};

    // Town code entry point
    public static void start(){
        boolean inTown = true;
        do{
            System.out.println("");
            System.out.println("---------");
            System.out.println("Durnwell");
            System.out.println("---------");
            System.out.println("");
            Utilities.delay(500);
            int choice = ConsoleIO.promptForMenuSelection(townOptions, false);
            switch (choice){
                case 1:
                    healer();
                    break;
                case 2:
                    shop();
                    break;
                case 3:
                    inTown = false;
                    break;
                case 4:
                    Utilities.mainMenu();

            }
        } while (inTown);
    }

    // It's a shop.
    private static void shop() {
        String shopFile = "C:\\Bryan_Roberts_Final_Project\\Dialogue\\Shop1.txt";
        String shopFile2 = "C:\\Bryan_Roberts_Final_Project\\Dialogue\\Shop2.txt";
        boolean shopping = true;
        int amount;
        int potionCost = 15;
        int journalCost = 20;
        int bookCost = 20;
        int choice;
        System.out.println("");
        System.out.println("Welcome to my shop.");
        Utilities.delay(500);
        while (shopping){
            System.out.println("");
            System.out.println("See anything you like?");
            System.out.println("");
            Utilities.reader(shopFile);
            System.out.println("Gold: " + Players.getGold());
            System.out.println("");
            switch (ConsoleIO.promptForMenuSelection(new String[]{"1: Buy Potions","2: Buy Journal","3: Buy Guidebook","4: Buy Weapons","5: Exit Shop"},false)){
                case 1:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * potionCost) > Players.getGold()){
                        insufficientFunds();
                    } else {
                        Players.subtractGold(amount * potionCost);
                        Players.fillPack(amount);
                        System.out.println(amount + " potion(s) added to pack.");
                        Utilities.delay(1000);
                    }
                    break;
                case 2:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * journalCost) > Players.getGold()){
                        insufficientFunds();
                    } else {
                        Players.subtractGold(amount * journalCost);
                        Players.setStrength(amount);
                        System.out.println("Your strength has increased by " + amount);
                        Utilities.delay(1000);
                    }
                    break;
                case 3:
                    amount = ConsoleIO.promptForInt("How many would you like to buy?",1,9999);
                    if ((amount * bookCost) > Players.getGold()){
                        insufficientFunds();
                    } else {
                        Players.subtractGold(amount*bookCost);
                        Players.setDefense(amount);
                        System.out.println("Your defense has increased by " + amount);
                        Utilities.delay(1000);
                    }
                    break;
                case 4:
                    System.out.println("");
                    Utilities.reader(shopFile2);
                    System.out.println("");
                    choice = ConsoleIO.promptForInt("Which one were you looking at?",1,Weapon.Weapons.values().length);
                    amount = Weapon.getCost(choice);
                    if (amount > Players.getGold()){
                        insufficientFunds();
                    } else {
                        Players.subtractGold(amount);
                        Weapon.setWeapon(choice);
                        System.out.println("Weapon acquired: " + Weapon.getWeaponName());
                        Utilities.delay(1000);
                    }
                    break;
                case 5:
                    shopping = false;
                    System.out.println("Thanks for stopping in!");
                    Utilities.delay(1000);
                    break;
            }
        }
    }

    // Heals player based on gold in inventory
    private static void healer() {
        int diff =  Players.getMaxHealth() - Players.getCurHealth();
        System.out.println("Welcome!");
        Utilities.delay(1000);
        if(diff != 0) {
            System.out.println("Let me heal your wounds...");
            Utilities.delay(1500);
            Players.heal(diff);
        } else {
            System.out.println("Oh, you don't need any healing...   Come back later.");
            Utilities.delay(500);
        }
    }

    // Prints insufficient fund errors in shop and at healer.
    private static void insufficientFunds(){
        System.out.println("");
        System.out.println("You don't have enough money.");
        System.out.println("");
        Utilities.delay(1000);
    }


}
