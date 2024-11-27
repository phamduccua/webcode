package com.project1.api.admin;

import com.project1.model.dto.ProblemDTO;
import com.project1.service.AddProblemService;
import com.project1.service.DeleteProblemService;
import com.project1.service.EditProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController(value="problemAPIOfAdmin")
@RequestMapping("/admin/problem/")
public class ProblemAPI {
    @Autowired
    private AddProblemService addProblemService;
    @Autowired
    private EditProblemService editProblemService;
    @Autowired
    private DeleteProblemService deleteProblemService;
    @PostMapping
    public ResponseEntity<?> addOrUpdateProblem(@RequestBody ProblemDTO problemDTO) {
        try {
            Long problemId;
            if (problemDTO.getId() == null) {
                problemId = addProblemService.addProblem(problemDTO);
            } else {
                problemId = editProblemService.updateProblem(problemDTO);
            }
            return ResponseEntity.ok(problemId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Mã bài tập " + problemDTO.getCode() + " đã tồn tại !!!!!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
    }
    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        deleteProblemService.deleteProblem(id);
        return ResponseEntity.ok().build();
    }
}
