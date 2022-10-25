package ru.develonica;

import ru.develonica.controller.InputController;
import ru.develonica.controller.MenuController;
import ru.develonica.model.FileTree;
import ru.develonica.model.ThreadPoolHolder;
import ru.develonica.model.operation.CreateOperation;
import ru.develonica.model.operation.DeleteOperation;
import ru.develonica.model.SizeConverter;
import ru.develonica.view.*;

import java.io.IOException;
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


        // ------Models------
        FileTree startFileTree;
        try {
            startFileTree = FileTree.build(HOME_PATH);
        } catch (IOException exception) {
            throw new RuntimeException();
        }
        CreateOperation createOperation = new CreateOperation();
        DeleteOperation deleteOperation = new DeleteOperation();
        SizeConverter sizeConverter = new SizeConverter();
        // ------------------

        // ------Views------
        ErrorView errorView = new ErrorView();
        MenuOperationsView menuOperationsView = new MenuOperationsView();
        FileTreeView fileTreeView = new FileTreeView(sizeConverter);
        GreetingView greetingView = new GreetingView();
        MenuView menuView = new MenuView();
        // -----------------

        // ------Controllers------
        MenuController menuController = new MenuController(menuOperationsView, errorView, deleteOperation, createOperation);
        InputController inputController = new InputController(
                startFileTree, fileTreeView, greetingView,
                menuView, errorView, menuController);
        // ------------------------
        inputController.start();
    }
}