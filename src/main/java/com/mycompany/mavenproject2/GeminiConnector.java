package com.mycompany.mavenproject2;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class GeminiConnector {

    public static String selectAction(Fighter player, Fighter enemy) throws MissingKeyException {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null) {
            throw new MissingKeyException("Error: GEMINI_API_KEY not set (setx on Windows or export in Linux/WSL).");
        }
  
        //String prompt = "You are an enemy in a simple turn based combat video game. Your goal is to strategically utilize your reources (the ability to fight, and the ability to heal) to beat your opponent. Your stats are as follows: Current HP: " + enemy.hp + "Max HP: " + maxHp + "Heals Remaining: " + enemy.healsLeft + ". Your opponent, the player, has the following HP Remaning: " + player.hp + ". Select your move by responding with 1 alone for attack or 2 alone for heal. ");
        //since this is parsable, I allowed gemni to create this prompt for me
        String prompt = "You are the enemy in a turn-based combat game. "
        + "Your stats -> HP: " + enemy.hp + "/" + enemy.maxHp
        + ", Heals left: " + enemy.healsLeft + " (each heal restores +5 HP, cannot exceed max HP)"
        + ", Attack Power: " + enemy.attackPower + " (deals 1 to " + (enemy.attackPower) + " damage). "
        + "Player stats -> HP: " + player.hp + "/" + player.maxHp
        + ", Heals left: " + player.healsLeft
        + ", Attack Power:  " + player.attackPower + ". "
        + "Respond ONLY with the single digit 1 (to attack) or 2 (to heal), with no extra words or punctuation.";
        
        
            //hSONoBJECT lol
            JsonObject textBox = new JsonObject();
            textBox.addProperty("text", prompt);
            
            JsonArray parts = new JsonArray();
            parts.add(textBox);
            
            JsonObject message = new JsonObject();
            message.add("parts",parts);
            
            JsonArray contents = new JsonArray();
            contents.add(message);

            JsonObject requestBody = new JsonObject();
            requestBody.add("contents", contents);

            String jsonBody = requestBody.toString();

            
            
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

