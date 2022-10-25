package ru.develonica.model;

/**
 * Enum-representations of menu buttons.
 */
public enum MenuButton {

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

    MenuButton(char symbol) {
        this.symbol = symbol;
    }

    public static MenuButton valueOf(char value) {
        return switch (value) {
            case 'B' -> BACK_BUTTON;
            case '+' -> CREATE_BUTTON;
            case '-' -> DELETE_BUTTON;
            default -> null;
        };
    }

    public char getSymbol() {
        return symbol;
    }
}
