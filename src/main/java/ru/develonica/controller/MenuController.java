package ru.develonica.controller;

import ru.develonica.model.FileTree;
import ru.develonica.util.FileTreeBuilder;
import ru.develonica.view.ErrorView;
import ru.develonica.view.MenuOperationsView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    private static final char BACK_BUTTON = 'B';

    private static final char CREATE_BUTTON = '+';

    private static final char DELETE_BUTTON = '-';

    private final MenuOperationsView menuOperationsView = new MenuOperationsView();

    private final ErrorView errorView = new ErrorView();

    /**
     * Menu handle method.
     *
     * @param tree        File tree.
     * @param inputButton Button from user input.
     * @return New file tree.
     */
    public FileTree handleMenu(FileTree tree, char inputButton) throws IOException, NumberFormatException {
        Path parent = tree.getTreePath().getParent();
        switch (Character.toUpperCase(inputButton)) {
            case BACK_BUTTON:
                return FileTreeBuilder.build(parent.toString());
            case CREATE_BUTTON:
                this.menuOperationsView.enterNameForNewObj();
                String dirName = new Scanner(System.in).nextLine();
                Path path = Path.of(tree.getTreePath().toString() + "/" + dirName);
                Files.createDirectories(path);
                this.menuOperationsView.createSuccess();
                // Rebuild tree with new directory.
                return FileTreeBuilder.build(tree.getTreePath().toString());
            case DELETE_BUTTON:
                this.menuOperationsView.enterIdToDelete();
                int id = Integer.parseInt(new Scanner(System.in).nextLine());
                Path pathToDelete = tree.getTree().get(id - 1).getPath();
                if (Files.deleteIfExists(pathToDelete)) {
                    this.menuOperationsView.deleteSuccess();
                } else {
                    this.errorView.proceedNotFoundPath(pathToDelete);
                }
                // Rebuild tree without deleted dir/file.
                return FileTreeBuilder.build(tree.getTreePath().toString());
        }
        return tree;
    }
}