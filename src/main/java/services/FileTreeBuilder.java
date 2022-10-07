package main.java.services;

import main.java.models.FileTree;
import main.java.models.Information;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    public static FileTree build(String destination) {
        File[] filesInDestDir = Path.of(destination).toFile().listFiles();
        if (filesInDestDir != null) {
            CompletableFuture<List<Information>> cf = CompletableFuture.supplyAsync(() -> {
                ArrayList<Information> infoList = new ArrayList<>();
                int progressCounter = 10;
                for (File file : filesInDestDir) {
                    Information info = new Information(file);
                    infoList.add(info);
                    progressPercentage(progressCounter, filesInDestDir.length * 10);
                    progressCounter += 10;
                }
                return infoList;
            });
            try {
                return new FileTree(cf.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return new FileTree();
    }

    public static void progressPercentage(int done, int total) {
        int size = 10;
        String iconDone = "█";
        String iconRemain = "░";

        if (done > total) {
            throw new IllegalArgumentException();
        }
        int donePercents = (100 * done) / total;
        int doneLength = size * donePercents / 100;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i < doneLength) {
                bar.append(iconDone);
            } else {
                bar.append(iconRemain);
            }
        }
        System.out.print("\r" + bar + " " + donePercents + "%");
        if (done == total) {
            System.out.print("\n");
        }
    }
}
