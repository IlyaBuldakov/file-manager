package ru.develonica.controller;

import ru.develonica.model.FileTree;
import ru.develonica.model.Information;
import ru.develonica.model.MenuButtons;
import ru.develonica.model.Type;
import ru.develonica.util.FileOperationsUtil;
import ru.develonica.util.FileTreeBuilder;
import ru.develonica.view.ErrorView;
import ru.develonica.view.FileTreeView;
import ru.develonica.view.GreetingView;
import ru.develonica.view.MenuView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

/**
 * Controller interacting with the user.
 */
public class InputController {

    private static final String EXIT_VALUE = "exit";

    /**
     * Active file tree.
     */
    private FileTree fileTree;

    private final FileTreeView fileTreeView;

    private final GreetingView greetingView;

    private final MenuView menuView;

    private final ErrorView errorView;

    private final MenuController menuController;

    public InputController(FileTree fileTree, FileTreeView fileTreeView, GreetingView greetingView,
                           MenuView menuView, ErrorView errorView, MenuController menuController) {
        this.fileTree = fileTree;
        this.fileTreeView = fileTreeView;
        this.greetingView = greetingView;
        this.menuView = menuView;
        this.errorView = errorView;
        this.menuController = menuController;
    }

    /**
     * Activating the InputController.
     */
    public void start() {
        try {
            this.greetingView.greetingPage();
            refreshOutput();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = br.readLine();
                greetingView.pleaseWait();
                if (input.equalsIgnoreCase(EXIT_VALUE)) {
                    break;
                }
                if (input.length() == 1
                        && (Character.isAlphabetic(input.charAt(0))
                        || input.charAt(0) == MenuButtons.CREATE_BUTTON.getSymbol()
                        || input.charAt(0) == MenuButtons.DELETE_BUTTON.getSymbol())) {
                    this.fileTree = this.menuController.handleMenu(this.fileTree, input.charAt(0));
                    refreshOutput();
                    continue;
                }
                try {
                    int id = Integer.parseInt(input);
                    if (id <= fileTree.getTree().size() && id > 0) {
                        handleIdInput(id);
                        refreshOutput();
                    } else {
                        this.errorView.proceed(new IndexOutOfBoundsException());
                    }
                } catch (NumberFormatException exception) {
                    this.fileTree = FileTreeBuilder.build(input);
                    refreshOutput();
                }
            }
        } catch (IOException exception) {
            this.errorView.proceed(exception);
        }
    }

    /**
     * Calls views to display file tree and menu.
     */
    private void refreshOutput() {
        this.fileTreeView.proceed(this.fileTree);
        this.menuView.proceed();
    }

    /**
     * Method of processing a numeric input.
     *
     * @param id Identifier of object (dir/file).
     * @throws IOException IOException.
     */
    private void handleIdInput(int id) throws IOException {
        Information fileInfo = this.fileTree.getTree().get(id - 1);
        Path filePath = fileInfo.getPath();
        if (fileInfo.getType().equals(Type.DIR)) {
            this.fileTree = FileTreeBuilder.build(filePath.toString());
        } else {
            FileOperationsUtil.openFile(filePath.toFile());
        }
    }
}