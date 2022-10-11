package main.java.views;

import main.java.models.Information;
import main.java.services.SizeConverter;
import main.java.util.FileCalculator;

import java.io.File;

public class InformationView {

    public static final String WHITE_BRIGHT = "\033[0;97m";

    /**
     * Method which prints information in console.
     *
     * @param file File corresponding {@link Information information}
     *             instance (for calculating the size).
     * @param info Information instance.
     * @param number Sequence number.
     */
    public static void print(Information info, File file, int number) {
        String firstPart = number + " | " + "Name: " + info.getObjName() + " | Type: " + info.getType() + " | Size: ";
        String secondPart = " | Count: " + info.getCount();
        String sb = firstPart +
                info.getSize() +
                secondPart;
        System.out.print(sb + WHITE_BRIGHT);
        System.out.println("\r" + firstPart + SizeConverter.convert(FileCalculator.calculateSize(file)) + secondPart);
    }
}