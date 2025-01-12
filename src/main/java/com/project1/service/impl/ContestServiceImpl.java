package com.project1.service.impl;

import com.project1.converter.ContestConverter;
import com.project1.entity.ContestEntity;
import com.project1.model.dto.ContestCreate;
import com.project1.model.dto.ContestDTO;
import com.project1.repository.ContestRepository;
import com.project1.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private ContestConverter contestConverter;
    @Override
    public List<ContestDTO> findAll() {
        List<ContestDTO> result = new ArrayList<>();
        List<ContestEntity> contestEntites = contestRepository.findAll();
        for(ContestEntity contestEntity : contestEntites){
            ContestDTO contestDTO = new ContestDTO();
            result.add(contestConverter.toContestDTO(contestEntity));
        }
        return result;
    }

    @Override
    public void createContest(ContestCreate contestCreate) {
        ContestEntity newContest = contestConverter.toContestEntity(contestCreate);
        newContest.setStatus(1);
        contestRepository.save(newContest);
    }

    @Override
    public ContestDTO findContestById(Long id) {
        ContestEntity contestEntty = contestRepository.findContestById(id);
        ContestDTO contestDTO = contestConverter.toContestDTO(contestEntty);
        return contestDTO;
    }

    @Override
    public void updateContest(ContestDTO contestDTO) {
        ContestEntity contest = contestRepository.findContestById(contestDTO.getId());
        contest.setName(contestDTO.getName());
        contest.setStartTime(contestDTO.getStart_time());
        contest.setEndTime(contestDTO.getEnd_time());
        if(contest.getEndTime().isAfter(LocalDateTime.now())){
            contest.setStatus(1);
        }
        contestRepository.save(contest);
    }

    @Override
    public void deleteContest(Long id) {
        ContestEntity contest = contestRepository.findContestById(id);
        contestRepository.delete(contest);
    }
}
