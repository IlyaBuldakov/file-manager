package main.java.services;

import main.java.models.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SyncSizeHandler implements SizeHandler {
    @Override
    public float activate(File destination) {
        float sizeInCurrentDir = 0;
        try {
            if (destination.isFile()) {
                sizeInCurrentDir += Files.size(destination.toPath());
            }
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
            System.err.println(Message.IO_ERROR.getText());
        }
        return sizeInCurrentDir;
    }
}
