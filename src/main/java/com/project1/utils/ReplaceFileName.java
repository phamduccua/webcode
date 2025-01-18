package com.project1.utils;

import com.project1.entity.InputEntity;
import com.project1.model.dto.FileSubmission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReplaceFileName {
    public static List<String> newFileName(List<InputEntity> listInput,String path) throws IOException {
        List<String> result = new ArrayList<>();
        List<FileSubmission> listFile = new ArrayList<>();
        for(InputEntity item : listInput){
            FileSubmission fileSubmission = new FileSubmission();
            String name = item.getFileName().substring(0,item.getFileName().lastIndexOf(".")) + ".txt";
            fileSubmission.setFileName(name);
            fileSubmission.setContentFile(item.getContentFile());
            listFile.add(fileSubmission);
            result.add(fileSubmission.getFileName());
        }
        CreateFile.createFile(listFile,path);
        return result;
    }
}
