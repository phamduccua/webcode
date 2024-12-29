package com.project1.api.admin;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.utils.SusscessUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import static java.lang.Math.max;
@Service
@Transactional
public class RunCode {
    @PersistenceContext
    private EntityManager entityManager;
    private final String path_init = "D:/webcode/src/main/judge/submissions/";

    private String execute(
            String language,
            ProblemEntity problem,
            String fileName,
            String testFileName,
            String outputFileName,
            String timeMemoryfileName
    ) {
        String codeFileName = "solution." + language;
        return String.format(
                "docker run --rm " +
                        "-v=\"%s\":/%s " +
                        "-v=\"%s\":/testcase.txt " +
                        "-v=\"%s\":/output.txt " +
                        "-v=\"%s\":/timeMemory.txt " +
                        "online-judge %s /output.txt /timeMemory.txt %s %s",
                fileName,
                codeFileName,
                testFileName,
                outputFileName,
                timeMemoryfileName,
                language,
                problem.getTime_limit(),
                problem.getMemory_limit()
        );
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
            Files.writeString(Paths.get(fileName), code);

            boolean ok = true;
            for (TestCaseEntity testCase : allTestCases) {
                Files.writeString(Paths.get(testFileName), testCase.getInput());
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", execute(submission.getLanguage(), problem, fileName, testFileName, outputFileName, timeMemoryfileName));
                System.out.println(execute(submission.getLanguage(), problem, fileName, testFileName, outputFileName, timeMemoryfileName));
                pb.directory(new File(path_init));
                Process process = pb.start();
                process.waitFor();

                String expectedOutput = testCase.getExpected_output().trim();
                String actualOutput = Files.readString(Paths.get(outputFileName)).trim();
                String timeMemoryOutput = Files.readString(Paths.get(timeMemoryfileName)).trim();
                SubmissionEntity sub = SusscessUtils.isSucess(submission,actualOutput,timeMemoryOutput,expectedOutput);
                if(sub.getStatus() != 0){
                    ok = false;
                    break;
                }
            }
            if (ok) {
                submission.setStatus(1);
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
                    .sorted(Comparator.reverseOrder()) // Xóa file trước, sau đó thư mục
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
