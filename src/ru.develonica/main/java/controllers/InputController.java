package controllers;

import models.FileTree;
import models.Information;
import models.Type;
import util.FileCalculator;
import util.FileTreeBuilder;
import views.v1.GreetingView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controller interacting with the user.
 */
public class InputController {

    FileTree fileTree;

    private static final String HOME_PATH = System.getProperty("user.home");

    private static final String EXIT_VALUE = "exit";

    /**
     * Activating the InputController.
     *
     * @throws IOException Exception.
     */
    public void start() throws IOException {
        GreetingView.greetingPage();
        fileTree = FileTreeBuilder.build(HOME_PATH);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equalsIgnoreCase(EXIT_VALUE)) {
                break;
            }
            fileTree = input.length() == 1
                    ? MenuController.handleMenu(fileTree, input.charAt(0))
                    : fileTree;
            try {
                handleIdInput(Integer.parseInt(input));
            } catch (NumberFormatException ex) {
                fileTree = FileTreeBuilder.build(input);
            }
        }
    }

    private void handleIdInput(int id) throws IOException {
        Information fileInfo = fileTree.getTree().get(id - 1);
        if (fileInfo.getType().equals(Type.DIR)) {
            fileTree = FileTreeBuilder.build(fileInfo.getPath().toString());
        } else {
            FileCalculator.openFile(fileInfo.getPath().toFile());
        }
    }
}