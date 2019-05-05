package com.atypon.training.litratum.controllers.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BackstageQueue {
    private static BlockingQueue<String> queue;

    static {
        queue = new LinkedBlockingQueue<>();
    }

    public static void add(String contentId) {
        queue.add(contentId);
    }

    public static String take() throws InterruptedException {
        return queue.take();
    }
}
