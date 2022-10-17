package ru.develonica.views;

import ru.develonica.models.Message;

/**
 * View that displays errors.
 */
public class ErrorView {

    public static void displayError(Message message) {
        System.err.println(message.getText());
    }
}
