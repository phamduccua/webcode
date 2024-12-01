package com.project1.api.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.request.SubmitRequest;
import com.project1.model.response.OutputResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunCode {
    public OutputResponse runCode(SubmitRequest submitRequest) {
        try {
            String clientId = submitRequest.getClientId(); // Replace with your client ID
            String clientSecret = submitRequest.getClientSecret(); // Replace with your client secret
            String script = submitRequest.getScript();
            String stdin = (submitRequest.getStdin() != null) ? submitRequest.getStdin() : "";
            String language = "python3";
            String versionIndex = "0";
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + "\"" + script.replace("\"", "\\\"") + "\"" +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\",\"stdin\":\"" + "\"" + stdin.replace("\"", "\\\"") + "\"}";

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            OutputResponse outputResponse = objectMapper.readValue(connection.getInputStream(), OutputResponse.class);
            return outputResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
