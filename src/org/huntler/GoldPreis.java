package org.huntler;

import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class GoldPreis {

    // used to hold all GoldTagespreis datasets
    private ArrayList<GoldTagespreis> list;

    // used to store min/max data
    private GoldTagespreis minValue ;
    private GoldTagespreis maxValue;

    /**
     * The constructor reads a file of data into a GoldTagespreis list.
     * @param dateiname should be a file containing some dates and values
     *                  separated by a \t
     * @throws FileNotFoundException will be thrown if there was an invalid
     * file given or if the user does not have enough rights to open the
     * given file.
     */
    public GoldPreis(String dateiname) throws FileNotFoundException {
        list = new ArrayList<>();

        // open a new file
        File f = new File(dateiname);

        // read the opened file if possible
        // otherwise GoldPreis() will throw a FileNotFoundException
        Scanner sc = new Scanner(f);

        // set min/max value to their opposit
        minValue = new GoldTagespreis("", Double.MAX_VALUE);
        maxValue = new GoldTagespreis("", Double.MIN_VALUE);

        // iterate through the file using a scanner
        while (sc.hasNext()) {
            // split a line into a date and a value
            String[] v = sc.nextLine().split("\t");
            double value = tryParse(v[1]);

            // initialize a new GoldTagespreis object
            GoldTagespreis gtp = new GoldTagespreis(v[0], value);

            // set the minimum value
            if (value != -1 && value < minValue.wert) {
                minValue.wert = value;
                minValue.datum = v[0];
            }

            // set the maximum value
            if (value != -1 && value > maxValue.wert) {
                maxValue.wert = value;
                maxValue.datum = v[0];
            }

            list.add(gtp);
        }
    }

    /**
     * Iterates through a list of GoldTagespreis.
     * @param datum should be a valid date. If not a NumberFormatException will be thrown.
     * @return returns the value of datum.
     * @throws NumberFormatException in case of a invalid date.
     */
    public double getPreis(String datum) {
        // iterate through the list and return a value that belongs to datum
        for (GoldTagespreis gtp : list) {
            if (gtp.datum.equals(datum)) {
                return gtp.wert;
            }
        }

        // if there was no value found, then throw an exception
        throw new NumberFormatException();
    }

    /**
     * Prints the maximum and minimum value of GoldTagespreis and
     * depending on it every day.
     */
    public void printMinMax() {
        String min = "";
        String max = "";

        // iterate through the list and append every date fitting to the min/max value
        for (GoldTagespreis gp : list) {
            if (gp.wert == minValue.wert) {
                min += minValue.datum + "\n";
            } else if (gp.wert == maxValue.wert) {
                max += maxValue.datum + "\n";
            }
        }

        // print out everything
        System.out.printf("Den niedrigsten Goldpreis von %f gab es an den folgenden Tagen:\n", minValue.wert);
        System.out.print(min);
        System.out.printf("Den hoechsten Goldpreis von %f gab es an den folgenden Tagen:\n", maxValue.wert);
        System.out.print(max);

    }

    // parses a string s to a double if possible
    // if not tryParse() returns -1
    private double tryParse(String s) {
        try {
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
            return nf.parse(s.replaceAll("[.]", "")).doubleValue();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * A custom toString()
     * @return returns a string including 'GoldTagespreis'
     */
    public String toString() {
        String s = "";
        for (GoldTagespreis g : list) {
            s += g.toString() + "\n";
        }
        return s;
    }
}