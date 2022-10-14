package controllers;

import models.FileTree;
import util.FileTreeBuilder;

import java.nio.file.Path;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    private static final char BACK_BUTTON = 'B';

    /**
     * Menu handle method.
     *
     * @param tree File tree.
     * @param inputButton Button from user input.
     * @return New file tree.
     */
    public static FileTree handleMenu(FileTree tree, char inputButton) {
        Path parent = tree.getTreePath().getParent();
        if (Character.toUpperCase(inputButton) == BACK_BUTTON) {
            return FileTreeBuilder.build(parent.toString());
        }
        return tree;
    }
}
