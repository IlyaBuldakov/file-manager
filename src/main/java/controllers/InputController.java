package main.java.controllers;

import main.java.models.FileTree;
import main.java.services.FileTreeBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputController {

    private void greetingPage() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("*** Welcome! Here is your home path ***");
        System.out.println("***************************************");

        FileTree fileTree = FileTreeBuilder.build(System.getProperty("user.home"));
        fileTree.displayView();
    }

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
