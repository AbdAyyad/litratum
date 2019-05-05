package com.atypon.training.litratum.controllers.tools;

public class SubmissionProcessor implements Runnable {
    private String unprocessedContentId;

    public SubmissionProcessor(String unprocessedContentId) {
        this.unprocessedContentId = unprocessedContentId;
    }

    @Override
    public void run() {
        System.out.println(unprocessedContentId);
    }
}
