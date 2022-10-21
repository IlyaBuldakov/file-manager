package ru.develonica.models;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class ThreadPoolHolder {

    private static ExecutorService THREAD_POOL;

    private ThreadPoolHolder() {
    }

    public static void setInstance(ExecutorService executorService) {
        THREAD_POOL = executorService;
    }

    public static Optional<ExecutorService> getInstance() {
        return Optional.ofNullable(THREAD_POOL);
    }
}
