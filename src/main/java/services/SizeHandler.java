package main.java.services;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Interface describing the component that is
 * responsible for determining the size of the directory.
 */
public interface SizeHandler {

    /**
     * Method that forces the size handler
     * to calculate the size of the directory.
     *
     * @return Directory size.
     */
    float activate(File destination) throws IOException, ExecutionException, InterruptedException;
}
