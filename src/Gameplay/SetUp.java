package Gameplay;

import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class SetUp {

    // Resets saved variables and prints intro.
    public static void newGame() {
        System.out.println("");
        Players.setName("",false);
        Players.setStrength(10);
        Players.setDefense(10);
        Players.setMaxHealth(20);
        Players.setCurHealth(20);
        Players.setGold(10);
    }

    // Reads saved data file and loads saved variables.
    public static void loadGame() {
        try {
            BufferedReader savedGameFile = new BufferedReader(new FileReader("C:\\Bryan_Roberts_Final_Project\\Save Files\\Player.txt"));
            String name = savedGameFile.readLine();
            Players.setName(name,true);
            System.out.println("");
            System.out.println("Welcome back " + name);
            System.out.println("");
            System.out.println("Loading...");
            delay(1000);
            System.out.println("");
            Players.setStrength(Integer.parseInt(savedGameFile.readLine()));
            Players.setDefense(Integer.parseInt(savedGameFile.readLine()));
            Players.setMaxHealth(Integer.parseInt(savedGameFile.readLine()));
            Players.setCurHealth(Integer.parseInt(savedGameFile.readLine()));
            Players.setGold(Integer.parseInt(savedGameFile.readLine()));
            Weapon.setWeapon(Integer.parseInt(savedGameFile.readLine()));
            Players.fillPack(Integer.parseInt(savedGameFile.readLine()));
            Players.setTalismanPieces(Integer.parseInt(savedGameFile.readLine()));
            System.out.println("");

        } catch (Exception e){
            System.out.println("No save files found. Starting new game. " + e);
            newGame();
        }
    }

    // Overwrites saved game file with current data values.
    static void save(String type) {
        BufferedWriter newGameFile = null;
        try {
            if (!type.equals("auto")) {
                System.out.println("Saving.....");
                delay(1500);
            }
            newGameFile = new BufferedWriter(new FileWriter("C:\\Bryan_Roberts_Final_Project\\Save Files\\Player.txt"));
            newGameFile.write(Players.getName());
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getStrength())); // Saves players current Strength.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getDefense())); // Saves players current Defense.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getMaxHealth())); // Saves players Max Health.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getCurHealth())); // Saves players Current Health.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getGold())); // Saves players current amount of Gold.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Weapon.getWeaponId())); // Saves players current weapon.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getPackSize())); // Saves number of potions player has.
            newGameFile.newLine();
            newGameFile.write(Integer.toString(Players.getTalismanPieces()));

            if (!type.equals("auto")) {
                System.out.println("Game Saved");
            }
        } catch (Exception e) {
            System.out.println("save error");
        } finally {
            try {
                if (newGameFile != null) {
                    newGameFile.flush();
                    newGameFile.close();

                }
            } catch (Exception e){
                System.out.println("save error");
            }
        }

    }

    // Shows current stats, handles save requests, program exit point.
    public static void mainMenu(){
        String[] mainMenu = new String[]{"1: Continue", "2: Save & Continue","3: Save & Quit"};
        Players.printPlayer();
        switch (ConsoleIO.promptForMenuSelection(mainMenu,false)){
            case 1:
                break;
            case 2:
                save("manual");
                delay(500);
                break;
            case 3:
                save("manual");
                delay(500);
                System.exit(0);
        }

    }

    // Handle read file requests.
    public static void reader(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e){
            System.out.println("Reader Error");
        }
    }

    // creates variable delay in console output for readability.
    public static void delay(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e){
            System.out.println("delay error");
        }
    }

    // Variable bound Random Number Generator
    public static int getRandom(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}

