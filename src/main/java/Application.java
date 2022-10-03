package main.java;

import main.java.services.SizeHandler;

import java.nio.file.Path;

public class Application {

    public static void main(String[] args) {
        SizeHandler sizeHandler = new SizeHandler();
        sizeHandler.activate(Path.of("C:\\Test").toFile());
    }
}
