package com.project1.entity.enums;

import java.util.TreeMap;

public enum language {
    C("C"),
    CPP("C/C++"),
    Java("Java"),
    Python3("Python3");

    private final String name;
    private language(String name) {
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
