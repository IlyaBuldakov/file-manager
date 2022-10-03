package main.java.services;

import main.java.models.Measure;

import java.util.Map;

public class SizeConverter {

    public static Map<String, Measure> convert(float input) {
        // Division by 1024 counter.
        int counter = 0;
        while (input > 1024) {
            input /= 1024;
            counter++;
        }
        return Map.of(String.format("%.2f", input), Measure.values()[counter]);
    }
}
