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
@RequestMapping("/admin/problem")
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
            if (problemDTO.getId() == null) {
                addProblemService.addProblem(problemDTO);
            } else {
                editProblemService.updateProblem(problemDTO);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        deleteProblemService.deleteProblem(id);
        return ResponseEntity.ok().build();
    }
}