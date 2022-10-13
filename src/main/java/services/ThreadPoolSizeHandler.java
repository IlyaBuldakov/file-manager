package main.java.services;

import main.java.models.Message;

import java.io.File;
    import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSizeHandler implements SizeHandler {

    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public float activate(File destination) throws ExecutionException, InterruptedException, IOException {
        File[] files = destination.listFiles();
        float filesSize = 0f;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filesSize += Files.size(file.toPath());
                } else {
                    filesSize += CompletableFuture.supplyAsync(() -> {
                        try {
                            return activate(file);
                        } catch (ExecutionException | InterruptedException e) {
                            System.err.println(Message.SIZE_HANDLER_ERROR);
                            return 0f;
                        } catch (IOException e) {
                            System.err.println(Message.IO_ERROR);
                            return 0f;
                        }
                    }, threadPool).get();
                }
            }
        }
        return filesSize;
    }
}