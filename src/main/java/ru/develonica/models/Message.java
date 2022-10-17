package ru.develonica.models;

/**
 * Messages for user.
 */
public enum Message {

    INTERNAL_ERROR("Internal error."),

    IO_ERROR("Input/output error. Perhaps the file does not exist or the path is incorrect."),

    SIZE_HANDLER_ERROR("Size handler error."),

    OUT_OF_BOUNDS("Incorrect number. Please, check your input.");

    /**
     * Error contents.
     */
    private final String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}