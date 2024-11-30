package com.project1.utils;

import com.project1.entity.enums.language;

import java.util.*;

public class LanguageUtils {
    public static List<String> listLanguage(List<String> lang){
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
    public static String unLanguage(String language){
        return switch (language) {
            case "c" -> "C";
            case "cpp17" -> "C/C++";
            case "java" -> "Java";
            case "python3" -> "Python3";
            default -> null;
        };
    }

    public static String language(String language){
        return switch (language) {
            case "C" -> "c";
            case "C/C++" -> "cpp17";
            case "Java" -> "java";
            case "Python3" -> "python3";
            default -> null;
        };
    }
}
