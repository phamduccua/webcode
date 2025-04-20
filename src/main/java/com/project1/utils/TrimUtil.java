package com.project1.utils;

import java.util.StringTokenizer;

public class TrimUtil {
    public static String Trim(String s){
        StringTokenizer str = new StringTokenizer(s,"\n");
        StringBuilder stbd =  new StringBuilder();
        while(str.hasMoreTokens()){
            stbd.append(str.nextToken().trim() + "\n");
        }
        return stbd.toString();
    }
}
