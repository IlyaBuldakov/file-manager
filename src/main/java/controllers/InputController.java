package main.java.controllers;

import main.java.models.FileTree;
import main.java.services.FileTreeBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

/**
 * Controller interacting with the user.
 */
public class InputController {

    FileTree fileTree = FileTreeBuilder.build(System.getProperty("user.home"));

    /**
     * The greeting method that is called when the
     * program starts before interacting with the user.
     */
    private void greetingPage() {
        CompletableFuture<Void> greetingUserDig = CompletableFuture.supplyAsync(() -> {
            System.out.println();
            System.out.println("***************************************");
            System.out.println("*** Welcome! Here is your home path ***");
            System.out.println("***************************************");
            fileTree.displayView();
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
                FileTree localTree = FileTreeBuilder
                        .build(fileTree
                                .getTree().get(Integer.parseInt(input) - 1).getPath().toString());
                localTree.displayView();
            } catch (NumberFormatException exception) {
                FileTree localTree = FileTreeBuilder
                        .build(input);
                localTree.displayView();
            }
        }
    }
}
