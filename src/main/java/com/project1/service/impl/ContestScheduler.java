package com.project1.service.impl;

import com.project1.entity.ContestEntity;
import com.project1.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContestScheduler {
    @Autowired
    private ContestRepository contestRepository;
    @Scheduled(fixedRate = 60000)
    public void updateContestStatus() {
        List<ContestEntity> contests = contestRepository.findAllByStatusAndEndTimeBefore(1, LocalDateTime.now());
        for (ContestEntity contest : contests) {
            contest.setStatus(0);
            contestRepository.save(contest);
        }
    }
}
