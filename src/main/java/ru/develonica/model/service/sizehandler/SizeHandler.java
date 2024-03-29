package ru.develonica.model.service.sizehandler;

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
    long calculateDestinationSize(File destination) throws IOException;
}
