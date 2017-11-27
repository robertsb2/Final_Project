package Monsters;

import Gameplay.Dungeon;
import Gameplay.SetUp;
import Players.Players;
import Weapons.Weapon;
import lib.ConsoleIO;

public class Monster {
    private static int strength;
    private static int defense;
    private static int health;
    private static int escapeRoll;
    private static Types type;
    private static String name;
    private static boolean escape;

    public enum Types {
        //TYPE("name", Health, Strength, Defense, Escape Requirement)
        GOBLIN("Goblin", 9, 8, 8, 9),
        ZOMBIE("Zombie", 15, 10, 11, 11),
        BANSHEE("Banshee", 20, 12, 12, 13),
        VAMPIRE("Vampire", 23, 14, 13, 15),
        GHOUL("Ghoul", 25, 16, 14, 17),
        WEREWOLF("Werewolf", 28, 18, 15, 19),
        MINOTAUR("Minotaur", 30, 20, 16, 21),
        CYCLOPS("Cyclops", 35, 22, 18, 23),
        DRAGON("Dragon", 40, 24, 20, 999),
        DEMON("Demon", 100, 26, 25, 999);

        private final String eName;
        private final int eHealth;
        private final int eStrength;
        private final int eDefense;
        private final int eEscape;

        Types(String type, int health, int strength, int defense, int escapeRoll){
            this.eName = type;
            this.eHealth = health;
            this.eStrength = strength;
            this.eDefense = defense;
            this.eEscape = escapeRoll;
        }

    }


    // Accepts random input to set new monster type and stats.
    public static void foundMonster(int i){
        type = Types.values()[i];
        strength = type.eStrength;
        defense = type.eDefense;
        health = type.eHealth;
        name = type.eName;
        escapeRoll = type.eEscape;

        fight();

    }

    // Logic for battle sequence.
    private static void fight() {
        escape = false;
        System.out.println("A " + Monster.name + " appeared!");
        SetUp.delay(1000);
        while (!escape){
            System.out.println("");
            printBattleStats();
            System.out.println("");
            playerTurn();
            if(Monster.health > 0 && !escape) {
                monsterAttack();
            }
            if (Monster.health <=0){
                System.out.println("You defeated the " + Monster.name);
                Dungeon.getReward();
                SetUp.delay(500);
                escape = true;
            }
        }
    }

    // Handles player decision during battle. Checks if player dies.
    private static void playerTurn() {
        int attack;
        int damage;
        if (Players.getCurHealth() <= 0){
            SetUp.delay(1000);
            System.out.println("Game Over...");
            SetUp.delay(1000);
            System.exit(0);


        }
        switch (ConsoleIO.promptForMenuSelection(new String[]{"1: Fight","2: Open pack", "3: Escape"},false)){
            case 1: // player attacks monster
                SetUp.delay(500);
                attack = SetUp.getRandom(20) + (Players.getStrength()/2) + 1;
                if(attack >= Monster.defense){
                    damage = (SetUp.getRandom(Players.getStrength()/2)) + 1 + Weapon.getAtkPower();
                    Monster.health -= damage;
                    System.out.println("You hit and deal " + damage + " damage.");
                } else {
                    System.out.println("Your attack missed...");
                }
                SetUp.delay(500);
                break;
            case 2: // player uses item
                if(!Players.openPack()){
                    playerTurn();
                }
                break;
            case 3: // player tries to escape
                if((SetUp.getRandom(20) + 1 + (Players.getStrength())) > (SetUp.getRandom(20) + 1 + Monster.escapeRoll)) {
                    SetUp.delay(1000);
                    System.out.println("You got away.");
                    SetUp.delay(1000);
                    escape = true;
                } else {
                    System.out.println("Can't Escape!");
                }



        }
    }

    // Handles monsters' turn.
    private static void monsterAttack() {
        System.out.println("The " + Monster.type + " attacks");
        SetUp.delay(500);
        int attack = SetUp.getRandom(20) + (Monster.strength/2) +1;
        if(attack >= Players.getDefense()){
            int damage = SetUp.getRandom(Monster.strength/2)+1;
            System.out.println("It deals " + damage + " damage.");
            Players.setCurHealth(-damage);
        } else {
            System.out.println("It's attack misses");
        }
        SetUp.delay(500);
    }

    // Prints updated player and monster health/stats each round during battle.
    private static void printBattleStats(){
        System.out.println(Players.getName() + "                            " + type);
        System.out.println("--------                         --------");
        System.out.println("Strength: " + Players.getStrength() + "                      Strength: " + strength);
        System.out.println("Defense: " + Players.getDefense() + "                       Defense: " + defense);
        System.out.println("Current Health: " + Players.getCurHealth() + "               Current Health: " + health);
    }

}
