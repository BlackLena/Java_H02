package org.huntler;

import java.io.FileNotFoundException;

public class Main {

    public static void Main(String[] args) {
        try {
            GoldPreis gp = new GoldPreis("gold.txt");
            System.out.println("" + gp.getPreis("2009-10-20"));
            System.out.println("" + gp.getPreis("2009-02-07"));
            System.out.println("" + gp.getPreis("2009-02-07"));
            // System.out.println(gp.toString());
            gp.printMinMax();
            gp.printMinMax();
            System.out.println("Gewollte Exception:");
            System.out.println("" + gp.getPreis("2018-10-12"));

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
