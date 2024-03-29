package ru.develonica.controller;

import ru.develonica.model.info.FileTree;
import ru.develonica.model.MenuButton;
import ru.develonica.model.service.operation.CreateOperation;
import ru.develonica.model.service.operation.DeleteOperation;
import ru.develonica.util.Validator;
import ru.develonica.view.ErrorView;
import ru.develonica.view.MenuOperationsView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Controller that is responsible for
 * user interaction using the menu.
 */
public class MenuController {

    private static final String HOME_PATH = System.getProperty("user.home");

    private final MenuOperationsView menuOperationsView;

    private final ErrorView errorView;

    private final DeleteOperation deleteOperation;

    private final CreateOperation createOperation;

    public MenuController(MenuOperationsView menuOperationsView,
                          ErrorView errorView, DeleteOperation deleteOperation, CreateOperation createOperation) {
        this.menuOperationsView = menuOperationsView;
        this.errorView = errorView;
        this.deleteOperation = deleteOperation;
        this.createOperation = createOperation;
    }

    /**
     * Menu handle method.
     *
     * @param tree        File tree.
     * @param inputButton Button from user input.
     * @return New file tree.
     */
    public FileTree handleMenu(FileTree tree, char inputButton) throws IOException {
        try {
            final char upperCaseInput = Character.toUpperCase(inputButton);
            MenuButton button = MenuButton.valueOf(upperCaseInput);
            Path parent = tree.getTreePath().getParent();
            switch (button) {
                case BACK_BUTTON -> {
                    return FileTree.build(parent.toString());
                }
                case CREATE_BUTTON -> {
                    this.menuOperationsView.enterNameForNewObj();
                    String dirName = new Scanner(System.in).nextLine();
                    this.createOperation.handle(tree, dirName);
                    this.menuOperationsView.createSuccess();
                    // Rebuild tree with new directory.
                    return FileTree.build(tree.getTreePath().toString());
                }
                case DELETE_BUTTON -> {
                    this.menuOperationsView.enterIdToDelete();
                    int id = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (Validator.isObjectIdValid(tree, id)) {
                        this.deleteOperation.handle(tree, id);
                        this.menuOperationsView.deleteSuccess();
                        // Rebuild tree without deleted dir/file.
                        return FileTree.build(tree.getTreePath().toString());
                    } else {
                        this.errorView.proceed(new FileNotFoundException());
                    }
                }
            }
            return tree;
        } catch (Exception exception) {
            this.errorView.proceed(exception);
        }
        return FileTree.build(HOME_PATH);
    }
}