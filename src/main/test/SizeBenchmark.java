package main.test;

import main.java.services.ForkJoinSizeHandler;
import main.java.services.SizeConverter;
import main.java.services.SizeHandler;
import main.java.services.SyncSizeHandler;
import main.java.services.ThreadPoolSizeHandler;

import java.nio.file.Path;

/**
 * Approximate benchmark that allows you to evaluate
 * the usefulness of asynchronous implementation of the file
 * size calculation of nested directories.
 */
public class SizeBenchmark {

    private static final String TEST_DIR = "C:\\Program Files (x86)";

    static SizeHandler fJHandler = new ForkJoinSizeHandler();
    static SizeHandler threadPoolHandler = new ThreadPoolSizeHandler();
    static SizeHandler syncHandler = new SyncSizeHandler();

    public static void main(String[] args) {
        forkJoinWay();
        threadPoolWay();
        syncWay();
    }

    public static void forkJoinWay() {
        System.out.println("=== Начало F/J ===");
        long start = System.currentTimeMillis();
        System.out.println(SizeConverter.convert(fJHandler.activate(Path.of(TEST_DIR).toFile())));
        long finish = System.currentTimeMillis();
        System.out.println("=== " + (finish - start) + " мс. ===");
    }

    public static void threadPoolWay() {
        System.out.println("=== Начало thread pool ===");
        long start = System.currentTimeMillis();
        System.out.println(SizeConverter.convert(threadPoolHandler.activate(Path.of(TEST_DIR).toFile())));
        long finish = System.currentTimeMillis();
        System.out.println("=== " + (finish - start) + " мс. ===");
    }

    public static void syncWay() {
        System.out.println("=== Начало sync ===");
        long start = System.currentTimeMillis();
        System.out.println(SizeConverter.convert(syncHandler.activate(Path.of(TEST_DIR).toFile())));
        long finish = System.currentTimeMillis();
        System.out.println("=== " + (finish - start) + " мс. ===");
    }
}
