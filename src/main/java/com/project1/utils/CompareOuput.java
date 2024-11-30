package com.project1.utils;

public class CompareOuput {
    public static boolean compareOutput(String output,String spected_output){
        String[] outputLines = output.split("\n");
        String[] expectedOutputLines = spected_output.split("\n");

        if(outputLines.length != expectedOutputLines.length){
            return false;
        }

        for(int i = 0; i < outputLines.length; i++){
            if(!outputLines[i].trim().equals(expectedOutputLines[i].trim())){
                return false;
            }
        }
        return true;
    }
}
