package ru.develonica.service;

import java.io.File;
import java.io.IOException;

/**
 * Interface describing the size handler.
 */
public interface SizeHandler {

    /**
     * Size handler activation method.
     *
     * @param destination Destination to be processed.
     * @return Size value.
     * @throws IOException Exception.
     */
    long activate(File destination) throws IOException;
}
