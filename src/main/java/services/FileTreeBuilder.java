package main.java.services;

import main.java.models.FileTree;
import main.java.models.Information;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    public static FileTree build(String destination) {
        File fileDest = Path.of(destination).toFile();
        File[] filesInDestDir = fileDest.listFiles();
        if (filesInDestDir != null) {
            ArrayList<Information> infoList = new ArrayList<>();
            int progressCounter = 10;
            for (File file : filesInDestDir) {
                Information info = new Information(file);
                infoList.add(info);
                progressPercentage(progressCounter, filesInDestDir.length * 10);
                progressCounter += 10;
            }
            return new FileTree(infoList, fileDest.getName());
        }
        return new FileTree(fileDest.getName());
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