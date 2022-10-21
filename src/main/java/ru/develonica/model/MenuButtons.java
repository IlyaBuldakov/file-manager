package ru.develonica.model;

public enum MenuButtons {

    BACK_BUTTON('B'),

    CREATE_BUTTON('+'),

    DELETE_BUTTON('-');

    private final Character symbol;

    MenuButtons(char symbol) {
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }
}
