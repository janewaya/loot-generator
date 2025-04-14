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
public class MagicOptions {

    public static class Magic {

        private String name;
        private String magCode;
        private int magVal;

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

        public String getName() {
            return this.name;
        }

        public String getMagCode() {
            return this.magCode;
        }

        public int getMagVal() {
            return this.magVal;
        }

        @Override
        public String toString() {
            String oooohMagic = "Name: " + this.name
                    + "\nCode: " + this.magCode
                    + "\nValue: " + this.magVal;
            return oooohMagic;
        }
    }

    private Magic[] MagicSuffixces;
    private Magic[] MagicPrefixces;

    public MagicOptions() throws FileNotFoundException {
        Random magExists = new Random();
        if (magExists.nextInt(2) == 1) {
            this.MagicSuffixces = new Magic[386];
            magicArrCreator("MagicSuffix.txt");
        } else {
            this.MagicSuffixces = null;
        }
        if (magExists.nextInt(2) == 1) {
            this.MagicPrefixces = new Magic[372];
            magicArrCreator("MagicPrefix.txt");
        } else {
            this.MagicPrefixces = null;
        }
    }

    public void magicArrCreator(String path) throws FileNotFoundException {
        Scanner magSc = new Scanner(new File(path));
        String magInfo;
        int len;
        if(path == "MagicPrefix.txt"){
            len = 372;
        } else {
            len = 386;
        }
        for (int i = 0; i < len; i++) {
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
                this.MagicPrefixces[i] = cur;
            } else if (path.equals("MagicSuffix.txt")) {
                this.MagicSuffixces[i] = cur;
            }
        }
    }

    public Magic getSuffix() {
        if (this.MagicSuffixces != null) {
            Random suffix = new Random();
            int newSuf = suffix.nextInt(386);
            return this.MagicSuffixces[newSuf];
        }
        return null;
    }

    public Magic getPrefix() {
        if (this.MagicPrefixces != null) {
            Random prefix = new Random();
            int newPre = prefix.nextInt(372);
            return MagicPrefixces[newPre];
        }
        return null;
    }

}
