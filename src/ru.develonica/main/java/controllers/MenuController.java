package controllers;

import models.FileTree;
import util.FileTreeBuilder;

import java.nio.file.Path;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    private final static char BACK_BUTTON = 'B';

    public static FileTree handleMenu(FileTree tree, char inputButton) {
        Path parent = tree.getTreePath().getParent();
        if (inputButton == BACK_BUTTON) {
            return FileTreeBuilder.build(parent.toString());
        }
        return tree;
    }
}
