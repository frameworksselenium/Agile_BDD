package com.open.hotel.services;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Payload {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    public Payload() {
    }

   public String payLoadPreparation(String requestTemplateName, Map<String, String> testData) {
       String val = "";
       BufferedReader bReader = null;
       FileReader reader = null;
       try {
           String sourcePath = System.getProperty("user.dir") + "\\src\\test\\resources\\templates\\" + requestTemplateName + ".txt";
           reader = new FileReader(sourcePath);
           bReader = new BufferedReader(reader);
           String newLine = System.lineSeparator();
           String temp = "";
           while ((temp = bReader.readLine()) != null) {
               val = val + temp + newLine;
           }
           // Map<String, String> testData = (Map<String, String>)testData;

           Set<String> keyList = testData.keySet();
           String data = "empty";
           Iterator var6 = keyList.iterator();

           while (var6.hasNext()) {
               String key = (String) var6.next();
               try {
                   Map<String, String> testData1 = (Map<String, String>) testData;
                   data = (String) testData1.get(key);
               } catch (Exception var18) {
                   log.info(var18.getMessage());
               }
               if (data != null) {
                   val = val.replace("${" + key + "}", data);
               }
           }
           log.info("XML Request After replacing values : " + val);
       }catch (Exception e){
           new RuntimeException("Not able to replace values");
       }
       return val;
   }
}
