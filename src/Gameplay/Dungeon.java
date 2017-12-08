package Gameplay;
import Main.DungeonMaster;
import Monsters.Monster;
import Players.Players;
import lib.ConsoleIO;

public class Dungeon {
    public enum Rooms {
        HALLWAY("Dark and dusty, full of cobwebs."),
        TREASURY("A small room with a single golden chest."),
        CAVERN("A massive opening with damp walls."),
        LIBRARY("A room filled with old books."),
        STAIRS("Cobblestone stairs leading further into the darkness..."),
        SHRINE("A large room with polished granite walls and a candle adorned altar.");



        private final String roomDescription;
        Rooms(String roomDescription){
            this.roomDescription = roomDescription;
        }
    }

    private static int floor = 1;
    private static int hallwayCount;
    private static int cavernCount;
    private static int libraryCount;
    private static int stairsCount;
    private static int treasuryCount;
    private static int shrineCount;
    private static int roomsExplored;
    private static int floorSize;
    private static boolean foundStairs;
    private static boolean floorCleared = false;

    // Entry point for dungeon methods.
    public static void start(){
        Utilities.save("auto");
        foundStairs = false;
        createFloor();

        System.out.println("");
        System.out.println("---------");
        System.out.println("Dungeon");
        System.out.println("Floor " + floor);
        System.out.println("---------");
        System.out.println("");
        Utilities.delay(500);



        String[] dungeonOptions;
        dungeonOptions = new String[]{"1: Explore", "2: Go Upstairs", "3: Open Pack", "4: Exit Dungeon", "5: Main Menu"};
        boolean inDungeon = true;
        do {
            if(floor == 10){
                Utilities.save("auto");
                finalFloor();
            } else {
                System.out.println("");
                int choice = ConsoleIO.promptForMenuSelection(dungeonOptions, false);
                switch (choice) {
                    case 1:
                        explore();
                        break;
                    case 2:
                        upStairs();
                        break;
                    case 3:
                        Players.openPack();
                        break;
                    case 4:
                        exitDungeon();
                        inDungeon = false;
                        break;
                    case 5:
                        Utilities.mainMenu();
                        break;
                }
            }
        } while (inDungeon);

    }

    // Randomly selects tower rooms type.
    private static void explore() {
        boolean valid = false;
        int i = 0;
        int j;
        do {
            j = Utilities.getRandom(1,100,1);
            if (j <= 22) {
                i = 1; //Hallway 22% chance
            } else if (j <= 44) {
                i = 2; //Cavern 22% chance
            } else if (j <= 66) {
                i = 3; //Library 22% chance
            } else if (j <= 80) {
                i = 4; //Stairs 14% chance
            } else if (j <= 90) {
                i = 5; //Treasury 10% chance
            } else if (j <= 100) {
                i = 6; //Shrine 10% chance
            }

            if (availableRooms(i)){
                valid = true;
            }

            if(roomsExplored == floorSize){
                floorCleared = true;
            }

        } while (!valid && !floorCleared);
        System.out.println("");

        if (!floorCleared) {
            Rooms room = roomType(i);
            System.out.println(room);
            System.out.println("-------");
            if (room != null) {
                System.out.println(room.roomDescription);
            }
            System.out.println("-------");
            System.out.println("");
            Utilities.delay(1000);
            encounter(room);
            roomsExplored++;



        } else {
            System.out.println("You have explored all the rooms on this floor.");
        }
    }

    private static boolean availableRooms(int i) {
        switch (i){
            case 1:
                if(hallwayCount > 0){
                    hallwayCount--;
                    return true;
                } else return false;
            case 2:
                if(cavernCount > 0){
                    cavernCount--;
                    return true;
                } else return false;
            case 3:
                if(libraryCount > 0){
                    libraryCount--;
                    return true;
                } else return false;
            case 4:
                if (stairsCount > 0){
                    stairsCount--;
                    return true;
                } else return false;
            case 5:
                if (treasuryCount > 0){
                    treasuryCount--;
                    return true;
                } else return false;
            case 6:
                if (shrineCount > 0){
                    shrineCount --;
                    return true;
                } else return false;
        }


        return true;
    }

    // Takes input from explore() to generate selected room.
    private static Gameplay.Dungeon.Rooms roomType(int i) {

        switch (i){
            case 1:
                return Rooms.HALLWAY;
            case 2:
                return Rooms.CAVERN;
            case 3:
                return Rooms.LIBRARY;
            case 4:
                foundStairs = true;
                return Rooms.STAIRS;
            case 5:
                return Rooms.TREASURY;
            case 6:
                return Rooms.SHRINE;
            default:
                return null;
        }
    }

    // Handles player encounters based on room type.
    private static void encounter(Rooms room) {
        switch (room){
            case CAVERN:
                Monster.foundMonster(Utilities.getRandom(1,floor,0));
                break;
            case SHRINE:
                if(ConsoleIO.promptForBool("Pray at the alter? (Yes/No)","Yes","No")) {
                    int i = Utilities.getRandom(1,3,1);
                    if (i == 1) {
                        Utilities.delay(2000);
                        System.out.println("You max health has increased by 1");
                        Players.setMaxHealth(1);
                        Players.setCurHealth(1);
                        Utilities.delay(1000);
                    } else if (i == 2) {
                        Utilities.delay(2000);
                        Players.heal(5);
                    } else {
                        Utilities.delay(2000);
                        System.out.println("Nothing happened...");
                        Utilities.delay(1000);
                    }
                }
                break;
            case HALLWAY:
                if(Utilities.getRandom(1,2,1) == 2){
                    System.out.println("You found some gold!");
                    Utilities.delay(1000);
                    getReward();
                } else {
                    Monster.foundMonster(Utilities.getRandom(1,floor,0));
                }
                break;
            case LIBRARY:
                if (Utilities.getRandom(1,6,1) > 5){
                    System.out.println("You found a hidden passageway! Choose a location");
                    int i = ConsoleIO.promptForMenuSelection(new String[]{"1: Stairs","2: Treasury","3: Shrine"},false) + 3;
                    System.out.println("");
                    Rooms passage = roomType(i);
                    System.out.println(passage);
                    System.out.println("-------");
                    if (passage != null) {
                        System.out.println(passage.roomDescription);
                    }
                    System.out.println("-------");
                    System.out.println("");
                    Utilities.delay(1000);
                    encounter(passage);
                } else {
                    Players.findPiece();
                    System.out.println();
                }
                break;
            case TREASURY:
                getReward();
                break;

        }
    }

    // Checks if stairs have been found on current floor.
    private static void upStairs() {
        if(foundStairs){
            foundStairs = false;
            floor++;
            System.out.println("");
            System.out.println("---------");
            System.out.println("Dungeon");
            System.out.println("Floor " + floor);
            System.out.println("---------");
            System.out.println("");
            createFloor();

        } else {
            System.out.println("");
            System.out.println("You haven't found the stairs for this floor yet...");
            Utilities.delay(1000);
        }
    }

    private static void createFloor() {
        floorCleared = false;
        roomsExplored = 0;
        hallwayCount = Utilities.getRandom(1,4,3);
        cavernCount = Utilities.getRandom(1,4,3);
        libraryCount = Utilities.getRandom(1,4,1);
        stairsCount = 1;
        treasuryCount = Utilities.getRandom(1,3,0);
        shrineCount = Utilities.getRandom(1,2,1);
        floorSize = hallwayCount + cavernCount + libraryCount + stairsCount + treasuryCount + shrineCount;
//        System.out.println(floorSize + " rooms");
    }

    // Initiates Boss Battle when final floor is reached.
    private static void finalFloor() {
        Utilities.delay(2000);
        System.out.println("You've done well to make it this far mortal...");
        Utilities.delay(2000);
        System.out.println("but you will go no further.");
        Utilities.delay(2000);
        System.out.println();
        System.out.println();
        Monster.foundMonster(floor -1);
        DungeonMaster.endGame();
    }

    // Handles player request to exit dungeon.
    private static void exitDungeon() {
        floor = 1;

    }

    // Gives random reward upon battle end or when treasure is found
    public static void getReward(){
        Players.addGold(Utilities.getRandom(1,5,floor));
    }
}
