package ru.develonica.model.service.sizehandler.impl;

import ru.develonica.model.service.sizehandler.SizeHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Sync {@link SizeHandler} implementation.
 */
public class SyncSizeHandler implements SizeHandler {

    @Override
    public long calculateDestinationSize(File destination) throws IOException {
        File[] files = destination.listFiles();
        long filesSize = 0L;
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    filesSize += Files.size(file.toPath());
                } else {
                    filesSize += calculateDestinationSize(file);
                }
            }
        }
        return filesSize;
    }
}