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
public class Monsters {

    public static class Monster {

        private String monClass;
        private String monType;
        int monLevel;
        String monTreasureClass;

        public Monster(String monClass, String monType, int monLevel, String monTreasureClass) {
            this.monClass = monClass;
            this.monType = monType;
            this.monLevel = monLevel;
            this.monTreasureClass = monTreasureClass;
        }
        
        public String getMonTreasureClass(){
            return this.monTreasureClass;
        }
        
        @Override
        public String toString() {
            String quakeInFear = this.monClass;
            return quakeInFear;
        }

        public String toStringGood() {
            String quakeInFear = "Name: " + this.monClass
                    + "\nType: " + this.monType
                    + "\nLevel: " + this.monLevel
                    + "\nTreasureClass: " + this.monTreasureClass;
            return quakeInFear;
        }
    }

    Monster[] monArr;

    public Monsters() throws FileNotFoundException {
        this.monArr = new Monster[49];

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
            this.monArr[i] = cur;
        }

    }

    public Monster getMonster() {
        Random monsterEncounter = new Random();
        int jumpScare = monsterEncounter.nextInt(49);
        return monArr[jumpScare];
    }

}
