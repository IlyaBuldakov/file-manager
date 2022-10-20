package ru.develonica.views;

import ru.develonica.models.Message;

/**
 * View that displays errors.
 */
public final class ErrorView {

    public void proceed(Message message) {
        System.err.println(message.getText());
    }
}
