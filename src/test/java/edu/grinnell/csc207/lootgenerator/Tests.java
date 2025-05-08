package edu.grinnell.csc207.lootgenerator;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void firstMonContained() throws FileNotFoundException {
        Monsters pals = new Monsters();
        String Jack = pals.get(0).toString();
        assertEquals(Jack, "DarkHunter");
    }

    @Test
    public void lastMonContained() throws FileNotFoundException {
        Monsters pals = new Monsters();
        String Jack = pals.get(48).toString();
        assertEquals(Jack, "OverSeer");
    }

    @Test
    public void firstTCContained() throws FileNotFoundException {
        TreasureClass test = new TreasureClass();
        String Jack = test.start.printName(test.start);
        assertEquals(Jack, "Act 1 Cast A");
    }

    @Test
    public void firstSufContained() throws FileNotFoundException {
        MagicOptions test = new MagicOptions();
        if (test.magicSuffixces != null) {
            String Jack = test.get(0, "Suffix").getName();
            assertEquals(Jack, "of Warming");
        }
    }

    @Test
    public void lastSufContained() throws FileNotFoundException {
        MagicOptions test = new MagicOptions();
        if (test.magicSuffixces != null) {
            String Jack = test.get(385, "Suffix").getName();
            assertEquals(Jack, "of Malice");
        }
    }

    @Test
    public void firstPreContained() throws FileNotFoundException {
        MagicOptions test = new MagicOptions();
        if (test.magicPrefixces != null) {
            String Jack = test.get(0, "Prefix").getName();
            assertEquals(Jack, "Sturdy");
        }
    }

    @Test
    public void lastPreContained() throws FileNotFoundException {
        MagicOptions test = new MagicOptions();
        if (test.magicPrefixces != null) {
            String Jack = test.get(371, "Prefix").getName();
            assertEquals(Jack, "Aureolin");
        }
    }
    
}
