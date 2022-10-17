package views;

import models.Message;

/**
 * View that displays errors.
 */
public class ErrorView {

    public static void displayError(Message message) {
        System.err.println(message.getText());
    }
}
