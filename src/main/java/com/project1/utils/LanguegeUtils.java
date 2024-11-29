package com.project1.utils;

import com.project1.entity.enums.language;

import java.util.*;

public class LanguegeUtils {
    public static List<String> language(List<String> lang){
        TreeMap<String,String> enums = language.type();
        List<String> result = new ArrayList<>();
        for(String it : lang){
            result.add(enums.get(it));
        }
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return result;
    }
}
