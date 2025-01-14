package com.project1.api.admin;

import com.project1.entity.UserEntity;
import com.project1.model.dto.ContestCreate;
import com.project1.model.dto.ContestDTO;
import com.project1.model.dto.ProblemContestDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.service.ContestService;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/")
@RestController
public class ContestAPI {
    @Autowired
    private ContestService contestService;
    @Autowired
    private SecurityUtils securityUtils;
    @PostMapping("admin/create_contest")
    public ResponseEntity<?> createContest(@RequestBody ContestCreate contestCreate, HttpServletRequest request) {
        try{
            String token = "";
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
            UserEntity user = securityUtils.getUser(token);
            contestCreate.setCreated_by(user.getId());
            contestService.createContest(contestCreate);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("admin/update_contest")
    public ResponseEntity<?> updateContest(@RequestBody ContestDTO ContestDTO) {
        try{
            contestService.updateContest(ContestDTO);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("admin/delete-contest-{id}")
    public ResponseEntity<?> deleteContest(@PathVariable Long id) {
        try{
            contestService.deleteContest(id);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("admin/contest-create_problem")
    public ResponseEntity<?> createProblem(@RequestBody ProblemContestDTO problemContestDTO){
        try{
            contestService.addProblemContest(problemContestDTO);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("admin/update_problem-contest")
    public ResponseEntity<?> updateProblemContest(@RequestBody ProblemContestDTO problemContestDTO){
        try{
            contestService.updateProblemContest(problemContestDTO);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("admin/add_testCase")
    public void addTestCase(@RequestBody TestCaseDTO testCaseDTO){
        System.out.println(testCaseDTO);

    }

    @PutMapping("admin/edit-member")
    public ResponseEntity<?> editMember(@RequestBody Map<String,String> map){
        try{
            contestService.editMember(map);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
