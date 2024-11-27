package com.project1.api.admin;


import com.project1.model.dto.TestCaseDTO;
import com.project1.service.AddOrUpdateTestCaseService;
import com.project1.service.DeleteTestCaseService;
import com.project1.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/testcase/")

public class TestCaseAPI {
    @Autowired
    private AddOrUpdateTestCaseService addOrUpdateTestCaseService;
    @Autowired
    private DeleteTestCaseService deleteTestCaseService;
    @PostMapping
    public ResponseEntity<Void> addOrUpdateTestCase(@RequestBody TestCaseDTO testCaseDTO){
        addOrUpdateTestCaseService.addOrUpdateTestCase(testCaseDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("delete-item/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable("id") Long id){
        deleteTestCaseService.deleteTestCase(id);
        return ResponseEntity.ok().build();
    }
}
