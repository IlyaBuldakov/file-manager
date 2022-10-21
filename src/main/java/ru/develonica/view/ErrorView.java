package ru.develonica.view;

import ru.develonica.model.Message;

import java.io.IOException;
import java.nio.file.Path;

/**
 * View that displays errors.
 */
public final class ErrorView {

    public void proceed(Exception exception) {
        switch (exception) {
            case IndexOutOfBoundsException outOfBounds -> System.err.println(Message.OUT_OF_BOUNDS.getText());
            case IOException io -> System.err.println(Message.IO_ERROR.getText());
            default -> System.err.println(Message.INTERNAL_ERROR.getText());
        }
    }

    public void proceedNotFoundPath(Path path) {
        if (path.toFile().exists()) {
            return;
        }
        System.err.println(Message.FILE_NOT_FOUND.getText());
    }
}
