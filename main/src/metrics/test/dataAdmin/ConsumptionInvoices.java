package metrics.test.dataAdmin;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class ConsumptionInvoices extends MetricsDomainWraper {
	
	
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		
		killIEInstances();
		Thread.sleep(3000);
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		driver = new InternetExplorerDriver(capability);
		//driver = new FirefoxDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, "Metrics1", "Metrics1");
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
		
		
		
	}


	@Test
	public  void download_Consumption_Template() throws Exception{
		gotoSubMenu(driver, "//a[text()='Data Admin']", "//a[text()='Consumption & Invoices']", "Consumption & Invoices");
		simpleFilter(driver,"Client",cachedProperties.value("ClientName"));
		safeClick(driver, By.id("ctl08_ctl05_selectClients_multiSelection_rptSelection_ctl00_liItem"));
		DeleteFile("Upload_Consumption.xls");
		Downloader(driver, "ctl08_ctl05_lbnConsumptionTemplate");
	    /*String templatcontains=readDataFromXL("Upload_Consumption.xls",0,1,0);
		System.out.println(templatcontains);*/
		
		
	}
	
	
	
	
	@Test (dependsOnMethods={"download_Consumption_Template"})
	public  void importDataAdmin_Consumption_BaseTime_Valid() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTimeValid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_Valid"})
	public  void importDataAdmin_Consumption_SiteEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_SiteEmpty"));
		Boolean success =textPresent(driver, "Site name missing", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Site name missing not displayed", false);
		}
 		
	}
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_SiteEmpty"})
	public  void importDataAdmin_Consumption_SiteInvalid() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_SiteInvalid"));
		Boolean success =textPresent(driver, "Invalid Meter Number / Site Name mentioned", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid Meter Number / Site Name mentioned not displayed", false);
		}
 		
	}
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_SiteInvalid"})
	public  void importDataAdmin_Consumption_CommodityEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_CommodityEmpty"));
		Boolean success =textPresent(driver, "Invalid Meter Number / Site Name mentioned", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid Meter Number / Site Name mentioned not displayed", false);
		}
 		
	}
	

	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_CommodityEmpty"})
	public  void importDataAdmin_Consumption_MeterInvalid() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_MeterInvalid"));
		Boolean success =textPresent(driver, "Invalid Meter Number / Site Name mentioned", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid Meter Number / Site Name mentioned not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_MeterInvalid"})
	public  void importDataAdmin_Consumption_WrongCombo() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_WrongCombo"));
		Boolean success =textPresent(driver, "commodity (Power) not found in the system", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("commodity (Power) not found in the system not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_WrongCombo"})
	public  void importDataAdmin_Consumption_BaseTimeEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTimeEmpty"));
		Boolean success =textPresent(driver, "Base time not selected", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Base time not selected not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTimeEmpty"})
	public  void importDataAdmin_Consumption_VolumeTypeEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_VolumeTypeEmpty"));
		Boolean success =textPresent(driver, "Volume Type not selected", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Volume Type not selected not displayed", false);
		}
 		
	}
	
	
		
	@Test (dependsOnMethods={"importDataAdmin_Consumption_VolumeTypeEmpty"})
	public  void importDataAdmin_Consumption_UnitTypeEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_UnitTypeEmpty"));
		Boolean success =textPresent(driver, "Unit not selected", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Unit not selected not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_UnitTypeEmpty"})
	public  void importDataAdmin_Consumption_BaseTime_OverlapUnitType() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_OverlapUnitType"));
		Boolean success =textPresent(driver, "Existing data", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Existing data  not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_OverlapUnitType"})
	public  void importDataAdmin_Consumption_BaseTimeGaskWArh() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTimeGaskWArh"));
		Boolean success =textPresent(driver, "Gas consumption cannot be entered in kW or kVArh. Please amend the unit", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Gas consumption cannot be entered in kW or kVArh. Please amend the unit not displayed", false);
		}
 		
	}
	
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTimeGaskWArh"})
	public  void importDataAdmin_Consumption_BaseTime_Power_Valid() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTimePowerValid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_Power_Valid"})
	public  void importDataAdmin_Consumption_BaseTime_Power_ActualReactiveInvalidUnit() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTimePowerPower_ActualReactiveInvalidUnit"));
		Boolean success =textPresent(driver, "The Unit for ActualReactive volume should be kVArh", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("The Unit for ActualReactive volume should be kVArh not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_Power_ActualReactiveInvalidUnit"})
	public  void importDataAdmin_Consumption_BaseTime_IntervalEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_IntervalEmpty"));
		Boolean success =textPresent(driver, "Consumption data missing", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Consumption data missing not displayed", false);
		}
 		
	}
	

	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_IntervalEmpty"})
	public  void importDataAdmin_Consumption_BaseTime_DuplicateInterval() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_DuplicateIntervals"));
		Boolean success =textPresent(driver, "Duplicate Interval data found", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Duplicate Interval data found not displayed", false);
		}
 		
	}
	
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_DuplicateInterval"})
	public  void importDataAdmin_Consumption_BaseTime_InconsistentInterval() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_InconsistentIntervals"));
		Boolean success =textPresent(driver, "Incorrect interval data found", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect interval data found not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_InconsistentInterval"})
	public  void importDataAdmin_Consumption_BaseTime_InvalidTimeFormat() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_InvalidTimeFormat"));
		Boolean success =textPresent(driver, "Invalid Interval data. Please specify interval in HH:MM format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid Interval data. Please specify interval in HH:MM format not displayed", false);
		}
 		
	}
	
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_InvalidTimeFormat"})
	public  void importDataAdmin_Consumption_BaseTime_DateEmpty() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_DateEmpty"));
		Boolean success =textPresent(driver, "Dates missing", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Dates missing not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_DateEmpty"})
	public  void importDataAdmin_Consumption_BaseTime_InvalidDateFormat() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_InvalidDateFormat"));
		Boolean success =textPresent(driver, "Invalid date format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid date format not displayed", false);
		}
 		
	}
	
	
	

	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_InvalidDateFormat"})
	public  void importDataAdmin_Consumption_BaseTime_ImproperDate() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_ImproperDate"));
		Boolean success =textPresent(driver, "Invalid date format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid date format not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_ImproperDate"})
	public  void importDataAdmin_Consumption_BaseTime_DuplicateDate() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_DuplicateDate"));
		Boolean success =textPresent(driver, "Duplicate dates found", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Duplicate dates found not displayed", false);
		}
 		
	}
	
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_DuplicateDate"})
	public  void importDataAdmin_Consumption_BaseTime_MissingData() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_MissingData"));
		Boolean success =textPresent(driver, "Consumption data missing", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Consumption data missing not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_MissingData"})
	public  void importDataAdmin_Consumption_BaseTime_NonNumericData() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_BaseTime_NonNumericData"));
		Boolean success =textPresent(driver, "Consumption data should be in numeric format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Consumption data should be in numeric format not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_BaseTime_NonNumericData"})
	public  void importDataAdmin_Consumption_ClockTime_MissingInterval() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_ClockTimeTime_MissingInterval"));
		Boolean success =textPresent(driver, "Interval(s) missing", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Interval(s) missing not displayed", false);
		}
 		
	}
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_ClockTime_MissingInterval"})
	public  void importDataAdmin_Consumption_ClockTime_InvalidDataForDST() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_ClockTime_InvalidDataForDST"));
		Boolean success =textPresent(driver, "Clock Time, DST start period must be blank ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Clock Time, DST start period must be blank not displayed", false);
		}
 		
	}
	
	
	
	@Test (dependsOnMethods={"importDataAdmin_Consumption_ClockTime_InvalidDataForDST"})
	public  void importDataAdmin_Consumption_ClockTime_validDataForDST() throws Exception{
		importXL(driver,cachedProperties.value("CIUpload_Consumption_ClockTime_ValidDataForDST"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}
 		
	}
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	@AfterMethod (alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception{
		if(screenshot){
			screenshot(_result, driver);
		}
	}

}