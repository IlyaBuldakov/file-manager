package views;

import models.Information;
import services.SizeConverter;

public class InformationView {

    /**
     * Method which prints information in console.
     *
     * @param info   Information instance.
     * @param number Sequence number.
     */
    public static void print(Information info, int number) {
        String firstPart = number
                + " | Name: " + info.getObjName()
                + " \\ Type: " + info.getType()
                + " \\ Size: ";
        String secondPart =
                " \\ Count: " + info.getCount();
        String sb = firstPart +
                info.getSize() +
                secondPart;
        System.out.print(sb);
        System.out.println("\r" + firstPart + SizeConverter.convert(info.getSize()) + secondPart);
    }
}