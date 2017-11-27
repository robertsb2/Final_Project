package Weapons;

public class Weapon {
    public enum Weapons{
        //NAME("Name",Attack Power, Cost)
        EMPTY("Dagger",0,0),
        SHORT_SWORD("Short Sword",1,30),
        LONG_SWORD("Long Sword",2,60),
        BROADSWORD("Broadsword",3,90),
        MACE("Mace",4,120),
        AXE("Axe",5,150);

        private final String weaponName;
        private final int power;
        private final int cost;

        Weapons(String name, int power, int cost){
            this.weaponName = name;
            this.power = power;
            this.cost = cost;
        }
    }

    private static int atkPower;
    private static String name = "Dagger";
    private static int weaponId = 0;

    // Loads saved weapons or updates when new weapon is purchased
    public static void setWeapon(int idNumber){
        Weapons weapon;
        weapon = Weapons.values()[idNumber];
        atkPower = weapon.power;
        name = weapon.weaponName;
        weaponId = idNumber;
    }

    // Returns the attack power characteristic of the set weapon.
    public static int getAtkPower(){
        return atkPower;
    }

    // Returns weapon name
    public static String getWeaponName(){
        return name;
    }

    // Returns weapon ID number. Used mostly for saving the game, but also for purchasing.
    public static int getWeaponId(){
        return weaponId;
    }

    // Returns cost of specific weapon.
    public static int getCost(int idNumber){
        Weapons weapon = Weapons.values()[idNumber];

        return weapon.cost;
    }
}
