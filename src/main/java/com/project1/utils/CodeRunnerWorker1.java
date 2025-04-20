package com.project1.utils;

import com.project1.entity.SubmissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class CodeRunnerWorker1 implements Runnable {
    private final BlockingQueue<SubmissionEntity> queue;
    private final RunCode1 runCode1;

    @Autowired
    public CodeRunnerWorker1(BlockingQueue<SubmissionEntity> queue, RunCode1 runCode1) {
        this.queue = queue;
        this.runCode1 = runCode1;
    }

    @Override
    public void run() {
        while (true) {
            try {
                SubmissionEntity submission = queue.take();
                runCode1.run(submission);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
