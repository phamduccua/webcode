package com.project1.utils;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.TestCaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static java.lang.Math.max;
@Service
@Transactional
public class RunCode1 {
    @PersistenceContext
    private EntityManager entityManager;
    private final String path_init = "/opt/apache-tomcat-10.1.34/webapps/judge1/submissions/";
    private final String path_judge = "/opt/apache-tomcat-10.1.34/webapps/judge1/docker/";
    private String execute(
            String path,
            String outputName,
            List<String> listInput,
            String language,
            ProblemEntity problem,
            String fileName,
            String testFileName,
            String outputFileName,
            String timeMemoryfileName
    ) {
        String codeFileName = "solution." + language;
        StringBuilder str = new StringBuilder("docker run --rm ");
        if(listInput.size() > 0){
            for(String item : listInput){
                str.append(String.format("-v=\"%s\":/%s ",path + item,item));
            }
        }
        str.append(String.format("-v=\"%s\":/%s ",path + outputName,outputName));
        str.append(String.format("-v=\"%s\":/%s ",fileName,codeFileName));
        str.append(String.format("-v=\"%s\":/testcase.txt ",testFileName));
        str.append(String.format("-v=\"%s\":/output.txt ",outputFileName));
        str.append(String.format("-v=\"%s\":/timeMemory.txt ",timeMemoryfileName));
        str.append(String.format("online-judge1 %s /output.txt /timeMemory.txt %s %s ",language,problem.getTime_limit() + 0.01,problem.getMemory_limit()));
        return str.toString();
    }

    public void run(SubmissionEntity submissionEntity) {
        SubmissionEntity submission = submissionEntity;
        ProblemEntity problem = submission.getProblem();
        String path = path_init + submission.getId() + "/";
        String code = submission.getSubmitted();
        String fileName = path + "solution." + submission.getLanguage();
        String testFileName = path + "testcase.txt";
        String outputFileName = path + "output.txt";
        String timeMemoryfileName = path + "timeMemory.txt";
        List<TestCaseEntity> allTestCases = problem.getTestCases();

        try {
            Files.createDirectories(Paths.get(path));
            Files.createFile(Paths.get(outputFileName));
            Files.createFile(Paths.get(fileName));
            Files.createFile(Paths.get(timeMemoryfileName));
            Files.createFile(Paths.get(testFileName));
            boolean ok = true;
            for (TestCaseEntity testCase : allTestCases) {
                Files.writeString(Paths.get(fileName),code);
                List<String> listFileInput = new ArrayList<>();
                if(testCase.getType().equals("file")){
                    List<String> list = ReplaceFileName.newFileName(testCase.getInputs(),path);
                    listFileInput.addAll(list);
                    CreateFile.createFileTemp(list,path_judge);
                    if(!testCase.getOutputFileName().equals("std") && !testCase.getOutputFileName().equals("output.txt")){
                        Files.createFile(Paths.get(path + testCase.getOutputFileName()));
                        Files.createFile(Paths.get(path_judge + testCase.getOutputFileName()));
                    }

                }
                else{
                    Files.writeString(Paths.get(testFileName), testCase.getInputs().get(0).getContentFile());
                }
                ProcessBuilder pb = new ProcessBuilder("bash", "-c", execute(path,testCase.getOutputFileName(),listFileInput,submission.getLanguage(), problem, fileName, testFileName, outputFileName, timeMemoryfileName));
                System.out.println(execute(path,testCase.getOutputFileName(),listFileInput,submission.getLanguage(), problem, fileName, testFileName, outputFileName, timeMemoryfileName));
                pb.directory(new File(path_init));
                Process process = pb.start();
                process.waitFor();
                String expectedOutput = testCase.getExpctedOutputFileContent().trim();
                String actualOutput = "";
                if(!testCase.getOutputFileName().equals("std") && !testCase.getOutputFileName().equals("output.txt")){
                    actualOutput = Files.readString(Paths.get(path + testCase.getOutputFileName())).trim();
                }
                else{
                    actualOutput = Files.readString(Paths.get(outputFileName)).trim();
                }
                String timeMemoryOutput = Files.readString(Paths.get(timeMemoryfileName)).trim();
                SubmissionEntity sub = SusscessUtils.isSucess(submission,actualOutput,timeMemoryOutput,expectedOutput);
                if(sub.getStatus() != null && sub.getStatus().equals("false")){
                    ok = false;
                    if(testCase.getType().equals("file")){
                        DeleteFile.deleteFileTemp(listFileInput,path_judge);
                        if(!testCase.getOutputFileName().equals("std") && !testCase.getOutputFileName().equals("output.txt")){
                            File file = new File(path_judge + testCase.getOutputFileName());
                            file.delete();
                        }
                    }
                    break;
                }
                if(testCase.getType().equals("file") && ok == true){
                    DeleteFile.deleteFileTemp(listFileInput,path_judge);
                    if(!testCase.getOutputFileName().equals("std") && !testCase.getOutputFileName().equals("output.txt")){
                        File file = new File(path_judge + testCase.getOutputFileName());
                        file.delete();
                    }
                }
            }
            if (ok) {
                submission.setStatus("true");
                submission.setCode("AC");
            }
            entityManager.merge(submission);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            deleteDirectory(Paths.get(path));
        }
    }

    private void deleteDirectory(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.delete()) {
                            System.err.println("Failed to delete: " + file.getAbsolutePath());
                        }
                    });
            System.out.println("Deleted directory: " + path);
        } catch (IOException e) {
            System.err.println("Error deleting directory: " + path);
            e.printStackTrace();
        }
    }
}

