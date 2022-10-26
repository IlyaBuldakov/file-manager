package ru.develonica.model.operation;

import ru.develonica.model.service.info.FileTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteOperation {

    public void handle(FileTree tree, int id) throws IOException {
        Path pathToDelete = tree.getTree().get(id - 1).getPath();
        Files.delete(pathToDelete);
    }
}
