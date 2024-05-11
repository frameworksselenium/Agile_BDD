package com.open.hotel.dataParsers;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;


public class ExcelData {
	Logger log;

	public HashMap<String, String> readData(String fileName, String sheetName, String key, String value) throws IOException {
		//String fileName = "TestData.xlsx";
		String filePath = System.getProperty("user.dir") + "//src//test//resources//data//" + fileName;
		File path = new File(filePath);
		boolean flag = path.exists();
		if (!flag) {
			path = new File(filePath);
		}
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		HashMap<String, String> data = new HashMap();
		int cellValue = 0;
		int rowValue = 0;
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int cellCount = row.getPhysicalNumberOfCells();
		
		int i = 0;
		for (i = 0; i < cellCount; ++i) {
			if(row.getCell(i).getStringCellValue().trim().equals(key.trim())) {
				cellValue = i;
				break;
			}
		}
		
		for (i = 0; i < cellCount; ++i) {
			row = sheet.getRow(i);
			if(row.getCell(cellValue).getStringCellValue().trim().equals(value.trim())) {
				rowValue = i;
				break;
			}
		}
		
		for (i = 0; i < cellCount; ++i) {
			XSSFRow rowheader = sheet.getRow(0);
			String keyName = rowheader.getCell(i).getStringCellValue().trim();
			row = sheet.getRow(rowValue);
			String valueName = null;
			try {
				if(row.getCell(i).getStringCellValue().trim().startsWith("#")) {
					valueName  = row.getCell(i).getStringCellValue().substring(1);
				}else {
					valueName  = row.getCell(i).getStringCellValue().trim();
				}
				data.put(keyName, valueName);
			}catch (Exception var1) {
				log.info("Exception - " + var1);
			}
		}
		return data;
	}


}
