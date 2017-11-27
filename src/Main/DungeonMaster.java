package Main;

import Gameplay.Dungeon;
import Gameplay.SetUp;
import Gameplay.Town;
import Players.Players;
import lib.ConsoleIO;

import java.util.Scanner;


public class DungeonMaster {

    public static void main(String[] args) {
        start();
    }


    // Starts game, shows top menu screen, handles player decisions
    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Dungeon Master");
        System.out.println("--------------");
        System.out.println("");
        System.out.println("");

        boolean cont = true;
        while (cont){
            int mainMenu = ConsoleIO.promptForMenuSelection(new String[]{"1: New Game", "2: Load Game"}, true);
            switch (mainMenu) {
                case 1:
                    System.out.println("");
                    System.out.println("Are you sure? Any previous saved games will be lost. ");
                    if (ConsoleIO.promptForBool("(Yes/No)","Yes","No")) {
                        SetUp.newGame();
                        System.out.println("");
                        SetUp.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Intro.txt");
                        scanner.nextLine();
                        SetUp.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Intro2.txt");
                        scanner.nextLine();
                        cont = false;
                        System.out.println("");
                        Players.printPlayer();
                        System.out.println("");
                        game();
                        break;
                    } else {
                        break;
                    }
                case 2:
                    SetUp.loadGame();
                    cont = false;
                    game();
                    break;
                case 0:
                    System.out.println("Goodbye");
                    System.exit(0);

            }


        }

//
    }

    // Main game entry point.
    private static void game() {
        boolean play = true;
        while (play){
            System.out.println("");
            System.out.println("---------");
            System.out.println("Base Camp");
            System.out.println("---------");
            System.out.println("");

            Players.printPlayer();
            System.out.println("");

            switch (ConsoleIO.promptForMenuSelection(new String[]{"1: To Town","2: To the Dungeon","3: Main Menu"},false)){
                case 1:
                    Town.start();
                    break;
                case 2:
                    Dungeon.start();
                    break;
                case 3:
                    SetUp.mainMenu();
                    break;
                default:
                    System.out.println("Game error");
                    play = false;
            }
        }
    }

    public static void endGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        SetUp.delay(2000);
        SetUp.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Win.txt");
        scanner.nextLine();
        System.out.println("Thanks For Playing!");
        System.exit(0);
    }
}
