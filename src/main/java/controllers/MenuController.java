package main.java.controllers;

import main.java.models.FileTree;
import main.java.models.MenuButton;
import main.java.services.FileTreeBuilder;

import java.nio.file.Path;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    public static FileTree handleMenu(FileTree tree, MenuButton button) {
        Path parent = tree.getTreePath().getParent();
        if (button == MenuButton.B) {
            FileTree fileTree = FileTreeBuilder.build(parent.toString());
            fileTree.displayView();
            return fileTree;
        }
        return tree;
    }
}
