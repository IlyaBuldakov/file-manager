package ru.develonica.model;

/**
 * Messages for user.
 */
public enum Message {

    /**
     * Internal application error.
     */
    INTERNAL_ERROR("Internal error."),

    /**
     * Input/output error.
     */
    IO_ERROR("Input/output error. Perhaps the file does not exist or the path is incorrect."),

    /**
     * Out of bounds error.
     * For example, when user enters -1 as input.
     */
    OUT_OF_BOUNDS("Incorrect number. Please, check your input."),

    ILLEGAL_ARGUMENT("Incorrect input. Please, try again."),

    FILE_NOT_FOUND("File not found.");

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