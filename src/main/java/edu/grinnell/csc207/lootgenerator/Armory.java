/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to 
 * change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author janewaya
 */
public class Armory {

    /**
     * Creates an Armor class, allowing for Armor objects to be created
     *
     */
    public static class Armor {

        private String name;
        private int defenseValue;

        /**
         * Creates an armor object
         *
         * @param name - The name of the armor
         * @param minVal - The lowest possible defense value
         * @param maxVal - The maximum possible defense value
         */
        public Armor(String name, int minVal, int maxVal) {
            this.name = name;
            if (minVal != maxVal) {
                Random defChoser = new Random();
                this.defenseValue = defChoser.nextInt(maxVal - minVal) + minVal;
            } else {
                this.defenseValue = minVal;
            }
        }

        /**
         * Returns the name of the armor object
         *
         * @return String - Returns the name of the object
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the defense value of the armor object
         *
         * @return int - Returns the defense value of the object
         */
        public int getDefVal() {
            return this.defenseValue;
        }
    }

    public Armor[] allArmor;
    public int size;

    /**
     * Creates an object that contains a HashMap which stores armor objects
     *
     */
    public Armory() throws FileNotFoundException {
        Scanner armorLen = new Scanner(new File("armor.txt"));
        int len = 0;
        while (armorLen.hasNextLine()) {
            armorLen.nextLine();
            len++;
            this.size++;
        }

        allArmor = new Armor[len];

        Scanner armorSc = new Scanner(new File("armor.txt"));
        String armScInfo;
        for (int i = 0; i < len; i++) {
            armScInfo = armorSc.nextLine();

            String name = "";
            int minVal = 0;
            int maxVal = 0;
            int index = armScInfo.indexOf((char) 9);
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    name = armScInfo.substring(0, index);
                } else if (j == 1) {
                    minVal = Integer.parseInt(armScInfo.substring(0, index));
                } else {
                    maxVal = Integer.parseInt(armScInfo.substring(0, armScInfo.length()));
                }
                armScInfo = armScInfo.substring(index + 1, armScInfo.length());
                index = armScInfo.indexOf((char) 9);
            }

            put(name, minVal, maxVal, len);
        }
    }

    /**
     * Adds an armor object to the HashMap in the Armory object
     *
     * @param name - The name of the object to be stored
     * @param minVal - The minimum defense value of the object to be stored
     * @param maxVal - The maximum defense value of the object to be stored
     * @param len - The length of the array
     */
    public void put(String name, int minVal, int maxVal, int len) {
        int index = Math.abs(name.hashCode() % len);
        if (allArmor[index] == null) {
            allArmor[index] = new Armor(name, minVal, maxVal);
        } else {
            while (allArmor[index] != null) {
                index++;
                if (index == len) {
                    index = 0;
                }
            }
            allArmor[index] = new Armor(name, minVal, maxVal);
        }
    }

}
