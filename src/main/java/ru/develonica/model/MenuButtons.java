package ru.develonica.model;

/**
 * Enum-representations of menu buttons.
 */
public enum MenuButtons {

    /**
     * Back (from file tree) menu button representation.
     */
    BACK_BUTTON('B'),

    /**
     * Create menu button representation.
     */
    CREATE_BUTTON('+'),

    /**
     * Delete menu button representation.
     */
    DELETE_BUTTON('-');

    private final char symbol;

    MenuButtons(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
