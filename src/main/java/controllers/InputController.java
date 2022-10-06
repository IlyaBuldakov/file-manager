package main.java.controllers;

import main.java.models.FileTree;
import main.java.services.FileTreeBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controller interacting with the user.
 */
public class InputController {

    /**
     * The greeting method that is called when the
     * program starts before interacting with the user.
     */
    private void greetingPage() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("*** Welcome! Here is your home path ***");
        System.out.println("***************************************");

        FileTree fileTree = FileTreeBuilder.build(System.getProperty("user.home"));
        fileTree.displayView();
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
            FileTree fileTree = FileTreeBuilder.build(input);
            fileTree.displayView();
        }
    }
}
