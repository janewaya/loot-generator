package edu.grinnell.csc207.lootgenerator;

import edu.grinnell.csc207.lootgenerator.MagicOptions.Magic;
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
        
        MagicOptions Abracadabra = new MagicOptions();
        Magic Suffix = Abracadabra.getSuffix();
        String sufStr = "";
        int sufVal = 0;
        String sufCode = "";
        
        String preStr = "";
        int preVal = 0;
        String preCode = "";
        if(Suffix != null){
            sufStr = Suffix.getName();
            sufVal = Suffix.getMagVal();
            sufCode = Suffix.getMagCode();
        }
        Magic Prefix = Abracadabra.getPrefix();
        if(Prefix != null){
            preStr = Prefix.getName();
            preVal = Prefix.getMagVal();
            preCode = Prefix.getMagCode();
        }
        
        //System.out.println("Fighting " + Paul.toString() + "...");
        //System.out.println("You have slain " + Paul.toString() + "!");
        //System.out.println(Paul.toString() + " dropped:");
        System.out.println("\n" + preStr + " " + Loot +  " " + sufStr + "\n");
        if(preVal != 0){
            System.out.println(preCode + ": " + preVal);
        }
        if(sufVal != 0){
            System.out.println(sufCode + ": " + sufVal);
        }
        
    }
}
