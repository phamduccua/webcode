package com.project1.api.admin;
import com.project1.entity.UserEntity;
import com.project1.model.dto.*;
import com.project1.service.ContestService;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
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
            UserEntity user = securityUtils.getUser(request);
            contestCreate.setCreatedBy(user.getId());
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

    @DeleteMapping("/admin/delete-problem")
    public ResponseEntity<?> deleteProblem(@RequestBody Map<String,String> map) {
        try{
            contestService.deleteProblemContest(map);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("admin/contest-create_problem")
    public ResponseEntity<?> createProblem(@RequestBody ProblemContestDTO problemContestDTO,HttpServletRequest request) {
        try{
            contestService.addProblemContest(problemContestDTO,request);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("admin/update_problem-contest")
    public ResponseEntity<?> updateProblemContest(@RequestBody ProblemContestDTO problemContestDTO,HttpServletRequest request) {
        try{
            contestService.updateProblemContest(problemContestDTO,request);
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

    @PutMapping("admin/install_contest")
    public ResponseEntity<?> updateLanguageContest(@RequestBody ContestInstallDTO contestInstallDTO){
        try{
            contestService.install(contestInstallDTO);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/admin/edit-problem-by-user")
    public ResponseEntity<?> updateProblemByUser(@RequestBody Map<String,String> map){
        try{
            contestService.updateProblemByUser(map);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
