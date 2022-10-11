package main.java.services;

import main.java.models.Message;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolSizeHandler implements SizeHandler {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    private static final SyncSizeHandler syncHandler = new SyncSizeHandler();

    public float activate(File destination) {
        try {
            float sum = 0;
            File[] files = destination.listFiles();
            if (files != null) {
                BlockingQueue<File> queue = new LinkedBlockingQueue<>(List.of(files));
                while (queue.size() > 1) {
                    Future<Float> fut1 = threadPool.submit(new SizeHandlerTask(queue.poll()));
                    Future<Float> fut2 = threadPool.submit(new SizeHandlerTask(queue.poll()));
                    sum += fut1.get();
                    sum += fut2.get();
                }
                if (queue.size() == 1) {
                    sum += syncHandler.activate(queue.poll());
                }
            }
            return sum;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(Message.INTERNAL_ERROR.getText());
        }
        return 0;
    }

    static class SizeHandlerTask implements Callable<Float> {

        private final File destination;

        public SizeHandlerTask(File destination) {
            this.destination = destination;
        }

        @Override
        public Float call() {
            return syncHandler.activate(destination);
        }
    }
}