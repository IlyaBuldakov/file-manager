package ru.develonica.model.service;

/**
 * Converter to KB/MB/GB/TB (or BYTES, if value less than 1024).
 */
public final class SizeConverter {

    public static final String[] MEASURES = new String[] {
            "BYTES", "KB", "MB", "GB", "TB"
    };

    /**
     * Convert method.
     *
     * @param input Value to convert.
     * @return Converted string.
     */
    public String convert(long input) {
        // Division by 1024 counter
        int counter = 0;
        // Division step. Examples with input 2048:
        // 1024 / capacity = 1 (MB)
        // 2048 / capacity = 2 (MB)
        int capacity = 1024;
        while (input > capacity) {
            input /= capacity;
            counter++;
        }
        return String.format("%d %s ", input, MEASURES[counter]);
    }
}
