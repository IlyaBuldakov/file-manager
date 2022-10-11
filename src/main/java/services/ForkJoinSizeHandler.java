package main.java.services;

import main.java.models.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Size handler implementation which uses Fork-Join pool.
 */
public class ForkJoinSizeHandler implements SizeHandler {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public float activate(File destination) {
        return forkJoinPool.invoke(new SizeHandlerTask(destination));
    }

    /**
     * Recursive task for fork-join pool.
     */
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
                System.err.println(Message.IO_ERROR.getText());
            }
            return sizeInCurrentDir;
        }
    }
}
