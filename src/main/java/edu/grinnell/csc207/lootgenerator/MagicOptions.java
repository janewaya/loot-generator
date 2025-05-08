/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to 
 * change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author janewaya
 */
public class MagicOptions {

    /**
     * Allows for the creation of a Magic object
     *
     */
    public static class Magic {

        private String name;
        private String magCode;
        private int magVal;

        /**
         * Creates a magic object
         *
         * @param name - The name of the Attribute
         * @param magCode - The type of Magic
         * @param magMin - The min possible value
         * @param magMax - the max possible value
         */
        public Magic(String name, String magCode, int magMin, int magMax) {
            this.name = name;
            this.magCode = magCode;
            if (magMin != magMax) {
                Random magChoser = new Random();
                this.magVal = magChoser.nextInt(magMax - magMin) + magMin;
            } else {
                this.magVal = magMin;
            }
        }

        /**
         * Returns the name of the magic object
         *
         * @return String - Returns the name of the object
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the attribute of the magic object
         *
         * @return String - Returns the attribute of the object
         */
        public String getMagCode() {
            return this.magCode;
        }

        /**
         * Returns the value of the magic object
         *
         * @return int - Returns the value of the object
         */
        public int getMagVal() {
            return this.magVal;
        }
    }

    public ArrayList<Magic> magicSuffixces;
    public ArrayList<Magic> magicPrefixces;
    public int magicSufSize;
    public int magicPreSize;

    /**
     * Creates an object storing between 0 and 2 arrays of magic objects
     *
     */
    public MagicOptions() throws FileNotFoundException {
        Random magExists = new Random();
        if (magExists.nextInt(2) == 1) {
            this.magicSuffixces = new ArrayList<Magic>();
            magicArrCreator("MagicSuffix.txt");
        } else {
            this.magicSuffixces = null;
        }
        if (magExists.nextInt(2) == 1) {
            this.magicPrefixces = new ArrayList<Magic>();
            magicArrCreator("MagicPrefix.txt");
        } else {
            this.magicPrefixces = null;
        }
    }

    /**
     * Stores appropriate magic objects within the created array using
     * MagicPrefix.txt and MagicSuffix.txt
     *
     * @param path The file
     */
    public void magicArrCreator(String path) throws FileNotFoundException {
        Scanner magSc = new Scanner(new File(path));
        String magInfo;
        while (magSc.hasNextLine()) {
            magInfo = magSc.nextLine();

            String findName = "";
            String findMagCode = "";
            int findMagMin = 0;
            int findMagMax = 0;
            int index = magInfo.indexOf((char) 9);
            for (int j = 0; j < 4; j++) {

                if (j == 0) {
                    findName = magInfo.substring(0, index);
                } else if (j == 1) {
                    findMagCode = magInfo.substring(0, index);
                } else if (j == 2) {
                    findMagMin = Integer.parseInt(magInfo.substring(0, index));
                } else {
                    findMagMax = Integer.parseInt(magInfo.substring(0, magInfo.length()));
                }
                magInfo = magInfo.substring(index + 1, magInfo.length());
                index = magInfo.indexOf((char) 9);
            }
            Magic cur = new Magic(findName, findMagCode, findMagMin, findMagMax);
            if (path.equals("MagicPrefix.txt")) {
                this.magicPrefixces.add(cur);
                this.magicPreSize++;
            } else if (path.equals("MagicSuffix.txt")) {
                this.magicSuffixces.add(cur);
                this.magicSufSize++;
            }
        }
    }

    /**
     * Returns a Magic object at the index specified
     *
     * @param index - The index at which to get the thing
     * @param type - From which array to get the Magic Entry
     * @return Magic - the Magic
     */
    public Magic get(int index, String type) {
        if (type.equals("Suffix")) {
            return magicSuffixces.get(index);
        } else {
            return magicPrefixces.get(index);
        }
    }
}
