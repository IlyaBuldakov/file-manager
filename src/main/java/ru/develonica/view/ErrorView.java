package ru.develonica.view;

import ru.develonica.model.Message;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * View that displays errors.
 */
public final class ErrorView {

    /**
     * Method outputs error message by resolving exception.
     *
     * @param throwable Exception input.
     */
    public void proceed(Throwable throwable) {
        switch (throwable) {
            case IndexOutOfBoundsException outOfBounds -> System.err.println(Message.OUT_OF_BOUNDS.getText());
            case FileNotFoundException fileNotFound -> System.err.println(Message.FILE_NOT_FOUND.getText());
            case IllegalArgumentException illegalArg -> System.err.println(Message.ILLEGAL_ARGUMENT.getText());
            case IOException io -> System.err.println(Message.IO_ERROR.getText());
            default -> System.err.println(Message.INTERNAL_ERROR.getText());
        }
    }
}
