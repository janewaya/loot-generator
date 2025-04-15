/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author janewaya
 */
public class TreasureClass {

    /**
     * A class that allows the storing of TreasureEntries.
     *
     */
    public static class TreasureEntry {

        String TC;
        String[] options;
        TreasureEntry right;
        TreasureEntry left;

        /**
         * The TreasureEntry Object
         *
         *
         * @param TC - The Treasure Class of the object
         * @param options - An array storing the three possible objects within
         * the TC
         * @param right - The right node
         * @param left - the left node
         */
        public TreasureEntry(String TC, String[] options, TreasureEntry right, TreasureEntry left) {
            TC = this.TC;
            options = this.options;
            right = this.right;
            left = this.left;
        }

        /**
         * The TreasureEntry Object (Saves time when right and left aren't yet
         * created)
         *
         *
         * @param TC - The Treasure Class of the object
         * @param options - An array storing the three possible objects within
         * the TC
         */
        public TreasureEntry(String TC, String[] options) {
            this.TC = TC;
            this.options = options;
            this.right = null;
            this.left = null;
        }
    }

    private TreasureEntry start;

    /**
     * A tree of TreasureEntry objects
     *
     *
     */
    public TreasureClass() throws FileNotFoundException {
        start = null;

        Scanner TCSc = new Scanner(new File("TreasureClassEx.txt"));
        String TCScInfo;
        for (int i = 0; i < 68; i++) {
            TCScInfo = TCSc.nextLine();

            String findTC = "";
            String[] findOptions = new String[3];
            int index = TCScInfo.indexOf((char) 9);
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    findTC = TCScInfo.substring(0, index);
                } else if (j == 3) {
                    findOptions[j - 1] = TCScInfo.substring(0, TCScInfo.length());
                } else {
                    findOptions[j - 1] = TCScInfo.substring(0, index);
                }
                TCScInfo = TCScInfo.substring(index + 1, TCScInfo.length());
                index = TCScInfo.indexOf((char) 9);
            }
            if (start == null) {
                start = new TreasureEntry(findTC, findOptions);
            } else {
                put(findTC, findOptions);
            }
        }

    }

    /**
     * Helps getOptions to return the array of three objects
     *
     *
     * @param name - The TC for which we are finding possible options
     * @param opts - The found options
     * @param cur - The current node of the tree
     * @return String[] - The options tied to the given TC
     */
    private String[] getOptionsH(String name, String[] opts, TreasureEntry cur) {
        if (cur.TC.equals(name)) {
            opts = cur.options;
        }
        if (cur == null) {
            return opts;
        } else if (name.compareTo(cur.TC) < 0) {
            return getOptionsH(name, opts, cur.left);
        } else if (name.compareTo(cur.TC) > 0) {
            return getOptionsH(name, opts, cur.right);
        } else {
            return opts;
        }
    }

    /**
     * Returns the array of three objects attached to a given TC
     *
     *
     * @param name - The TC for which we are finding possible options
     * @return String[] The options tied to the given string
     */
    public String[] getOptions(String name) {
        if (!containsTC(name)) {
            throw new IllegalStateException("The program has broken. Sorry buddy no more killing today.");
        }
        return getOptionsH(name, null, start);
    }

    /**
     * Helps to determine if the given name is a TC
     *
     *
     * @param name - The TC we are determining the existence of
     * @param cur - The current node of the tree
     * @return boolean - true if name is a TC
     */
    private boolean containsTCH(String name, TreasureEntry cur) {
        if (name.equals(cur.TC)) {
            return true;
        } else if ((name.compareTo(cur.TC) < 0 && cur.left == null)
                || name.compareTo(cur.TC) > 0 && cur.right == null) {
            return false;
        } else if (name.compareTo(cur.TC) < 0) {
            return containsTCH(name, cur.left);
        } else {
            return containsTCH(name, cur.right);
        }
    }

    /**
     * Determines if the given name is a TC
     *
     *
     * @param name - The TC we are determining the existence of
     * @return boolean - true if name is a TC
     */
    public boolean containsTC(String name) {
        return containsTCH(name, start);
    }

    /**
     * Finds the TreasureEntry to add to the tree
     *
     *
     * @param name - The name of the TE we're adding
     * @param options - The options of the TE we're adding
     * @param cur - The current node of the tree
     * @return TreasureEntry - The TreasureEntry to be added to the tree
     */
    private TreasureEntry putH(String name, String[] options, TreasureEntry cur) {
        if (cur == null) {
            return new TreasureEntry(name, options);
        } else if (name.compareTo(cur.TC) < 0) {
            cur.left = putH(name, options, cur.left);
        } else if (name.compareTo(cur.TC) > 0) {
            cur.right = putH(name, options, cur.right);
        }
        return cur;
    }

    /**
     * Adds a TreasureEntry to the tree
     *
     *
     * @param name - The name of the TE we're adding
     * @param options - The options of the TE we're adding
     */
    public void put(String name, String[] options) {
        start = putH(name, options, start);
    }

}
