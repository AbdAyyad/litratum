package com.atypon.training.litratum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static ThreadPool instance;
    private ExecutorService pool;

    private ThreadPool() {
        pool = Executors.newCachedThreadPool();
    }

    public synchronized static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    public void execute(Runnable task) {
        pool.submit(task);
    }
}
