package ru.develonica;

import ru.develonica.controller.InputController;
import ru.develonica.controller.MenuController;
import ru.develonica.model.SizeConverter;
import ru.develonica.model.ThreadPoolHolder;
import ru.develonica.model.operation.CreateOperation;
import ru.develonica.model.operation.DeleteOperation;
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

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("async")) {
            ThreadPoolHolder.setInstance(Executors.newCachedThreadPool());
        }

        // ------Models------
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
        MenuController menuController
                = new MenuController(menuOperationsView, errorView, deleteOperation, createOperation);
        InputController inputController
                = new InputController(fileTreeView, greetingView, menuView, errorView, menuController);
        // ------------------------
        inputController.start();
    }
}