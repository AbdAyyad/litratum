package com.atypon.training.litratum.controllers.tools;

public class ContentProcessingService implements Runnable {
    @Override
    public void run() {
        try {
            ThreadPool pool = ThreadPool.getInstance();
            while (true) {
                String submissionId = BackstageQueue.take();
                Runnable task = new SubmissionProcessor(submissionId);
                pool.execute(task);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
