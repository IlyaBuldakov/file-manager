package ru.develonica;

import ru.develonica.controller.InputController;
import ru.develonica.controller.MenuController;
import ru.develonica.model.FileTree;
import ru.develonica.model.ThreadPoolHolder;
import ru.develonica.util.FileTreeBuilder;
import ru.develonica.view.ErrorView;
import ru.develonica.view.FileTreeView;
import ru.develonica.view.GreetingView;
import ru.develonica.view.MenuOperationsView;
import ru.develonica.view.MenuView;

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

        /*
        Views initialization.
         */
        ErrorView errorView = new ErrorView();
        MenuOperationsView menuOperationsView = new MenuOperationsView();
        FileTreeView fileTreeView = new FileTreeView();
        GreetingView greetingView = new GreetingView();
        MenuView menuView = new MenuView();

        MenuController menuController = new MenuController(menuOperationsView, errorView);

        InputController inputController = new InputController(
                startFileTree, fileTreeView, greetingView,
                menuView, errorView, menuController);

        inputController.start();
    }
}