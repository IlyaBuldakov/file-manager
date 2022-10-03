package main.java.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class SizeHandler {

    ForkJoinPool forkJoinPool = new ForkJoinPool();

    public void activate(File destination) {
        float result = forkJoinPool.invoke(new SizeHandlerTask(destination));
        System.out.println(SizeConverter.convert(result));
    }

    static class SizeHandlerTask extends RecursiveTask<Float> {

        private final File destination;

        public SizeHandlerTask(File destination) {
            this.destination = destination;
        }

        @Override
        protected Float compute() {
            float sizeInCurrentDir = 0;
            try {
                File[] files = destination.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            sizeInCurrentDir += Files.size(file.toPath());
                        } else {
                            SizeHandlerTask task1 = new SizeHandlerTask(file);
                            task1.fork();
                            sizeInCurrentDir += task1.join();
                        }
                    }
                }
            } catch (IOException exception) {
                System.err.println("Ошибка");
            }
            return sizeInCurrentDir;
        }
    }
}
