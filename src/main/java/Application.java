package main.java;

import main.java.controllers.InputController;
import main.java.models.Message;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        InputController inputController = new InputController();
        try {
            inputController.start();
        } catch (IOException e) {
            System.err.println(Message.IO_ERROR.getText());
        }
    }
}
