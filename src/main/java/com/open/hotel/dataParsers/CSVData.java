package com.open.hotel.dataParsers;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class CSVData {

	org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

	public CSVData() {

	}


	public HashMap readData(String csvFileName, String testCaseID){

		HashMap<Integer, String> data = new LinkedHashMap();
		HashMap<String, String> data1 = new LinkedHashMap();
		int counter = 1;
		String valueName = null;
		try{
			String filePath = System.getProperty("user.dir") + "//src//test//resources//Data//" + csvFileName;
			Reader in = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			
			for(Iterator var2 = records.iterator(); var2.hasNext(); ++counter){
				CSVRecord record = (CSVRecord)var2.next();
				int j;
				if(counter == 1){
					for(j = 0; j < record.size();++j){
						data.put(j,record.get(j));
					}
				}
				if(testCaseID.equalsIgnoreCase(record.get(0))){
					for(j = 0; j < data.size();++j){
						try{
							if(record.get(j).trim().startsWith("#")){
								valueName = record.get(j).substring(1);
								data1.put(data.get(j),valueName);
							}else{
								data1.put(data.get(j),record.get(j));
							}
						}catch(Exception var3){
							data.put(j, (String)null);
						}
					}
				}
			}
		} catch(IOException val1){
			log.info("Exception " + val1);
		}
		return data1;
	}

}
