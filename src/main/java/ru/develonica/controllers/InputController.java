package ru.develonica.controllers;

import ru.develonica.models.FileTree;
import ru.develonica.models.Information;
import ru.develonica.models.Type;
import ru.develonica.util.FileCalculator;
import ru.develonica.util.FileTreeBuilder;
import ru.develonica.views.ErrorView;
import ru.develonica.views.FileTreeView;
import ru.develonica.views.GreetingView;
import ru.develonica.views.MenuView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                } catch (NumberFormatException ex) {
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
        if (fileInfo.getType().equals(Type.DIR)) {
            this.fileTree = FileTreeBuilder.build(fileInfo.getPath().toString());
        } else {
            FileCalculator.openFile(fileInfo.getPath().toFile());
        }
    }
}