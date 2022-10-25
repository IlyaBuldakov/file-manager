package ru.develonica.view;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * View that displays errors.
 */
public final class ErrorView {

    /**
     * Internal application error.
     */
    private static final String INTERNAL_ERROR = "Internal error.";

    /**
     * Input/output error.
     */
    private static final String IO_ERROR = "Input/output error. Perhaps the file does not exist or the path is incorrect.";

    /**
     * Out of bounds error.
     * For example, when user enters -1 as input.
     */
    private static final String OUT_OF_BOUNDS = "Incorrect number. Please, check your input.";

    /**
     * Illegal argument error.
     */
    private static final String ILLEGAL_ARGUMENT = "Incorrect input. Please, try again.";

    /**
     * File not found error.
     */
    private static final String FILE_NOT_FOUND = "File not found.";

    /**
     * Method outputs error message by resolving exception.
     *
     * @param exception Exception input.
     */
    public void proceed(Exception exception) {
        switch (exception) {
            case IndexOutOfBoundsException outOfBounds -> System.err.println(OUT_OF_BOUNDS);
            case FileNotFoundException fileNotFound -> System.err.println(FILE_NOT_FOUND);
            case IllegalArgumentException illegalArg -> System.err.println(ILLEGAL_ARGUMENT);
            case IOException io -> System.err.println(IO_ERROR);
            default -> System.err.println(INTERNAL_ERROR);
        }
    }
}
