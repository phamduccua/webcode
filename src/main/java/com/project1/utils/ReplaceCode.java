package com.project1.utils;

import com.project1.entity.InputEntity;

import java.util.List;

public class ReplaceCode {
    public static String replace(String code, List<InputEntity> list,String ouputName) {
        for(InputEntity item : list){
            code = code.replace(item.getFileName(),item.getFileName().substring(0,item.getFileName().lastIndexOf(".")) + ".txt");
        }
        if(!ouputName.equals("std")){
            code = code.replace(ouputName,ouputName.substring(0,ouputName.lastIndexOf(".")) + ".txt");
        }
        return code;
    }
}
