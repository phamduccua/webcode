package com.project1.service.impl;

import com.project1.converter.ContestConverter;
import com.project1.converter.ProblemAddConverter;
import com.project1.converter.ProblemDTOConverter;
import com.project1.entity.ContestEntity;
import com.project1.entity.ProblemEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ContestCreate;
import com.project1.model.dto.ContestDTO;
import com.project1.model.dto.ProblemContestDTO;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.response.LeaderBoardResponse;
import com.project1.model.response.UserLeaderBoeard;
import com.project1.repository.ContestRepository;
import com.project1.repository.ProblemRepository;
import com.project1.repository.SubmissionRepository;
import com.project1.repository.UserRepository;
import com.project1.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ContestServiceImpl implements ContestService {
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private ContestConverter contestConverter;
    @Autowired
    private ProblemAddConverter problemAddConverter;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemDTOConverter problemDTOConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
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
        ContestEntity contest = contestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contest not found"));
        contest.getProblemEntities().forEach(problem -> problem.getContestEntites().remove(contest));
        contestRepository.delete(contest);
    }

    @Override
    public void addProblemContest(ProblemContestDTO problemContestDTO) {
        ProblemEntity problemEntity = problemAddConverter.toProblemEntity(problemContestDTO);
        ContestEntity contest = contestRepository.findContestById(problemContestDTO.getId_contest());
        problemEntity.getContestEntites().add(contest);
        contest.getProblemEntities().add(problemEntity);
        problemRepository.save(problemEntity);
        contestRepository.save(contest);
    }

    @Override
    public List<ProblemDTO> findProblem(Long id) {
        ContestEntity contest = contestRepository.findContestById(id);
        List<ProblemEntity> list = contest.getProblemEntities();
        List<ProblemDTO> result = new ArrayList<>();
        for(ProblemEntity problemEntity : list){
            problemDTOConverter.toProblemDTO(problemEntity);
            result.add(problemDTOConverter.toProblemDTO(problemEntity));
        }
        return result;
    }

    @Override
    public void updateProblemContest(ProblemContestDTO problemContestDTO) {
        ProblemEntity prolemEntity = problemRepository.findById(problemContestDTO.getId()).get();
        prolemEntity = problemAddConverter.toProblemEntity(problemContestDTO);
        problemRepository.save(prolemEntity);
    }

    @Override
    public void editMember(Map<String, String> map) {
        Long contestId = Long.valueOf(map.get("contestId"));
        Long userId = Long.valueOf(map.get("userId"));
        ContestEntity contest = contestRepository.findContestById(contestId);
        UserEntity user = userRepository.findById(userId);
        if(map.get("checked").equals("true")){
            contest.getUserEntities().add(user);
            user.getContestEntities().add(contest);
            contestRepository.save(contest);
            userRepository.save(user);
        }
        else{
            contest.getUserEntities().remove(user);
            user.getContestEntities().remove(contest);
            contestRepository.save(contest);
            userRepository.save(user);
        }
    }

    @Override
    public LeaderBoardResponse leaderBoard(ContestDTO contestDTO) {
        LeaderBoardResponse result = new LeaderBoardResponse();

        ContestEntity contest = contestRepository.findContestById(contestDTO.getId());
        int size = contest.getProblemEntities().size();
        List<String> nameProblem = new ArrayList<>(Collections.nCopies(size, ""));
        result.setName_problem(nameProblem);
        List<UserLeaderBoeard> listUser = new ArrayList<>();
        for(UserEntity userEntity : contest.getUserEntities()){
            UserLeaderBoeard userLeaderBoeard = new UserLeaderBoeard();
            userLeaderBoeard.setFullname(userEntity.getFullname());
            List<String> status = new ArrayList<>();
            for(ProblemEntity problemEntity : contest.getProblemEntities()){
               List<String> list = submissionRepository.findDistinctStatusesByUserIdAndProblemId(userEntity.getId(), problemEntity.getId());
               if(list.size() == 0){
                   status.add(null);
               }
               else{
                   if(list.size() > 1){
                       status.add("true");
                   }
                   else{
                       if(list.get(0).equals("1")){
                           status.add("true");
                       }
                       else status.add("false");
                   }
               }
            }
            userLeaderBoeard.setStatus(status);
            listUser.add(userLeaderBoeard);
        }
        result.setUser(listUser);
        return result;
    }
}
