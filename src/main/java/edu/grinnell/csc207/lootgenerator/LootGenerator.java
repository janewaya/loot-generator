package edu.grinnell.csc207.lootgenerator;

import edu.grinnell.csc207.lootgenerator.Monsters.Monster;
import java.io.FileNotFoundException;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program kills monsters and generates loot!");
        Monsters Pauls = new Monsters();
        Monster Paul = Pauls.getMonster();
        System.out.println(Paul.toStringGood());
        TreasureClass Hoard = new TreasureClass();
        String Loot = Hoard.selectLoot(Paul.getMonTreasureClass());
        
        //System.out.println("Fighting " + Paul.toString() + "...");
        //System.out.println("You have slain " + Paul.toString() + "!");
        //System.out.println(Paul.toString() + " dropped:");
        System.out.println("\n" + Loot);
    }
}
