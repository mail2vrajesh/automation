package com.common;
import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;


public class down_Up_XL extends FrameworkCommon{
	

		
	@Test
	public static void downloadUpload() throws InterruptedException, InvalidFormatException, IOException, AWTException{
		
		writeTemplateData(1,2,2, "./inputData/Book1.xlsx");
		writeTemplateData(1,3,3, "./inputData/Book1.xlsx");
			
	}


//1,2, "./inputData/Upload_Client_Blank.xlsx"
	
		
	}