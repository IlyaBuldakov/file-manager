package ru.develonica.service;

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
    double activate(File destination) throws Exception;
}
