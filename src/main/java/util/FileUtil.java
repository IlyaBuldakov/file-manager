package main.java.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for work with files.
 */
public class FileUtil {

    public static void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
