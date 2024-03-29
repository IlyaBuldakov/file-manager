package ru.develonica.model.service.operation;

import ru.develonica.model.info.FileTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateOperation {

    public void handle(FileTree tree, String dirName) throws IOException {
        Path path = Path.of("%s/%s".formatted(tree.getTreePath().toString(), dirName));
        Files.createDirectories(path);
    }
}
