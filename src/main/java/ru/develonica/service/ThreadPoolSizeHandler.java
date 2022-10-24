package ru.develonica.service;

import ru.develonica.view.ErrorView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * Multithreading (thread pool) {@link SizeHandler} implementation.
 */
public class ThreadPoolSizeHandler implements SizeHandler {

    private final ExecutorService threadPool;

    private final ErrorView errorView = new ErrorView();

    public ThreadPoolSizeHandler(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public long activate(File destination) throws ExecutionException, InterruptedException, IOException {
        File[] files = destination.listFiles();
        long filesSize = 0L;
        if (files != null && files.length != 0) {
            List<CompletableFuture<Long>> cfList = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    filesSize += Files.size(file.toPath());
                } else {
                    cfList.add(CompletableFuture.supplyAsync(() -> {
                        try {
                            return activate(file);
                        } catch (ExecutionException
                                 | InterruptedException | IOException exception) {
                            this.errorView.proceed(exception);
                            return 0L;
                        }
                    }, threadPool));
                }
            }
            CompletableFuture.allOf(cfList.toArray(CompletableFuture[]::new)).join();
            filesSize += cfList.stream().map(CompletableFuture::join).reduce(0L, Long::sum);
        }
        return filesSize;
    }
}