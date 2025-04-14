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

    public static class TreasureEntry {

        String TC;
        String[] options;
        TreasureEntry right;
        TreasureEntry left;

        public TreasureEntry(String TC, String[] options, TreasureEntry right, TreasureEntry left) {
            TC = this.TC;
            options = this.options;
            right = this.right;
            left = this.left;
        }

        public TreasureEntry(String TC, String[] options) {
            this.TC = TC;
            this.options = options;
            this.right = null;
            this.left = null;
        }
    }

    private TreasureEntry start;

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

    private String[] getOptionsH(String name, TreasureEntry cur, String[] exists) {

        if (cur == null) {
            return null;
        } else if (name.equals(cur.TC)) {
            return getOptionsH(name, cur.left, cur.options);
        } else if (name.equals(cur.TC)) {
            return getOptionsH(name, cur.right, cur.options);
        } else {
            return exists;
        }
    }

    public String[] getOptions(String name) {
        return getOptionsH(name, start, null);
    }

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

    public void put(String name, String[] options) {
        start = putH(name, options, start);
    }

    /**
     * @return a string representation of the tree obtained via an pre-order
     * traversal in the form: "[v0, v1, ..., vn]"
     */
    public String keys() {
        StringBuffer buf = new StringBuffer("[");
        buf = keysH(start, start, buf);
        buf.append("]");
        return buf.toString();
    }

    public StringBuffer keysH(TreasureEntry cur, TreasureEntry start, StringBuffer buf) {
        if (cur != null) {
            if (cur.equals(start)) {
                buf.append(cur.TC);
            } else {
                buf.append(", " + cur.TC);
            }
            if (cur.left != null) {
                buf = keysH(cur.left, start, buf);
            }
            if (cur.right != null) {
                buf = keysH(cur.right, start, buf);
            }
        }
        return buf;
    }

    /**
     * @return a string representation of the tree obtained via an pre-order
     * traversal in the form: "[v0, v1, ..., vn]"
     */
    public String values() {
        StringBuffer buf = new StringBuffer("[");
        buf = valuesH(start, start, buf);
        buf.append("]");
        return buf.toString();
    }

    public StringBuffer valuesH(TreasureEntry cur, TreasureEntry root, StringBuffer buf) {
        if (cur != null) {
            if (cur.equals(root)) {
                for (int i = 0; i < 3; i++) {
                    buf.append(i + ": " + cur.options[i]);
                }
            } else {
                buf.append(", ");
                for (int i = 0; i < 3; i++) {
                    buf.append(i + ": " + cur.options[i]);
                }
            }
            if (cur.left != null) {
                buf = valuesH(cur.left, root, buf);
            }
            if (cur.right != null) {
                buf = valuesH(cur.right, root, buf);
            }
        }
        return buf;
    }

}
