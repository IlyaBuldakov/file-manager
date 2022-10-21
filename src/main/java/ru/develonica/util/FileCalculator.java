package ru.develonica.util;

import ru.develonica.models.Message;
import ru.develonica.models.ThreadPoolHolder;
import ru.develonica.services.SizeHandler;
import ru.develonica.services.SyncSizeHandler;
import ru.develonica.services.ThreadPoolSizeHandler;
import ru.develonica.views.ErrorView;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * Utility class for work with files.
 */
public final class FileCalculator {

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
    public static float calculateSize(File file) {
        try {
            if (file.isDirectory()) {
                try {
                    return SIZE_HANDLER.activate(file);
                } catch (Exception exception) {
                    ERROR_VIEW.proceed(exception);
                }
            }
            return Files.size(file.toPath());
        } catch (IOException exception) {
            ERROR_VIEW.proceed(exception);
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