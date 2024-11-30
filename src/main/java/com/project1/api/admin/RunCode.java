package com.project1.api.admin;
import com.project1.model.request.SubmitRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RunCode {
    public static void runCode(SubmitRequest submitRequest) {
        String clientId = submitRequest.getClientId(); // Replace with your client ID
        String clientSecret = submitRequest.getClientSecret(); // Replace with your client secret
        String script = "a = input();\nprint('Hello, ' + a)";
        String language = "python3";
        String versionIndex = "0";
        String stdin = "World"; // Input to the script

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Modify input to include stdin
            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\",\"stdin\":\"" + stdin + "\"}";

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                response.append(output);
            }

            String responseJson = response.toString(); // Parse the response JSON
            System.out.println("Execution response: " + responseJson);

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
