package com.project1.api.admin;


import com.project1.model.dto.TestCaseDTO;
import com.project1.service.AddOrUpdateTestCaseService;
import com.project1.service.DeleteTestCaseService;
import com.project1.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/testcase")

public class TestCaseAPI {
    @Autowired
    private AddOrUpdateTestCaseService addOrUpdateTestCaseService;
    @Autowired
    private DeleteTestCaseService deleteTestCaseService;
    @Autowired
    private TestCaseService testCaseService;
    @PostMapping
    public ResponseEntity<Void> addOrUpdateTestCase(@RequestBody TestCaseDTO testCaseDTO){
        addOrUpdateTestCaseService.addOrUpdateTestCase(testCaseDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable("id") Long id){
        deleteTestCaseService.deleteTestCase(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/get/{id}")
    public ResponseEntity<?> getTestCase(@PathVariable("id") Long id){
        try{
            TestCaseDTO test = testCaseService.findById(id);
            return ResponseEntity.ok(test);
        } catch(Exception e){
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("/edit_example")
    public ResponseEntity<Void> editTestExample(@RequestBody Map<String,String> map){
        try{
            addOrUpdateTestCaseService.editTestExample(map);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.ok().build();
        }
    }
}
