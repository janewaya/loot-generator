/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author janewaya
 */
public class Monsters {

    /**
     * A class that stores the Monster object
     *
     */
    public static class Monster {

        private String monClass;
        private String monType;
        int monLevel;
        String monTreasureClass;

        /**
         * The Monster Object
         *
         * @param monClass The class of the Monster
         * @param monType The Type of Monster (Unnecessary)
         * @param monLevel The level of the Monster (Unnecessary)
         * @param monTreasureClass The TC of the Monster
         */
        public Monster(String monClass, String monType, int monLevel, String monTreasureClass) {
            this.monClass = monClass;
            this.monType = monType;
            this.monLevel = monLevel;
            this.monTreasureClass = monTreasureClass;
        }

        /**
         * Returns the Treasure Class of the Monster
         *
         * @return String - Returns the TC of the Monster
         */
        public String getMonTreasureClass() {
            return this.monTreasureClass;
        }

        /**
         * Returns the Class of the Monster
         *
         * @return String - Returns the class of the Monster
         */
        @Override
        public String toString() {
            String quakeInFear = this.monClass;
            return quakeInFear;
        }
    }

    ArrayList<Monster> monArr;

    /**
     * Creates an ArrayList of Monster Objects from monstats.txt
     *
     */
    public Monsters() throws FileNotFoundException {
        this.monArr = new ArrayList<Monster>();

        Scanner monSc = new Scanner(new File("monstats.txt"));
        String monInfo;
        for (int i = 0; i < 49; i++) {
            monInfo = monSc.nextLine();

            String findMonClass = "";
            String findMonType = "";
            int findMonLevel = 0;
            String findMonTreasureClass = "";
            int index = monInfo.indexOf((char) 9);
            for (int j = 0; j < 4; j++) {

                if (j == 0) {
                    findMonClass = monInfo.substring(0, index);
                } else if (j == 1) {
                    findMonType = monInfo.substring(0, index);
                } else if (j == 2) {
                    findMonLevel = Integer.parseInt(monInfo.substring(0, index));
                } else {
                    findMonTreasureClass = monInfo.substring(0, monInfo.length());
                }
                monInfo = monInfo.substring(index + 1, monInfo.length());
                index = monInfo.indexOf((char) 9);
            }
            Monster cur = new Monster(findMonClass, findMonType, findMonLevel, findMonTreasureClass);
            this.monArr.add(cur);
        }

    }
    
    /**
         * Returns a Monster at the index specified
         *
         * @param index - The index at which to get the Monster
         * @return Monster - the Monster
         */
    public Monster get(int index){
        return monArr.get(index);
    }

}
