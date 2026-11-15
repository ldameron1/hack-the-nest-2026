/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
//https://www.geeksforgeeks.org/java/types-of-exception-in-java-with-examples/ refrenced for exception information

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
/**
 *
 * @author ld
 */
public class GeminiConnector {
    public static String askTest() throws MissingKeyException
    {
       if (System.getenv("GEMINI_API_KEY") == null)
       {
           throw new MissingKeyException("Error: Gemini API key not set. Please set the key as an enviornment variable (setx for Windows, export for Linux.)");
       }
       String apiKey = System.getenv("GEMINI_API_KEY");
       try {
           HttpClient client = HttpClient.newHttpClient();
           .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent"))
                   .header()
           return response.body();
       }
       catch (Exception e)
       {
       
           return "Error: " + e.getMessage();
       }
       // return null;
    }
}

class MissingKeyException extends Exception {
    public MissingKeyException(String message) {
        super(message);
    }
}
