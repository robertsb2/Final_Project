package Main;

import Gameplay.Dungeon;
import Gameplay.Utilities;
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
//        int test = 0;
//        while (true) {
//            if ((Utilities.getRandom(2, 10, 1) + 14) > (Utilities.getRandom(2, 10, 1) + 15)) {
//                System.out.println("passed");
//            } else {
//                System.out.println("fail");
//            }
//            test++;
//            if (test>=10) {
//                System.exit(0);
//                break;
//            }
//        }
//        int pint;
//        int ptot = 0;
//        int monint;
//        int montot = 0;
//
//        while (true){
//            pint = (Monster.getDamage(14,12,1));
//            monint = (Monster.getDamage(12,13,0));
//            System.out.println("Player:");
//            System.out.println(pint);
//            System.out.println("Monster:");
//            System.out.println(monint);
//            System.out.println();
//            System.out.println();
//            ptot += pint;
//            montot += monint;
//            test ++;
//            if(test>=3){
//                System.out.println("Player total:");
//                System.out.println(ptot);
//                System.out.println("Monster total:");
//                System.out.println(montot);
//                System.exit(0);
//                break;
//            }
//        }




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
                        Utilities.newGame();
                        System.out.println("");
                        Utilities.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Intro.txt");
                        scanner.nextLine();
                        Utilities.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Intro2.txt");
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
                    Utilities.loadGame();
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
                    Utilities.mainMenu();
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
        Utilities.delay(2000);
        Utilities.reader("C:\\Bryan_Roberts_Final_Project\\Dialogue\\Win.txt");
        scanner.nextLine();
        System.out.println("Thanks For Playing!");
        System.exit(0);
    }
}
