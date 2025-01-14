package com.project1.service;

import com.project1.model.dto.*;
import com.project1.model.response.LeaderBoardResponse;

import java.util.List;
import java.util.Map;

public interface ContestService {
    List<ContestDTO> findAll();
    void createContest(ContestCreate contestCreate);
    ContestDTO findContestById(Long id);
    void updateContest(ContestDTO contestDTO);
    void deleteContest(Long id);
    void addProblemContest(ProblemContestDTO problemContestDTO);
    List<ProblemDTO> findProblem(Long id);
    void updateProblemContest(ProblemContestDTO problemContestDTO);
    void editMember(Map<String,String> map);
    LeaderBoardResponse leaderBoard(ContestDTO contestDTO);
    void updateLanguage(ContestUpdateLanguageDTO contestUpdateLanguageDTO);
}
