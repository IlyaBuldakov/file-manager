package main.java.services;

import main.java.models.Measure;
public class SizeConverter {

    public static String convert(float input) {
        // Division by 1024 counter.
        int counter = 0;
        while (input > 1024) {
            input /= 1024;
            counter++;
        }
        return String.format("%.2f " + Measure.values()[counter], input);
    }
}
