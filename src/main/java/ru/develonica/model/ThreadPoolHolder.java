package ru.develonica.model;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * Thread pool holder class.
 *
 * Depending on the run parameters, it stores the
 * specified thread pool, or an empty {@link Optional}.
 */
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
