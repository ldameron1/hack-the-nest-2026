package com.mycompany.mavenproject2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiConnector {

    public static String askTest() throws MissingKeyException {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null) {
            throw new MissingKeyException("Error: GEMINI_API_KEY not set (setx on Windows or export in Linux/WSL).");
        }

        String jsonBody = """
                {"contents":[{"parts":[{"text":"Reply with just: 1|Bring it on!"}]}]}
                """;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent"))
                    .header("Content-Type", "application/json")
                    .header("x-goog-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

