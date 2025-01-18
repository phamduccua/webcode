package com.project1.utils;

import java.io.File;
import java.util.List;

public class DeleteFile {
    public static void deleteFileTemp(List<String> list, String path){
        for(String item : list){
            File file = new File(path + item);
            file.delete();
        }
    }
}
