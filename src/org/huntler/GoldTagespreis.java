package org.huntler;

import java.util.HashMap;
import java.util.Map;

public class GoldTagespreis {

    /**
     * should be a date
     */
    public String datum;

    /**
     * should be the gold value
     */
    public double wert;

    /**
     * The constructor is used to set the parameters once.
     * @param d is the date parameter
     * @param w is the value parameter
     */
    public GoldTagespreis(String d, double w) {
        datum = d;
        wert = w;
    }

    /**
     * A custom toString()
     * @return returns the date and value in a readable format.
     */
    public String toString() {
        return "{ " + datum + " - " + wert + " }";
    }
}
