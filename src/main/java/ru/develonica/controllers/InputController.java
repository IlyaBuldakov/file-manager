package ru.develonica.controllers;

import ru.develonica.models.FileTree;
import ru.develonica.models.Information;
import ru.develonica.models.Message;
import ru.develonica.models.Type;
import ru.develonica.util.FileCalculator;
import ru.develonica.util.FileTreeBuilder;
import ru.develonica.views.ErrorView;
import ru.develonica.views.GreetingView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controller interacting with the user.
 */
public class InputController {

    /**
     * Active file tree.
     */
    private FileTree fileTree;

    private static final String HOME_PATH = System.getProperty("user.home");

    private static final String EXIT_VALUE = "exit";

    /**
     * Activating the InputController.
     */
    public void start() {
        try {
            GreetingView.greetingPage();
            fileTree = FileTreeBuilder.build(HOME_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = br.readLine();
                GreetingView.pleaseWait();
                if (input.equalsIgnoreCase(EXIT_VALUE)) {
                    break;
                }
                if (input.length() == 1
                        && (Character.isAlphabetic(input.charAt(0))
                        || input.charAt(0) == '+'
                        || input.charAt(0) == '-')) {
                    fileTree = MenuController.handleMenu(fileTree, input.charAt(0));
                    continue;
                }
                try {
                    handleIdInput(Integer.parseInt(input));
                } catch (NumberFormatException ex) {
                    fileTree = FileTreeBuilder.build(input);
                }
            }
        } catch (IndexOutOfBoundsException exception) {
            ErrorView.displayError(Message.OUT_OF_BOUNDS);
        } catch (IOException exception) {
            ErrorView.displayError(Message.IO_ERROR);
        }
    }

    /**
     * Method of processing a numeric input.
     *
     * @param id Identifier of object (dir/file).
     * @throws IOException IOException.
     */
    private void handleIdInput(int id) throws IOException {
        Information fileInfo = fileTree.getTree().get(id - 1);
        if (fileInfo.getType().equals(Type.DIR)) {
            fileTree = FileTreeBuilder.build(fileInfo.getPath().toString());
        } else {
            FileCalculator.openFile(fileInfo.getPath().toFile());
        }
    }
}