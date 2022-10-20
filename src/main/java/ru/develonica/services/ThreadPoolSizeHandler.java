package ru.develonica.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadPoolSizeHandler implements SizeHandler {

    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public float activate(File destination) throws ExecutionException, InterruptedException, IOException {
        File[] files = destination.listFiles();
        float filesSize = 0f;
        if (files != null && files.length != 0) {
            List<File> dest = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    filesSize += Files.size(file.toPath());
                } else {
                    dest.add(file);
                }
            }
            List<CompletableFuture<Float>> cfList = dest.stream()
                    .map(file -> CompletableFuture.supplyAsync(() -> {
                        try {
                            return activate(file);
                        } catch (ExecutionException | InterruptedException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, threadPool)).collect(Collectors.toList());
            CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0])).join();
            filesSize += cfList.stream().map(CompletableFuture::join).reduce(0f, Float::sum);
        }
        return filesSize;
    }
}