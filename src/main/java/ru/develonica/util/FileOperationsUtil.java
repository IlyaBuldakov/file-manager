package ru.develonica.util;

import ru.develonica.model.ThreadPoolHolder;
import ru.develonica.service.SizeHandler;
import ru.develonica.service.SyncSizeHandler;
import ru.develonica.service.ThreadPoolSizeHandler;
import ru.develonica.view.ErrorView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * Utility class for work with files.
 */
public final class FileOperationsUtil {

    /*
      Initializing size handler.
     */
    static {
        Optional<ExecutorService> threadPool = ThreadPoolHolder.getInstance();
        if (threadPool.isPresent()) {
            SIZE_HANDLER = new ThreadPoolSizeHandler(threadPool.get());
        } else {
            SIZE_HANDLER = new SyncSizeHandler();
        }
    }

    private static final SizeHandler SIZE_HANDLER;

    private static final ErrorView ERROR_VIEW = new ErrorView();

    /**
     * Size calculation method.
     *
     * @param file File.
     * @return Size.
     */
    public static long calculateSize(File file) {
        try {
            if (file.isDirectory()) {
                return SIZE_HANDLER.activate(file);
            }
            return Files.size(file.toPath());
        } catch (Exception exception) {
            ERROR_VIEW.proceed(exception);
        }
        return 0;
    }

    /**
     * Method that calculates count of objects in directory.
     *
     * @param file Destination to be calculated.
     * @return Count of objects in directory.
     */
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
     * Method that opens file.
     *
     * @param file File to be opened.
     * @throws IOException Exception.
     */
    public static void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}