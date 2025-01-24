package com.project1.api.admin;

import com.project1.model.dto.ProblemDTO;
import com.project1.service.AddProblemService;
import com.project1.service.DeleteProblemService;
import com.project1.service.EditProblemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public ResponseEntity<?> addOrUpdateProblem(@RequestBody ProblemDTO problemDTO, HttpServletRequest request) {
        try {
            if (problemDTO.getId() == null) {
                addProblemService.addProblem(problemDTO,request);
            } else {
                editProblemService.updateProblem(problemDTO,request);
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
    private final String url = "D:/webcode/src/main/images/";
    @PostMapping("/upload/images")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        try {
            if(file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");
            }
            String uniqueName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis();
            String extension = getExtension(file.getOriginalFilename());
            String newFileName = uniqueName + "." + extension;
            File destinationFile = new File(url + newFileName);
            file.transferTo(destinationFile);
            return ResponseEntity.ok(newFileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
    private String getExtension(String filename) {
        return filename != null && filename.contains(".") ? filename.substring(filename.lastIndexOf(".") + 1) : "";
    }

    @DeleteMapping("/delete-image/{name}")
    public ResponseEntity<?> deleteImage(@PathVariable String name) {
        try{
            File file = new File(url + name);
            file.delete();
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File delete failed");
        }
    }
}