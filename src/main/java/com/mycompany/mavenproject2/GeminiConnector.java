/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
//https://www.geeksforgeeks.org/java/types-of-exception-in-java-with-examples/ refrenced for exception
/**
 *
 * @author ld
 */
public class GeminiConnector {
    public static String askTest()
    {
       if (System.getenv("GEMINI_API_KEY") == null)
       {
           throw new MissingKeyException("Error: Gemini API key not set. Please set the key as an enviornment variable.");
       }
       String apiKey = Syste.
    }
}

class MissingKeyException extends Exception {
    public MissingKeyException(String message) {
        super(message);
    }
}
