package main.java.util;

import main.java.models.Message;
import main.java.services.SizeHandler;
import main.java.services.ThreadPoolSizeHandler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Utility class for work with files.
 */
public class FileCalculator {

    private static SizeHandler sizeHandler = new ThreadPoolSizeHandler();

    public static float calculateSize(File file) {
        try {
            if (file.isDirectory()) {
                return sizeHandler.activate(file);
            }
            return Files.size(file.toPath());
        } catch (IOException exception) {
            System.err.println(Message.IO_ERROR.getText());
        }
        return 0;
    }

    public static int calculateCount(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                return files.length;
            } else {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Setter for size handler implementation.
     *
     * @param sizeHandler Size handler implementation.
     */
    public static void setSizeHandler(SizeHandler sizeHandler) {
        FileCalculator.sizeHandler = sizeHandler;
    }

    public static void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
