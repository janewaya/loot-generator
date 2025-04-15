package edu.grinnell.csc207.lootgenerator;

import edu.grinnell.csc207.lootgenerator.MagicOptions.Magic;
import edu.grinnell.csc207.lootgenerator.Monsters.Monster;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

    /**
     * Runs the loot generator program; Takes in user input to generate a
     * progression from monster to loot to how good the loot is.
     *
     * @param String[] args Optional (unused) command line input
     */
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Welcome to the loot generator of your dreams");
        Scanner violence = new Scanner(System.in);
        boolean replay = true;
        int fool = 0;
        System.out.println("Would you like to kill something? [y/n]");

        String keepGoing = violence.nextLine().toLowerCase();
        while (!keepGoing.equals("y") && !keepGoing.equals("n")) {
            if (fool < 7) {
                System.out.println("Please enter 'y' or 'n'- thanks!");
            } else {
                System.out.println("I'm not judging. But get it together man. 'y' or 'n' please.");
            }
            fool++;
            keepGoing = violence.nextLine().toLowerCase();
        }
        if (keepGoing.equals("n")) {
            System.out.println("Boring but acceptable.");
            replay = false;
        }

        while (replay) {

            Monster Paul = pickMonster();
            String TreasureClass = fetchTreasureClass(Paul);
            String Loot = generateBaseItem(TreasureClass);

            Magic Suffix = generateAffix("Suffix");
            String sufStr = "";
            int sufVal = 0;
            String sufCode = "";

            if (Suffix != null) {
                sufStr = Suffix.getName();
                sufVal = Suffix.getMagVal();
                sufCode = Suffix.getMagCode();
            }

            Magic Prefix = generateAffix("Prefix");

            String preStr = "";
            int preVal = 0;
            String preCode = "";

            if (Prefix != null) {
                preStr = Prefix.getName();
                preVal = Prefix.getMagVal();
                preCode = Prefix.getMagCode();
            }

            System.out.println("\n\nFighting " + Paul.toString() + "...");
            System.out.println("You have slain " + Paul.toString() + "!");
            System.out.println(Paul.toString() + " dropped:\n");
            if (preStr != "") {
                System.out.println(preStr + " " + Loot + " " + sufStr);
            } else {
                System.out.println(Loot + " " + sufStr);
            }
            System.out.println("Defense: " + generateBaseStats(Loot));
            if (preVal != 0) {
                System.out.println(preCode + ": " + preVal);
            }
            if (sufVal != 0) {
                System.out.println(sufCode + ": " + sufVal);
            }
            System.out.println("\n\nContinue the slaughter? [y/n]");
            keepGoing = violence.nextLine().toLowerCase();
            while (!keepGoing.equals("y") && !keepGoing.equals("n")) {
                if (fool < 7) {
                    System.out.println("Please enter 'y' or 'n'- thanks!");
                } else {
                    System.out.println("I'm not judging. But get it together man. 'y' or 'n' please.");
                }
                fool++;
                keepGoing = violence.nextLine().toLowerCase();
            }
            if (keepGoing.equals("n")) {
                System.out.println("Play again soon!");
                replay = false;
            }

        }
    }

    /**
     * Random selects a monster from the previously stored array of monster
     * objects
     *
     * @return Monster - returns the monster the user is killing
     */
    public static Monster pickMonster() throws FileNotFoundException {
        Monsters Buddies = new Monsters();
        Random monsterEncounter = new Random();
        int jumpScare = monsterEncounter.nextInt(49);
        return Buddies.monArr[jumpScare];
    }

    /**
     * Outputs the Treasure Class of the object we shall be selecting
     *
     * @param friend - Takes in the Monster one is killing
     * @return String - Returns the stored TC of the Monster
     */
    public static String fetchTreasureClass(Monster friend) throws FileNotFoundException {
        String Loot = friend.getMonTreasureClass();
        return Loot;
    }

    /**
     * Outputs a loot item of specified lootClass
     *
     * @param lootClass - Takes in the TC of the loot to select
     * @return String - Returns the name of the looted object
     */
    public static String generateBaseItem(String lootClass) throws FileNotFoundException {
        TreasureClass Hoard = new TreasureClass();
        String[] coolStuff = Hoard.getOptions(lootClass);
        Random whichLoot = new Random();
        int treasure = whichLoot.nextInt(3);
        if (Hoard.containsTC(coolStuff[treasure])) {
            return generateBaseItem(coolStuff[treasure]);
        }

        return coolStuff[treasure];

    }

    /**
     * Outputs a loot item of specified lootClass
     *
     * @param Loot - Takes in the looted object
     * @return int - Returns the defense level of the looted object
     */
    public static int generateBaseStats(String Loot) throws FileNotFoundException {
        Armory deathUponYeWhoSteal = new Armory();
        int index = Math.abs(Loot.hashCode() % 202);
        while (!deathUponYeWhoSteal.allArmor[index].getName().equals(Loot)) {
            index++;
            if (index == 202) {
                index = 0;
            }
        }
        return deathUponYeWhoSteal.allArmor[index].getDefVal();
    }

    /**
     * Outputs the Magic specification of the item
     *
     * @param type - determines if it's selecting a Suffix or Prefix
     * @return Magic - Returns an object containing the specifications of the
     * Suffix or Prefix.
     */
    public static Magic generateAffix(String type) throws FileNotFoundException {
        MagicOptions Abracadabra = new MagicOptions();
        if (type.equals("Suffix")) {
            if (Abracadabra.MagicSuffixces != null) {
                Random suffix = new Random();
                int newSuf = suffix.nextInt(386);
                return Abracadabra.MagicSuffixces[newSuf];
            }
            return null;
        } else {
            if (Abracadabra.MagicPrefixces != null) {
                Random prefix = new Random();
                int newPre = prefix.nextInt(372);
                return Abracadabra.MagicPrefixces[newPre];
            }
            return null;
        }
    }

}
