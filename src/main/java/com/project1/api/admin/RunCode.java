package com.project1.api.admin;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class RunCode {
    public String runCode() {
        String clientId = "your_client_id_here"; // Replace with your client ID
        String clientSecret = "your_client_secret_here"; // Replace with your client secret
        String script = "print('Hello, World!')";
        String language = "python3";
        String versionIndex = "0";

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"}";

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
            connection.disconnect();
            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
