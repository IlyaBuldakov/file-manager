package ru.develonica.util;

import ru.develonica.models.Message;
import ru.develonica.services.SizeHandler;
import ru.develonica.services.ThreadPoolSizeHandler;
import ru.develonica.views.ErrorView;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

/**
 * Utility class for work with files.
 */
public final class FileCalculator {

    private static final SizeHandler sizeHandler = new ThreadPoolSizeHandler();

    private static final ErrorView errorView = new ErrorView();

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
                    errorView.proceed(Message.INTERNAL_ERROR);
                }
            }
            return Files.size(file.toPath());
        } catch (IOException exception) {
            errorView.proceed(Message.IO_ERROR);
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