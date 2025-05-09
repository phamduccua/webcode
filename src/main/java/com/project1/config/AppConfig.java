package com.project1.config;

import com.project1.entity.SubmissionEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class AppConfig {

    @Bean
    public BlockingQueue<SubmissionEntity> submissionQueue() {
        return new LinkedBlockingQueue<>();
    }

}

