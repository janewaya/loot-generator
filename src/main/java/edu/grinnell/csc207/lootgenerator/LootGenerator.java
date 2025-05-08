package edu.grinnell.csc207.lootgenerator;

import edu.grinnell.csc207.lootgenerator.MagicOptions.Magic;
import edu.grinnell.csc207.lootgenerator.Monsters.Monster;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Main Class- Runs LootGenerator
 */
public class LootGenerator {

    /**
     * Runs the loot generator program; Takes in user input to generate a
     * progression from monster to loot to how good the loot is.
     *
     * @param args Optional (unused) command line input
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

            Monster myBoiPaul = pickMonster();
            String treasureClass = fetchTreasureClass(myBoiPaul);
            String loot = generateBaseItem(treasureClass);

            Magic suffix = generateAffix("Suffix");
            String sufStr = "";
            int sufVal = 0;
            String sufCode = "";

            if (suffix != null) {
                sufStr = suffix.getName();
                sufVal = suffix.getMagVal();
                sufCode = suffix.getMagCode();
            }

            Magic prefix = generateAffix("Prefix");

            String preStr = "";
            int preVal = 0;
            String preCode = "";

            if (prefix != null) {
                preStr = prefix.getName();
                preVal = prefix.getMagVal();
                preCode = prefix.getMagCode();
            }

            System.out.println("\n\nFighting " + myBoiPaul.toString() + "...");
            System.out.println("You have slain " + myBoiPaul.toString() + "!");
            System.out.println(myBoiPaul.toString() + " dropped:\n");
            if (preStr != "") {
                System.out.println(preStr + " " + loot + " " + sufStr);
            } else {
                System.out.println(loot + " " + sufStr);
            }
            System.out.println("Defense: " + generateBaseStats(loot));
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
                    System.out.println("I'm not judging. But get it together man."
                                       + " 'y' or 'n' please.");
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
        Monsters buddies = new Monsters();
        Random monsterEncounter = new Random();
        int jumpScare = monsterEncounter.nextInt(buddies.size);
        return buddies.monArr.get(jumpScare);
    }

    /**
     * Outputs the Treasure Class of the object we shall be selecting
     *
     * @param friend - Takes in the Monster one is killing
     * @return String - Returns the stored TC of the Monster
     */
    public static String fetchTreasureClass(Monster friend) throws FileNotFoundException {
        String loot = friend.getMonTreasureClass();
        return loot;
    }

    /**
     * Outputs a loot item of specified lootClass
     *
     * @param lootClass - Takes in the TC of the loot to select
     * @return String - Returns the name of the looted object
     */
    public static String generateBaseItem(String lootClass) throws FileNotFoundException {
        TreasureClass hoard = new TreasureClass();
        String[] coolStuff = hoard.getOptions(lootClass);
        Random whichLoot = new Random();
        int treasure = whichLoot.nextInt(3);
        if (hoard.containsTC(coolStuff[treasure])) {
            return generateBaseItem(coolStuff[treasure]);
        }

        return coolStuff[treasure];

    }

    /**
     * Outputs a loot item of specified lootClass
     *
     * @param loot - Takes in the looted object
     * @return int - Returns the defense level of the looted object
     */
    public static int generateBaseStats(String loot) throws FileNotFoundException {
        Armory deathUponYeWhoSteal = new Armory();
        int index = Math.abs(loot.hashCode() % deathUponYeWhoSteal.size);
        while (!deathUponYeWhoSteal.allArmor[index].getName().equals(loot)) {
            index++;
            if (index == deathUponYeWhoSteal.size) {
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
        MagicOptions abracadabra = new MagicOptions();
        if (type.equals("Suffix")) {
            if (abracadabra.magicSuffixces != null) {
                Random suffix = new Random();
                int newSuf = suffix.nextInt(abracadabra.magicSufSize);
                return abracadabra.magicSuffixces.get(newSuf);
            }
            return null;
        } else {
            if (abracadabra.magicPrefixces != null) {
                Random prefix = new Random();
                int newPre = prefix.nextInt(abracadabra.magicPreSize);
                return abracadabra.magicPrefixces.get(newPre);
            }
            return null;
        }
    }

}
