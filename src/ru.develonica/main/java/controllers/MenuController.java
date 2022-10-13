package controllers;

import models.FileTree;
import models.MenuButton;
import util.FileTreeBuilder;

import java.nio.file.Path;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    public static FileTree handleMenu(FileTree tree, MenuButton button) {
        Path parent = tree.getTreePath().getParent();
        if (button == MenuButton.B) {
            return FileTreeBuilder.build(parent.toString());
        }
        return tree;
    }
}
