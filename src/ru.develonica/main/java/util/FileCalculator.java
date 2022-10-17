package util;

import models.Message;
import services.SizeHandler;
import services.ThreadPoolSizeHandler;
import views.ErrorView;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

/**
 * Utility class for work with files.
 */
public class FileCalculator {

    private static final SizeHandler sizeHandler = new ThreadPoolSizeHandler();

    /**
     * Size calculation method.
     *
     * @param file File.
     * @return Size.
     */
    public static float calculateSize(File file) {
        try {
            if (file.isDirectory()) {
                try {
                    return sizeHandler.activate(file);
                } catch (ExecutionException | InterruptedException e) {
                    ErrorView.displayError(Message.INTERNAL_ERROR);
                }
            }
            return Files.size(file.toPath());
        } catch (IOException exception) {
            ErrorView.displayError(Message.IO_ERROR);
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

    public static void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}