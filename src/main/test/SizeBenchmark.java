package main.test;

import main.java.services.SizeConverter;
import main.java.services.SizeHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Approximate benchmark that allows you to evaluate
 * the usefulness of asynchronous implementation of the file
 * size calculation of nested directories.
 */
public class SizeBenchmark {

    public static void main(String[] args) {
        // async way
        forkJoinWay();

        // sync way
        System.out.println("=== Начало ===");
        long start = System.currentTimeMillis();
        System.out.println(SizeConverter.convert(syncWay(Path.of("C:\\Program Files (x86)").toFile())));
        long finish = System.currentTimeMillis();
        System.out.println("=== " + (finish - start) + " мс. ===");
    }

    public static void forkJoinWay() {
        SizeHandler sizeHandler = new SizeHandler();
        System.out.println("=== Начало ===");
        long start = System.currentTimeMillis();
        sizeHandler.activate(Path.of("C:\\Program Files (x86)").toFile());
        long finish = System.currentTimeMillis();
        System.out.println("=== " + (finish - start) + " мс. ===");
    }

    public static float syncWay(File destination) {
        float sizeInCurrentDir = 0;
        try {
            File[] files = destination.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        sizeInCurrentDir += Files.size(file.toPath());
                    } else {
                        sizeInCurrentDir += syncWay(file);
                    }
                }
            }
        } catch (IOException exception) {
            System.err.println("Ошибка");
        }
        return sizeInCurrentDir;
    }
}
