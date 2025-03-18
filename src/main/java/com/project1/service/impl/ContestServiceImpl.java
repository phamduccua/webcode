package com.project1.service.impl;
import com.project1.converter.ContestConverter;
import com.project1.converter.ProblemAddConverter;
import com.project1.converter.ProblemDTOConverter;
import com.project1.entity.ContestEntity;
import com.project1.entity.ProblemEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.*;
import com.project1.model.request.ProblemByUserRequest;
import com.project1.model.response.LeaderBoardResponse;
import com.project1.model.response.ProblemByUserResponse;
import com.project1.model.response.UserLeaderBoeard;
import com.project1.repository.ContestRepository;
import com.project1.repository.ProblemRepository;
import com.project1.repository.SubmissionRepository;
import com.project1.repository.UserRepository;
import com.project1.service.ContestService;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public List<ContestDTO> findByUserCreateId(HttpServletRequest request) {
        UserEntity userEntity = securityUtils.getUser(request);
        List<ContestEntity> list = contestRepository.findByCreatedBy(userEntity.getId());
        List<ContestDTO> contestDTOList = new ArrayList<>();
        for (ContestEntity contestEntity : list) {
            contestDTOList.add(contestConverter.toContestDTO(contestEntity));
        }
        return contestDTOList;
    }
    @Override
    public List<ContestDTO> findAll(HttpServletRequest request) {
        List<ContestDTO> result = new ArrayList<>();
        UserEntity user = securityUtils.getUser(request);
        List<ContestEntity> contestEntites = user.getContestEntities();
        for(ContestEntity contestEntity : contestEntites){
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
    public void addProblemContest(ProblemContestDTO problemContestDTO,HttpServletRequest request) {
        ProblemEntity problemEntity = problemAddConverter.toProblemEntity(problemContestDTO,request);
        ContestEntity contest = contestRepository.findContestById(problemContestDTO.getId_contest());
        problemEntity.getContestEntites().add(contest);
        contest.getProblemEntities().add(problemEntity);
        problemEntity.setLanguage(contest.getLanguage());
        problemEntity.setShow_test(contest.getShowTest());
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
    public void updateProblemContest(ProblemContestDTO problemContestDTO,HttpServletRequest request) {
        ProblemEntity prolemEntity = problemRepository.findById(problemContestDTO.getId()).get();
        ProblemEntity tmp = problemAddConverter.toProblemEntity(problemContestDTO,request);
        tmp.setLanguage(prolemEntity.getLanguage());
        problemRepository.save(tmp);
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
            if(userEntity.getStatus() != 0){
                UserLeaderBoeard userLeaderBoeard = new UserLeaderBoeard();
                userLeaderBoeard.setFullname(userEntity.getFullname());
                List<String> status = new ArrayList<>();
                Integer cnt = 0;
                for(ProblemEntity problemEntity : contest.getProblemEntities()){
                    List<String> list = submissionRepository.findDistinctStatusesByUserIdAndProblemId(userEntity.getId(), problemEntity.getId());
                    if(list.size() == 0){
                        status.add(null);
                    }
                    else{
                        if(list.size() > 1){
                            status.add("true");
                            cnt++;
                        }
                        else{
                            if(list.get(0).equals("true")){
                                status.add("true");
                                cnt++;
                            }
                            else status.add("false");
                        }
                    }
                }
                userLeaderBoeard.setCount(cnt);
                userLeaderBoeard.setStatus(status);
                listUser.add(userLeaderBoeard);
            }
        }
        Collections.sort(listUser, new Comparator<UserLeaderBoeard>() {
            @Override
            public int compare(UserLeaderBoeard o1, UserLeaderBoeard o2) {
                if(o1.getCount() != o2.getCount()){
                    return o2.getCount() - o1.getCount();
                }
                else{
                    String[] a = o1.getFullname().split(" ");
                    String[] b = o2.getFullname().split(" ");
                    if(!a[a.length - 1].equals(b[b.length - 1])){
                        return a[a.length - 1].compareTo(b[b.length - 1]);
                    }
                    else if(!a[0].equals(b[0])){
                        return a[0].compareTo(b[0]);
                    }
                    return o1.getFullname().compareTo(o2.getFullname());
                }
            }
        });
        result.setUser(listUser);
        return result;
    }

    @Override
    public void install(ContestInstallDTO contestInstallDTO) {
        StringBuilder language = new StringBuilder();
        boolean show_test = contestInstallDTO.isShow_test();
        List<String> languages = contestInstallDTO.getLanguages();
        if(languages != null) {
            for (int i = 0; i < languages.size(); i++) {
                if (i != languages.size() - 1) {
                    language.append(languages.get(i)).append(",");
                } else language.append(languages.get(i));
            }
        }
        ContestEntity contest = contestRepository.findContestById(contestInstallDTO.getContestId());
        contest.setLanguage(language.toString());
        for(ProblemEntity problemEntity : contest.getProblemEntities()){
            problemEntity.setLanguage(language.toString());
            problemEntity.setShow_test(show_test == true ? 1 : 0);
            problemRepository.save(problemEntity);
        }
        contest.setShowTest(show_test == true ? 1 : 0);
        contestRepository.save(contest);
    }

    @Override
    public List<ProblemByUserResponse> findByUser(ProblemByUserRequest problemByUserRequest, HttpServletRequest request, Pageable pageable) {
        if(problemByUserRequest.getName() == null){
            problemByUserRequest.setName("");
        }
        UserEntity user = securityUtils.getUser(request);
        List<ProblemEntity> list = problemRepository.findByTitleContainingAndCreatedByAndType(problemByUserRequest.getName(),user.getId(),pageable,"CONTEST");
        List<ProblemByUserResponse> result = new ArrayList<>();
        for(ProblemEntity problemEntity : list){
            ProblemByUserResponse problemByUserResponse = new ProblemByUserResponse();
            problemByUserResponse.setProblemId(problemEntity.getId());
            problemByUserResponse.setName(problemEntity.getTitle());
            List<ContestEntity> contests = problemEntity.getContestEntites();
            for(ContestEntity contestEntity : contests){
                if(contestEntity.getId() == problemByUserRequest.getContestId()){
                    problemByUserResponse.setStatus(1);
                    break;
                }
            }
            result.add(problemByUserResponse);
        }
        return result;
    }

    @Override
    public void updateProblemByUser(Map<String, String> map) {
        Long contestId = Long.valueOf(map.get("contestId"));
        Long problemId = Long.valueOf(map.get("problemId"));
        ContestEntity contest = contestRepository.findContestById(contestId);
        ProblemEntity problem = problemRepository.findById(problemId).get();
        if(map.get("checked").equals("true")){
            contest.getProblemEntities().add(problem);
            problem.getContestEntites().add(contest);
            contestRepository.save(contest);
            problemRepository.save(problem);
        }
        else{
            contest.getProblemEntities().remove(problem);
            problem.getContestEntites().remove(contest);
            contestRepository.save(contest);
            problemRepository.save(problem);
        }
    }

    @Override
    public void deleteProblemContest(Map<String, String> map) {
        Long contestId = Long.valueOf(map.get("contestId"));
        Long problemId = Long.valueOf(map.get("problemId"));
        ContestEntity contest = contestRepository.findContestById(contestId);
        ProblemEntity problem = problemRepository.findById(problemId).get();
        contest.getProblemEntities().remove(problem);
        problem.getContestEntites().remove(contest);
        contestRepository.save(contest);
        problemRepository.save(problem);
    }

    @Override
    public int countTotalProblemByUser(HttpServletRequest request) {
        UserEntity user = securityUtils.getUser(request);
        return problemRepository.countByCreatedByAndType(user.getId(),"CONTEST");
    }
}
