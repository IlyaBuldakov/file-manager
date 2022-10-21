package ru.develonica;

import ru.develonica.controllers.InputController;
import ru.develonica.models.FileTree;
import ru.develonica.models.ThreadPoolHolder;
import ru.develonica.util.FileTreeBuilder;

import java.util.concurrent.Executors;

/**
 * Main application class.
 */
public class Application {

    private static final String HOME_PATH = System.getProperty("user.home");

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("async")) {
            ThreadPoolHolder.setInstance(Executors.newCachedThreadPool());
        }
        /*
        Program loop start.
         */
        FileTree startFileTree = FileTreeBuilder.build(HOME_PATH);
        InputController inputController = new InputController(startFileTree);
        inputController.start();
    }
}