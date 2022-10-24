package ru.develonica.controller;

import ru.develonica.model.FileTree;
import ru.develonica.model.MenuButtons;
import ru.develonica.model.FileTreeBuilder;
import ru.develonica.util.Validator;
import ru.develonica.view.ErrorView;
import ru.develonica.view.MenuOperationsView;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    private static final char BACK_BUTTON = MenuButtons.BACK_BUTTON.getSymbol();

    private static final char CREATE_BUTTON = MenuButtons.CREATE_BUTTON.getSymbol();

    private static final char DELETE_BUTTON = MenuButtons.DELETE_BUTTON.getSymbol();

    private static final String HOME_PATH = System.getProperty("user.home");

    private final MenuOperationsView menuOperationsView;

    private final ErrorView errorView;

    private final FileTreeBuilder fileTreeBuilder;

    public MenuController(MenuOperationsView menuOperationsView,
                          ErrorView errorView, FileTreeBuilder fileTreeBuilder) {
        this.menuOperationsView = menuOperationsView;
        this.errorView = errorView;
        this.fileTreeBuilder = fileTreeBuilder;
    }

    /**
     * Menu handle method.
     *
     * @param tree        File tree.
     * @param inputButton Button from user input.
     * @return New file tree.
     */
    public FileTree handleMenu(FileTree tree, char inputButton) {
        try {
            Path parent = tree.getTreePath().getParent();
            final char upperCaseInput = Character.toUpperCase(inputButton);
            if (upperCaseInput == BACK_BUTTON) {
                return fileTreeBuilder.build(parent.toString());
            }
            if (upperCaseInput == CREATE_BUTTON) {
                this.menuOperationsView.enterNameForNewObj();
                String dirName = new Scanner(System.in).nextLine();
                Path path = Path.of("%s/%s".formatted(tree.getTreePath().toString(), dirName));
                Files.createDirectories(path);
                this.menuOperationsView.createSuccess();
                // Rebuild tree with new directory.
                return this.fileTreeBuilder.build(tree.getTreePath().toString());
            }
            if (upperCaseInput == DELETE_BUTTON) {
                this.menuOperationsView.enterIdToDelete();
                int id = Integer.parseInt(new Scanner(System.in).nextLine());
                if (Validator.isObjectIdValid(tree, id)) {
                    Path pathToDelete = tree.getTree().get(id - 1).getPath();
                    Files.delete(pathToDelete);
                    this.menuOperationsView.deleteSuccess();
                    // Rebuild tree without deleted dir/file.
                    return this.fileTreeBuilder.build(tree.getTreePath().toString());
                } else {
                    this.errorView.proceed(new FileNotFoundException());
                }
            }
            return tree;
        } catch (Exception exception) {
            this.errorView.proceed(exception);
        }
        return this.fileTreeBuilder.build(HOME_PATH);
    }
}