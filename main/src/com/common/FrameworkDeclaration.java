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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
	public static CachedProperties cachedProperties = instance();
	public CachedProperties data = dataInstance();

	public boolean screenshot=Boolean.parseBoolean(cachedProperties.value("screenshot"));
	public boolean gd=Boolean.parseBoolean(cachedProperties.value("GenerateData"));

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
	
	public static String  getStringFromXL(String filePath, String Sheetname,int row, int cel) throws FileNotFoundException, IOException,
	InvalidFormatException {
FileInputStream input_document = new FileInputStream(new File(filePath));//"F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xlsx"
Workbook wb = WorkbookFactory.create(input_document);
   Cell cell = null; 
   cell = wb.getSheet(Sheetname).getRow(row).getCell(cel);
   input_document.close();
return cell.getStringCellValue();
}



public static int getIntegerFromXL(String filePath, String Sheetname, int row, int cel) throws FileNotFoundException, IOException,
	InvalidFormatException {
FileInputStream input_document = new FileInputStream(new File(filePath));//"F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xlsx"
Workbook wb = WorkbookFactory.create(input_document);
   Cell cell = null; 
   cell = wb.getSheet(Sheetname).getRow(row).getCell(cel);
   input_document.close();
return (int) cell.getNumericCellValue();
}
public String getBit()
{

	String architecture = "os.arch";
	String bit =System.getProperty(architecture);
	System.out.println(bit);
	return bit;
}


public void  killIEInstances() throws IOException
{
	File file = new File(".");
	String filepath =file.getCanonicalPath()+"\\resources\\";
	String path="cmd /c start "+filepath+"killIE.bat";
	Runtime rn=Runtime.getRuntime();
	Process pr=rn.exec(path);
	System.out.println(pr);

}


public void dataGenerator() throws FileNotFoundException, IOException,
		InvalidFormatException {
	if(gd){
		
	
			FileInputStream input_document = new FileInputStream(new File("F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xls"));
			   //Access the workbook
			   HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document); 
			   //Access the worksheet, so that we can update / modify it.
			   HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
			   // declare a Cell object
			   Cell cell = null; 
			   // Access the cell first to update the value
			   int raw =Integer.parseInt(cachedProperties.value("Increment"));
			   String inc=Integer.toString(raw);
			   String data= cachedProperties.value("ClientName")+inc;
			   System.out.println(data);
			   cell = my_worksheet.getRow(1).getCell(0);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(1);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(2).getCell(2);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(2).getCell(3);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(2).getCell(4);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(2).getCell(5);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(6);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(7);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(8); 
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(9);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(10);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(11);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(12);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(13);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(14);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(15);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(16);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(17);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(18);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(19);
			   cell.setCellValue(data);
			   cell = my_worksheet.getRow(1).getCell(20);
			   cell.setCellValue(data);
			   
			  String arraydata = "ClientGroupType,SiteGroupType,MeterGroupType,TechParamType,TechParam";
			  String[] array =arraydata.split(",");
		
		
		
			  
			   cell = my_worksheet.getRow(1).getCell(21);
			   cell.setCellValue(data+array[0]);
			   cell = my_worksheet.getRow(1).getCell(22);
			   cell.setCellValue(data+array[1]);
			   cell = my_worksheet.getRow(1).getCell(23);
			   cell.setCellValue(data+array[2]);
			   cell = my_worksheet.getRow(1).getCell(24);
			   cell.setCellValue(data+array[0]);
			   cell = my_worksheet.getRow(1).getCell(25);
			   cell.setCellValue(data+array[1]);
			   cell = my_worksheet.getRow(1).getCell(26);
			   cell.setCellValue(data+array[2]);
			   
			   cell = my_worksheet.getRow(1).getCell(27);
			   cell.setCellValue(data+array[3]);
			   cell = my_worksheet.getRow(1).getCell(28);
			   cell.setCellValue(data+array[4]);
			   cell = my_worksheet.getRow(1).getCell(29);
			   cell.setCellValue(data+"tech1");
			   // Get current value and then add 5 to it 
			   //Close the InputStream
			   
			   
			   int raw1 =Integer.parseInt(cachedProperties.value("Increment"))+1;
			   String inc1=Integer.toString(raw1);
			   String siteone1= "sOne"+inc;
			   String siteone2= "sOne"+inc1;
			   String sitetwo1= "sTwo"+inc;
			   String sitetwo2= "sTwo"+inc1;
			   cell = my_worksheet.getRow(10).getCell(2);
			   cell.setCellValue(siteone1);
			   cell = my_worksheet.getRow(11).getCell(2);
			   cell.setCellValue(siteone2);
			   cell = my_worksheet.getRow(10).getCell(3);
			   cell.setCellValue(sitetwo1);
			   cell = my_worksheet.getRow(11).getCell(3);
			   cell.setCellValue(sitetwo2);
			   
			   cell = my_worksheet.getRow(10).getCell(4);
			   cell.setCellValue(siteone1);
			   cell = my_worksheet.getRow(11).getCell(4);
			   cell.setCellValue(siteone2);
			   cell = my_worksheet.getRow(10).getCell(5);
			   cell.setCellValue(sitetwo1);
			   cell = my_worksheet.getRow(11).getCell(5);
			   cell.setCellValue(sitetwo2);
			   
			   
			   cell = my_worksheet.getRow(3).getCell(6);
			   cell.setCellValue(siteone1);
			   cell = my_worksheet.getRow(3).getCell(7);
			   cell.setCellValue(siteone2);
			   cell = my_worksheet.getRow(3).getCell(8);
			   cell.setCellValue(sitetwo1);
			   cell = my_worksheet.getRow(3).getCell(9);
			   cell.setCellValue(sitetwo2);
			   
			   cell = my_worksheet.getRow(3).getCell(10);
			   cell.setCellValue(siteone1);
			   cell = my_worksheet.getRow(3).getCell(11);
			   cell.setCellValue(siteone2);
			   cell = my_worksheet.getRow(3).getCell(12);
			   cell.setCellValue(sitetwo1);
			   cell = my_worksheet.getRow(3).getCell(13);
			   cell.setCellValue(sitetwo2);
			   
			   String ARsiteone1= "ARsOne"+inc;
			   String ARsiteone2= "ARsOne"+inc1;
			   String ARsitetwo1= "ARsTwo"+inc;
			   String ARsitetwo2= "ARsTwo"+inc1;
			   cell = my_worksheet.getRow(6).getCell(6);
			   cell.setCellValue(ARsiteone1);
			   cell = my_worksheet.getRow(6).getCell(7);
			   cell.setCellValue(ARsiteone2);
			   cell = my_worksheet.getRow(6).getCell(8);
			   cell.setCellValue(ARsitetwo1);
			   cell = my_worksheet.getRow(6).getCell(9);
			   cell.setCellValue(ARsitetwo2);
			   
			   cell = my_worksheet.getRow(6).getCell(10);
			   cell.setCellValue(ARsiteone1);
			   cell = my_worksheet.getRow(6).getCell(11);
			   cell.setCellValue(ARsiteone2);
			   cell = my_worksheet.getRow(6).getCell(12);
			   cell.setCellValue(ARsitetwo1);
			   cell = my_worksheet.getRow(6).getCell(13);
			   cell.setCellValue(ARsitetwo2);
			   
			   cell = my_worksheet.getRow(3).getCell(17);
			   cell.setCellValue(ARsiteone1);
			   cell = my_worksheet.getRow(3).getCell(18);
			   cell.setCellValue(ARsiteone2);
			   cell = my_worksheet.getRow(3).getCell(19);
			   cell.setCellValue(ARsitetwo1);
			   cell = my_worksheet.getRow(3).getCell(20);
			   cell.setCellValue(ARsitetwo2);
			   
			   
			   int a= getInteger("F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xls", "Sheet1", 3, 21);
			   System.out.println(a);
			   cell = my_worksheet.getRow(3).getCell(21);
			   cell.setCellValue(a+3);
			   int b= getInteger("F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xls", "Sheet1", 3, 22);
			   System.out.println(b);
			   cell = my_worksheet.getRow(3).getCell(22);
			   cell.setCellValue(b+3);
			   int c= getInteger("F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xls", "Sheet1", 3, 23);
			   System.out.println(c);
			   cell = my_worksheet.getRow(3).getCell(23);
			   cell.setCellValue(c+3);
			   
			   
			   
			   input_document.close();
			   //Open FileOutputStream to write updates
			   FileOutputStream output_file =new FileOutputStream(new File("F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xls"));
			   //write changes
			   my_xls_workbook.write(output_file);
			   //close the stream
			   output_file.close();
	}
		}

public static int getInteger(String filePath, String Sheetname, int row, int cel) throws FileNotFoundException, IOException,
InvalidFormatException {
	FileInputStream input_document = new FileInputStream(new File(filePath));//"F:\\energyquote\\automation\\resources\\metricsdata\\metricsTemplateDatas.xlsx"
	Workbook wb = WorkbookFactory.create(input_document);
	Cell cell = null; 
	cell = wb.getSheet(Sheetname).getRow(row).getCell(cel);
	input_document.close();
	return (int) cell.getNumericCellValue();
}


}
