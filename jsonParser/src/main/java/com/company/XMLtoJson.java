package com.company;



import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// import javax.sound.sampled.SourceDataLine;


public class XMLtoJson {

    public static void main(String[] args) throws Exception {


        try{

            String xmlFile = "C:\\Users\\redon\\Desktop\\jsonParser\\src\\main\\java\\test\\resources\\camt27.xml";
            String xml = readFileAsString(xmlFile);
            // System.out.println("The Camt 027 in XML format: ");
            // System.out.println(xml);

            JSONObject jsonObject = XML.toJSONObject(xml.replaceAll("\\r\\n\\s+|BBkOQF:",""));
            //jsonObject = XML.toJSONObject(xml.replaceAll("BBkOQF:",""));
            String xmlToJson = jsonObject.toString(4);
            System.out.println("==================================================\n");
            // System.out.println("The Camt 027 in JSON format: ");
            // System.out.println(xmlToJson);
            FileWriter fw = new FileWriter("C:\\Users\\redon\\Desktop\\jsonParser\\src\\main\\java\\test\\resources\\writtenJson.json");
            fw.write(xmlToJson.formatted("\n"));
            fw.close();
            String sendingInstitution = jsonObject.getJSONObject("BBkOQFBlkCdtTrf").get("SndgInst").toString();
            String receivingInstitution = jsonObject.getJSONObject("BBkOQFBlkCdtTrf").get("RcvgInst").toString();
            String messageID = jsonObject.getJSONObject("BBkOQFBlkCdtTrf").getJSONObject("ClmNonRct").getJSONObject("Undrlyg").getJSONObject("IntrBk").getJSONObject("OrgnlGrpInf").get("OrgnlMsgId").toString();
            String messageNameID = jsonObject.getJSONObject("BBkOQFBlkCdtTrf").getJSONObject("ClmNonRct").getJSONObject("Undrlyg").getJSONObject("IntrBk").getJSONObject("OrgnlGrpInf").get("OrgnlMsgNmId").toString();
            System.out.println("This camt027 was send by: "+sendingInstitution);
            System.out.println("This camt027 was received by: "+ receivingInstitution);
            System.out.println("The camt027 original message ID: "+ messageID);
            System.out.println("The camt027 original message name ID: "+ messageNameID);
            System.out.println("JSON File created");

            try{

                String newFile = "C:\\Users\\redon\\Desktop\\jsonParser\\src\\main\\java\\test\\resources\\outputFile.txt";
                File outputFile = new File(newFile);
                FileWriter fw1 = new FileWriter(outputFile);
                fw1.write("This camt027 was send by: "+sendingInstitution+"\n");
                fw1.write("This camt027 was received by: "+ receivingInstitution+"\n");
                fw1.write("The camt027 original message ID: "+ messageID+"\n");
                fw1.write("The camt027 original message name ID: "+ messageNameID+"\n");
                fw1.close();

            }catch(Exception ex)
            {
                System.out.println(ex);
            }

        }catch(JSONException ex)
        {
            System.out.println(ex);
            System.out.println("JSON File NOT created");
        }

    }


    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}

//https://stackoverflow.com/questions/38576797/in-java-how-do-i-overwrite-a-specific-part-of-a-line-in-a-file
