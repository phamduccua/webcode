package com.project1.entity.enums;

import java.util.TreeMap;

public enum language {
    c("C"),
    cpp("C/C++"),
    java("Java"),
    py("Python3");

    private final String name;
    language(String name) {
        this.name = name;
    }

    public static TreeMap<String,String> type(){
        TreeMap<String,String> map = new TreeMap<>();
        for(language lang : language.values()){
            map.put(lang.toString(),lang.name);
        }
        return map;
    }
}
