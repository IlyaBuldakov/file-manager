package ru.develonica.services;

/**
 * Converter to KB/MB/GB/TB (or BYTES, if value less than 1024).
 */
public class SizeConverter {

    public static final String[] MEASURES = new String[] {
            "BYTES", "KB", "MB", "GB", "TB"
    };

    /**
     * Convert method.
     *
     * @param input Value to convert.
     * @return Converted string.
     */
    public static String convert(float input) {
        // Division by 1024 counter.
        int counter = 0;
        while (input > 1024) {
            input /= 1024;
            counter++;
        }
        return String.format("%.2f " + MEASURES[counter], input);
    }
}
