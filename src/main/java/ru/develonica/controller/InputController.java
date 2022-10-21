package ru.develonica.controller;

import ru.develonica.model.FileTree;
import ru.develonica.model.Information;
import ru.develonica.model.Type;
import ru.develonica.util.FileCalculator;
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

    private final FileTreeView fileTreeView = new FileTreeView();

    private final GreetingView greetingView = new GreetingView();

    private final MenuView menuView = new MenuView();

    private final ErrorView errorView = new ErrorView();

    private final MenuController menuController = new MenuController();

    public InputController(FileTree fileTree) {
        this.fileTree = fileTree;
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
                        || input.charAt(0) == '+'
                        || input.charAt(0) == '-')) {
                    this.fileTree = this.menuController.handleMenu(this.fileTree, input.charAt(0));
                    refreshOutput();
                    continue;
                }
                try {
                    handleIdInput(Integer.parseInt(input));
                    refreshOutput();
                } catch (NumberFormatException exception) {
                    this.fileTree = FileTreeBuilder.build(input);
                    refreshOutput();
                }
            }
        } catch (IndexOutOfBoundsException | IOException exception) {
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
            FileCalculator.openFile(filePath.toFile());
        }
    }
}