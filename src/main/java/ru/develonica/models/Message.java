package ru.develonica.models;

/**
 * Messages for user.
 */
public enum Message {

    INTERNAL_ERROR("Internal error."),

    IO_ERROR("Input/output error. Perhaps the file does not exist or the path is incorrect."),

    OUT_OF_BOUNDS("Incorrect number. Please, check your input."),

    FILE_NOT_FOUND("File not found. Please, try another one.");

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