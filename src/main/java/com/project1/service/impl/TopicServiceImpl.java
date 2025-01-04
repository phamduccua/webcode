package com.project1.service.impl;

import com.project1.repository.TopicRepository;
import com.project1.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public List<String> findTopic(long classId) {
        List<String> result = topicRepository.findTopic(classId);
        return result;
    }
}
