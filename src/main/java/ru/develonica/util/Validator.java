package ru.develonica.util;

import ru.develonica.model.FileTree;
import ru.develonica.model.MenuButton;

/**
 * Class that validates data.
 */
public final class Validator {

    /**
     * Checks whether the id is in the
     * range of the file tree list.
     *
     * @param fileTree {@link FileTree}.
     * @param id Identifier.
     * @return Boolean.
     */
    public static boolean isObjectIdValid(FileTree fileTree, int id) {
        return id <= fileTree.getTree().size() && id > 0;
    }

    /**
     * Checks whether the input is a menu button
     *
     * @param input Input string.
     * @return Boolean.
     */
    public static boolean isMenuButton(String input) {
        return input.length() == 1
                && (Character.isAlphabetic(input.charAt(0))
                || input.charAt(0) == MenuButton.CREATE_BUTTON.getSymbol()
                || input.charAt(0) == MenuButton.DELETE_BUTTON.getSymbol());
    }
}
