package main.java.services;

import main.java.models.FileTree;
import main.java.models.Information;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    public static FileTree build(String destination) {
        File[] filesInDestDir = Path.of(destination).toFile().listFiles();
        if (filesInDestDir != null) {
            return new FileTree((Arrays.stream(filesInDestDir)
                    .map(Information::new)
                    .collect(Collectors.toList())));
        }
        return new FileTree();
    }
}
