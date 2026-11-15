package com.mycompany.mavenproject2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class GeminiConnector {

    public static String selectAction(Fighter player, Fighter enemy) throws MissingKeyException {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null) {
            throw new MissingKeyException("Error: GEMINI_API_KEY not set (setx on Windows or export in Linux/WSL).");
        }
  
        

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent"))
                    .header("Content-Type", "application/json")
                    .header("x-goog-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
                 // we re just using the method to parse the reply we got back and then searching toriugh it to find the correct elment?
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
           JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
           JsonObject firstCandidate = root.getAsJsonArray("candidates").get(0).getAsJsonObject();
           JsonObject content = firstCandidate.getAsJsonObject("content");
           JsonObject firstPart = content.getAsJsonArray("parts").get(0).getAsJsonObject();
           return firstPart.get("text").getAsString().trim();
         
            
            
            
        } catch (Exception e) {
            return "FALLBACK";
        }
    }
}

