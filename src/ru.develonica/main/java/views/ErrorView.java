package views;

import models.Message;

public class ErrorView {

    public static void displayError(Message message) {
        System.err.println(message.getText());
    }
}
