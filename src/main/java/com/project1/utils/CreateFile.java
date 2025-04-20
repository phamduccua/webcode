package com.project1.utils;

import com.project1.model.dto.FileSubmission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class CreateFile {
    public static void createFile(List<FileSubmission> list,String path) throws IOException {
        for(FileSubmission file : list){
            Files.createFile(Paths.get(path + file.getFileName()));
            Files.writeString(Paths.get(path + file.getFileName()),file.getContentFile());
        }
    }
    public static void createFileTemp(List<String> list,String path) throws IOException {
        for(String item : list){
            Files.createFile(Paths.get(path + item));
        }
    }
}
