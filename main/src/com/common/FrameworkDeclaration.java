package com.common;

import static com.common.CachedProperties.dataInstance;
import static com.common.CachedProperties.instance;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.selenium.SeleneseTestBase;

public class FrameworkDeclaration extends SeleneseTestBase {



	//public RemoteWebDriver driver;
	public CachedProperties cachedProperties = instance();
	public CachedProperties data = dataInstance();

	public boolean screenshot=Boolean.parseBoolean(cachedProperties.value("screenshot"));

	public HashMap < String, String > Metrics_readValuesFromCSV() {

		InputStream fin = this.getClass().getClassLoader().getResourceAsStream("Metrics/Metrics_OR.csv");

		DataInputStream in = new DataInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		HashMap < String, String > values = new HashMap < String, String >();

		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				if ( !strLine.startsWith("###") ) {
					String tokens[] = strLine.split(";");
					values.put(tokens[0].trim(), tokens[1].trim());
				}
			}
			in.close();
		}
		catch (Exception e) {

			System.out.println("please check if the space is ter in OR");
		}
		return values;
	}





	public String getTime() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timeDate = dateFormat.format(date);
		return timeDate;
	}



	public String putDate(int days) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +days);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("dd/MM/yyyy").format(s);
		return dateString;
	}

	public String putLogDate() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
		return dateString;
	}
	public String putuser() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("EEEddMMMhhmmss").format(s);
		return dateString;
	}


	public static void writeTemplateData(int templateWriteStartColumnNumber, int templateWriteToRowNumber, int readDataFromCol, String fileToRead) throws FileNotFoundException,
	IOException, InvalidFormatException {
		FileInputStream fs=new FileInputStream (fileToRead);
		Workbook wb = WorkbookFactory.create(fs);
		Row row = wb.getSheet("Sheet1").createRow(templateWriteToRowNumber);
		int col=templateWriteStartColumnNumber;
		int lastRowCount=getExcelRowCount("Sheet1");
		for (int i=1;i<lastRowCount+1;i++){
			String excelData=getExcelData("Sheet1", i, readDataFromCol);
			row.createCell(col).setCellValue(excelData);	
			col++;			
		}
		FileOutputStream fos=new FileOutputStream(fileToRead);
		wb.write(fos);
	}


	public static String getExcelData(String SheetName,int row, int col){
		String excelData;
		try{        
			FileInputStream fs = new FileInputStream ("./resources/metricsdata/metricsTemplateDatas.xlsx");
			Workbook wb = WorkbookFactory.create(fs);
			excelData =wb.getSheet(SheetName).getRow(row).getCell(col).getStringCellValue();
		}
		catch(Exception e){
			return "";
		}
		return excelData;
	}


	public static int getExcelRowCount(String SheetName){
		try{        
			FileInputStream fs = new FileInputStream ("./resources/metricsdata/metricsTemplateDatas.xlsx");
			Workbook wb = WorkbookFactory.create(fs);
			return wb.getSheet(SheetName).getLastRowNum();
		}
		catch(Exception e){
			return 0;
		}
	}
	
	
	public void compareExcel(String excel1, String excel2) {
		try
		{
			 FileInputStream file = new FileInputStream(new File(excel1));
			FileInputStream file1 = new FileInputStream(new File(excel2));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFWorkbook workbook1 = new XSSFWorkbook(file1);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFSheet sheet1 = workbook1.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Iterator<Row> rowIterator1 = sheet1.iterator();
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				Row row1 = rowIterator1.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				Iterator<Cell> cellIterator1 = row1.cellIterator();

				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					Cell cell1 = cellIterator1.next();
					//Check the cell type and format accordingly
					switch (cell.getCellType()) 
					{
					case Cell.CELL_TYPE_NUMERIC:
						if(cell.getNumericCellValue()==cell1.getNumericCellValue()){

						}else{
							System.out.print(cell.getNumericCellValue() +"===="+cell1.getNumericCellValue());
						}

						break;
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue().equals(cell1.getStringCellValue())){

						}else{
							System.out.print(cell.getStringCellValue() +"===="+cell1.getStringCellValue());
						}

						break;
					}
				}
			}
			file.close();
			file1.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String  getStringFromXL(String filePath, int row, int cel) throws FileNotFoundException, IOException,
	InvalidFormatException {
FileInputStream input_document = new FileInputStream(new File(filePath));//"F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xlsx"
Workbook wb = WorkbookFactory.create(input_document);
   Cell cell = null; 
   cell = wb.getSheet("Sheet1").getRow(row).getCell(cel);
   System.out.println( cell.getStringCellValue());	  
   input_document.close();
return cell.getStringCellValue();
}



public int getIntegerFromXL(String filePath, int row, int cel) throws FileNotFoundException, IOException,
	InvalidFormatException {
FileInputStream input_document = new FileInputStream(new File(filePath));//"F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xlsx"
Workbook wb = WorkbookFactory.create(input_document);
   Cell cell = null; 
   cell = wb.getSheet("Sheet1").getRow(row).getCell(cel);
   System.out.println( cell.getNumericCellValue());
   input_document.close();
return (int) cell.getNumericCellValue();
}


}
