package main.java.controllers;

import main.java.models.FileTree;
import main.java.models.Information;
import main.java.models.MenuButton;
import main.java.models.Type;
import main.java.services.FileTreeBuilder;
import main.java.util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

/**
 * Controller interacting with the user.
 */
public class InputController {

    FileTree fileTree;

    /**
     * The greeting method that is called when the
     * program starts before interacting with the user.
     */
    private void greetingPage() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println();
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("**** Welcome! Here is your home path *********");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("***** You can navigate at your file **********");
            System.out.println("***** system using absolute path *************");
            System.out.println("***** or numbers in directory list. **********");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println();
            fileTree = FileTreeBuilder.build(System.getProperty("user.home"));
            return null;
        });
    }

    /**
     * Activating the InputController.
     *
     * @throws IOException Exception.
     */
    public void start() throws IOException {
        greetingPage();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equalsIgnoreCase("exit")) {
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