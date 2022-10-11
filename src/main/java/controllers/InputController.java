package main.java.controllers;

import main.java.models.FileTree;
import main.java.models.Information;
import main.java.models.MenuButton;
import main.java.models.Type;
import main.java.services.FileTreeBuilder;
import main.java.util.FileUtil;
import main.java.views.GreetingView;

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
            try {
                fileTree = MenuController.handleMenu(fileTree, MenuButton.valueOf(input.toUpperCase()));
            } catch (IllegalArgumentException exception) {
                FileTree localTree;
                try {
                    Information fileInfo = fileTree.getTree()
                            .get(Integer.parseInt(input) - 1);
                    if (fileInfo.getType().equals(Type.DIR)) {
                        localTree = FileTreeBuilder.build(fileInfo.getPath().toString());
                        fileTree = localTree;
                    } else {
                        FileUtil.openFile(fileInfo.getPath().toFile());
                    }
                } catch (NumberFormatException ex) {
                    localTree = FileTreeBuilder.build(input);
                    fileTree = localTree;
                }
            }
        }
    }
}