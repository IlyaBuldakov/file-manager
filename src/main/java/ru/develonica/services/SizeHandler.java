package ru.develonica.services;

import java.io.File;

/**
 * Interface describing the size handler.
 */
public interface SizeHandler {

    /**
     * Size handler activation method.
     *
     * @param destination Destination to be processed.
     * @return Size value.
     * @throws Exception Exception.
     */
    float activate(File destination) throws Exception;
}
