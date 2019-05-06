package com.atypon.training.litratum.controllers.tools;

import com.atypon.training.litratum.model.database.daos.implementations.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUnprocessedContentDao;

public class ContentProcessingService implements Runnable {
    @Override
    public void run() {
        try {
            ThreadPool pool = ThreadPool.getInstance();
            IUnprocessedContentDao dao = new UnprocessedContentDao();
            while (true) {
                String submissionId = BackstageQueue.take();
                dao.updateStatus(submissionId, 2);
                Runnable task = new SubmissionProcessor(submissionId);
                pool.execute(task);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
