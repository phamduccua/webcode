package com.project1.service.impl;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.model.request.RankingRequest;
import com.project1.model.response.RankingResponse;
import com.project1.repository.ProblemRepository;
import com.project1.repository.SubmissionRepository;
import com.project1.repository.UserRepository;
import com.project1.service.RankingService;
import com.project1.utils.ClassIdUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingServiceImpl implements RankingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Override
    public List<RankingResponse> findAllRanking(RankingRequest rankingRequest, Pageable pageable) {
        Long classId = ClassIdUtils.toClassId(rankingRequest.getGroup());
        List<UserEntity> listUser = userRepository.findByRoleAndClassIdContainingAndStatus("USER",String.valueOf(classId),1);
        List<ProblemEntity> listProblem = problemRepository.findByClassId(classId);
        List<RankingResponse> listRankingResponse = new ArrayList<>();
        for(UserEntity user:listUser){
            Long countAccepnt = 0L;
            Long coutTried = 0L;
            RankingResponse rankingResponse = new RankingResponse();
            rankingResponse.setUserName(user.getUsername());
            rankingResponse.setFullName(user.getFullname());
            for(ProblemEntity problem:listProblem){
                List<String> list = submissionRepository.findDistinctStatusesByUserIdAndProblemId(user.getId(), problem.getId());
                if(list == null || list.isEmpty()){
                    continue;
                }
                else{
                    if(list.size() > 1){
                        countAccepnt++;
                        coutTried++;
                    }
                    else{
                        if(list.get(0).equals("true")){
                            countAccepnt++;
                            coutTried++;
                        }
                        else if(list.get(0).equals("false")){
                            coutTried++;
                        }
                    }
                }

            }
            rankingResponse.setCountAccept(countAccepnt);
            rankingResponse.setCountTried(coutTried);
            listRankingResponse.add(rankingResponse);
        }
        return sortAndPage(listRankingResponse, pageable);
    }

    @Override
    public int countTotalItem(RankingRequest rankingRequest) {
        Long classId = ClassIdUtils.toClassId(rankingRequest.getGroup());
        return userRepository.countByRoleAndClassIdContainingAndStatus("USER",String.valueOf(classId),1);
    }

    private List<RankingResponse> sortAndPage(List<RankingResponse> list,Pageable pageable) {
        Collections.sort(list, new Comparator<RankingResponse>() {
            @Override
            public int compare(RankingResponse o1, RankingResponse o2) {
                if(!Objects.equals(o1.getCountAccept(), o2.getCountAccept())){
                    return o2.getCountTried() > o1.getCountTried() ? 1 : -1;
                }
                else{
                    if(!Objects.equals(o1.getCountTried(), o2.getCountTried())){
                        return o1.getCountTried() < o2.getCountTried() ? 1 : -1;
                    }
                    return o1.getUserName().compareTo(o2.getUserName());
                }
            }
        });
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        if (start > list.size()) {
            return new ArrayList<>();
        }
        return list.subList(start, end);
    }
}
