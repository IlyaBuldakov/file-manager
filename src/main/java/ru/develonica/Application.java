package ru.develonica;

import ru.develonica.controllers.InputController;
import ru.develonica.models.FileTree;
import ru.develonica.util.FileTreeBuilder;

/**
 * Main application class.
 */
public class Application {

    private static final String HOME_PATH = System.getProperty("user.home");

    public static void main(String[] args) {
        /*
        Program loop start.
         */
        FileTree startFileTree = FileTreeBuilder.build(HOME_PATH);
        InputController inputController = new InputController(startFileTree);
        inputController.start();
    }
}