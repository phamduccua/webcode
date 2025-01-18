package com.project1.utils;
import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import static java.lang.Math.max;
public class SusscessUtils {
    public static SubmissionEntity isSucess(SubmissionEntity submission,String outPut,String timeAndMemo,String output_ex) {
        String[] out = outPut.split("\n");
        ProblemEntity problem = submission.getProblem();
        if(out[out.length-1].equals("COMPILATION ERROR")) {
            StringBuilder error = new StringBuilder();
            for(int i = 0;i< out.length - 1;i++) {
                error.append(out[i]).append("\n");
            }
            submission.setError(error.toString());
            submission.setStatus("false");
            submission.setCode("CE");
        }
        else{
            String[] arr = timeAndMemo.split("\n");
            String timeAndMom = arr[arr.length-1];
            String[] array = timeAndMom.split(" ");
            Double time = Double.parseDouble(array[array.length - 2]);
            Long memory = Long.parseLong(array[array.length - 1]);
            if(submission.getMemoryUsed() == null){
                submission.setMemoryUsed(memory);
            }
            else{
                submission.setMemoryUsed(max(submission.getMemoryUsed(), memory));
            }
            if(submission.getExecutionTime() == null){
                submission.setExecutionTime(time);
            }
            else{
                submission.setExecutionTime(max(submission.getMemoryUsed(), time));
            }
            if (time > problem.getTime_limit()) {
                submission.setExecutionTime(problem.getTime_limit());
                submission.setStatus("false");
                submission.setCode("TLE");
            } else if (memory > problem.getMemory_limit()) {
                submission.setStatus("false");
                submission.setCode("MLE");
            }else if (outPut.contains("RUNTIME ERROR")) {
                submission.setStatus("false");
                submission.setCode("RTE");
            } else if (!outPut.equals(output_ex)) {
                submission.setStatus("false");
                submission.setCode("WA");
            }
        }
        return submission;
    }
}
