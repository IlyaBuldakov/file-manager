package ru.develonica;

import ru.develonica.controller.InputController;
import ru.develonica.controller.MenuController;
import ru.develonica.model.FileOperationsHandler;
import ru.develonica.model.FileTree;
import ru.develonica.model.ThreadPoolHolder;
import ru.develonica.model.FileTreeBuilder;
import ru.develonica.view.*;

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

        // ------Views------
        ErrorView errorView = new ErrorView();
        MenuOperationsView menuOperationsView = new MenuOperationsView();
        FileTreeView fileTreeView = new FileTreeView();
        GreetingView greetingView = new GreetingView();
        MenuView menuView = new MenuView();
        ProgressBarView progressBarView = new ProgressBarView();
        // -----------------

        // ------Models------
        FileOperationsHandler fileOperationsHandler = new FileOperationsHandler(errorView);
        FileTreeBuilder fileTreeBuilder = new FileTreeBuilder(progressBarView, fileOperationsHandler);
        FileTree startFileTree = fileTreeBuilder.build(HOME_PATH);
        // ------------------

        // ------Controllers------
        MenuController menuController = new MenuController(menuOperationsView, errorView, fileTreeBuilder);
        InputController inputController = new InputController(
                startFileTree, fileTreeView, greetingView,
                menuView, errorView, menuController, fileOperationsHandler, fileTreeBuilder);
        // ------------------------
        inputController.start();
    }
}