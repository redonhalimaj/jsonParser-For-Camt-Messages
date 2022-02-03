package com.company;

import  java.nio.file.Files;
import  java.nio.file.Paths;
import java.util.ArrayList;

import  org.json.*;

public class JsonParser {

    public static void main(String[] args) throws Exception {
	// write your code here
        String file  = "C:\\Users\\redon\\IdeaProjects\\jsonParser\\src\\main\\java\\test\\resources\\myFile.json";
        String json = readFileAsString(file);
        System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);
        System.out.println("=========================================================");
        System.out.println(jsonObject.get("age"));
        System.out.println(jsonObject.get("hobbies"));

        ArrayList<String> hobbiesList = new ArrayList<String>();
        JSONArray jsonArray = (JSONArray) jsonObject.get("hobbies");
        if(jsonArray != null){

            int listLength = jsonArray.length();
            for (int i = 0; i<listLength; i++)
            {
                hobbiesList.add(jsonArray.get(i).toString());
                System.out.println(hobbiesList);
            }
        }

        System.out.println(hobbiesList.get(0));

        System.out.println("=========================================================");


    }


    // Read and write the json file as String
    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
