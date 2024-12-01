//package com.project1.converter;
//
//import com.project1.model.response.OutputResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OutputResponseConverter {
//    public OutputResponse parseJson(String json) {
//        OutputResponse response = new OutputResponse();
//
//        String output = extractValue(json, "\"output\":");
//        response.setOutput(output);
//
//        String error = extractValue(json, "\"error\":");
//        response.setError(error);
//
//        String statusCodeStr = extractValue(json, "\"statusCode\":");
//        response.setStatusCode(Integer.parseInt(statusCodeStr));
//
//        String memory = extractValue(json, "\"memory\":");
//        response.setMemory(memory);
//
//        String cpuTime = extractValue(json, "\"cpuTime\":");
//        response.setCpuTime(cpuTime);
//
//        String compilationStatus = extractValue(json, "\"compilationStatus\":");
//        response.setCompilationStatus(compilationStatus);
//
//        String projectKey = extractValue(json, "\"projectKey\":");
//        response.setProjectKey(projectKey);
//
//
//        String isExecutionSuccessStr = extractValue(json, "\"isExecutionSuccess\":");
//        response.setExecutionSuccess(Boolean.parseBoolean(isExecutionSuccessStr));
//
//
//        String isCompiledStr = extractValue(json, "\"isCompiled\":");
//        response.setCompiled(Boolean.parseBoolean(isCompiledStr));
//
//        return response;
//    }
//
//    private static String extractValue(String json, String key) {
//        int startIndex = json.indexOf(key) + key.length();
//        if (startIndex == -1) return null;
//
//        int endIndex = json.indexOf(",", startIndex);
//        if (endIndex == -1) {
//            endIndex = json.indexOf("}", startIndex);
//        }
//
//        return json.substring(startIndex, endIndex).trim().replaceAll("\"", "");
//    }
//}
