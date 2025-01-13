//package com.project1.utils;
//
//import com.project1.entity.SubmissionEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.BlockingQueue;
//
//@Component
//public class CodeRunnerWorker implements Runnable {
//    private final BlockingQueue<SubmissionEntity> queue;
//    private final RunCode runCode;
//
//    @Autowired
//    public CodeRunnerWorker(BlockingQueue<SubmissionEntity> queue, RunCode runCode) {
//        this.queue = queue;
//        this.runCode = runCode;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                SubmissionEntity submission = queue.take();
//                runCode.run(submission);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
//        }
//    }
//}
