package ru.develonica.service;

import java.io.File;
import java.nio.file.Files;

/**
 * Sync {@link SizeHandler} implementation.
 */
public class SyncSizeHandler implements SizeHandler {

    @Override
    public float activate(File destination) throws Exception {
        File[] files = destination.listFiles();
        float filesSize = 0f;
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    filesSize += Files.size(file.toPath());
                } else {
                    filesSize += activate(file);
                }
            }
        }
        return filesSize;
    }
}