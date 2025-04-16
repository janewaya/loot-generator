/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
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

    /**
     * Creates an object that contains a HashMap which stores armor objects
     *
     */
    public Armory() throws FileNotFoundException {
        allArmor = new Armor[202];

        Scanner armorSc = new Scanner(new File("armor.txt"));
        String ArmScInfo;
        for (int i = 0; i < 202; i++) {
            ArmScInfo = armorSc.nextLine();

            String name = "";
            int minVal = 0;
            int maxVal = 0;
            int index = ArmScInfo.indexOf((char) 9);
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    name = ArmScInfo.substring(0, index);
                } else if (j == 1) {
                    minVal = Integer.parseInt(ArmScInfo.substring(0, index));
                } else {
                    maxVal = Integer.parseInt(ArmScInfo.substring(0, ArmScInfo.length()));
                }
                ArmScInfo = ArmScInfo.substring(index + 1, ArmScInfo.length());
                index = ArmScInfo.indexOf((char) 9);
            }

            put(name, minVal, maxVal);
        }
    }

    /**
     * Adds an armor object to the HashMap in the Armory object
     *
     * @param name - The name of the object to be stored
     * @param minVal - The minimum defense value of the object to be stored
     * @param maxVal - The maximum defense value of the object to be stored
     */
    public void put(String name, int minVal, int maxVal) {
        int index = Math.abs(name.hashCode() % 202);
        if (allArmor[index] == null) {
            allArmor[index] = new Armor(name, minVal, maxVal);
        } else {
            while (allArmor[index] != null) {
                index++;
                if (index == 202) {
                    index = 0;
                }
            }
            allArmor[index] = new Armor(name, minVal, maxVal);
        }
    }

}
