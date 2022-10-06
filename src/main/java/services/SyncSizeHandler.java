package main.java.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SyncSizeHandler implements SizeHandler{
    @Override
    public float activate(File destination) {
        float sizeInCurrentDir = 0;
        try {
            File[] files = destination.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        sizeInCurrentDir += Files.size(file.toPath());
                    } else {
                        sizeInCurrentDir += activate(file);
                    }
                }
            }
        } catch (IOException exception) {
            System.err.println("Ошибка");
        }
        return sizeInCurrentDir;
    }
}
