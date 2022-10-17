package ru.develonica.views;

import ru.develonica.models.Information;
import ru.develonica.models.Type;
import ru.develonica.services.SizeConverter;

public class InformationView {

    /**
     * Method which prints information in console.
     *
     * @param info   Information instance.
     * @param number Sequence number.
     */
    public static void print(Information info, int number) {
        String objName = info.getObjName();
        Type type = info.getType();
        String size = SizeConverter.convert(info.getSize());
        int count = info.getCount();
        String object =
                number
                + " | Name: " + objName
                + " \\ Type: " + type
                + " \\ Size: " + size
                + " \\ Count: " + count;

        System.out.println("\r" + object);
    }
}