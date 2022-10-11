package main.java.models;

/**
 * Messages for user.
 */
public enum Message {

    INTERNAL_ERROR("Internal error."),

    IO_ERROR("Input/output error. Perhaps the file does not exist or the path is incorrect.");

    private final String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}