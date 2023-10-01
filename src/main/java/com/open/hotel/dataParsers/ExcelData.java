package com.open.hotel.dataParsers;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;


public class ExcelData {

	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	public FileInputStream fis = null;
	public XSSFRow rowheader = null;
	public FileOutputStream outPut_File = null;
	org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

	public ExcelData() {

	}

	public HashMap<String, String> readData(String fileName, String sheetName, String ColumnName, String testCaseID) throws IOException {

		HashMap<String, String> data = new HashMap();
		try {
			String filePath = System.getProperty("user.dir") + "//src//test//resources//data//" + fileName;
			//ClassLoader classLoader = this.getClass().getClassLoader();
			File path = new File(filePath);
			boolean flag = path.exists();
			if (!flag) {
				path = new File(filePath);
			}
			this.fis = new FileInputStream(path);
			this.workbook = new XSSFWorkbook(this.fis);
			this.sheet = this.workbook.getSheet(sheetName);


			int cellValue = 0;
			int rowValue = 0;
			int rowCount = this.sheet.getPhysicalNumberOfRows();
			this.row = this.sheet.getRow(0);
			int cellCount = this.row.getPhysicalNumberOfCells();

			int i = 0;
			for (i = 0; i < cellCount; ++i) {
				if (this.row.getCell(i).getStringCellValue().trim().equals(ColumnName.trim())) {
					cellValue = i;
					break;
				}
			}

			for (i = 0; i < cellCount; ++i) {
				this.row = this.sheet.getRow(i);
				if (this.row.getCell(cellValue).getStringCellValue().trim().equals(testCaseID.trim())) {
					rowValue = i;
					break;
				}
			}

			for (i = 0; i < cellCount; ++i) {
				this.rowheader = this.sheet.getRow(0);
				String keyName = this.rowheader.getCell(i).getStringCellValue().trim();
				this.row = this.sheet.getRow(rowValue);
				String valueName = null;
				try {
					if (this.row.getCell(i).getStringCellValue().trim().startsWith("#")) {
						valueName = this.row.getCell(i).getStringCellValue().substring(1);
					} else {
						valueName = this.row.getCell(i).getStringCellValue().trim();
					}
					data.put(keyName, valueName);
				} catch (Exception var1) {
					log.info("Exception - " + var1);
				}
			}
		}catch (Exception e){
			System.out.println("Error in reading excel sheet");
		}
		return data;
	}
	
}
