package metrics.test.contracts;

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

public class Account_References extends MetricsDomainWraper {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file = new File("exe\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, "Metrics", "Metrics");
	}
	
	@Test
	public  void download_Account_References() throws Exception{
		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		safeClick(driver, By.id("ctl08_ctl05_selectClients_multiSelection_rptSelection_ctl00_liItem"));
		DeleteFile("Upload_AccountRefTemplate.xls");
		Downloader(driver, "ctl08_ctl05_lbnAccountRefData");
		String templatcontains=readDataFromXL("Upload_AccountRefTemplate.xls",0,1,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Site Name"));
		assertTrue(templatcontains.contains("Meter"));
		assertTrue(templatcontains.contains("Start Date"));
		assertTrue(templatcontains.contains("End Date"));
		assertTrue(templatcontains.contains("Supplier Account Ref"));
		assertTrue(templatcontains.contains("Supplier Name"));
		String templatcontains1=readDataFromXL("Upload_AccountRefTemplate.xls",0,2,2);
		System.out.println(templatcontains1);
		assertTrue(templatcontains1.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains1.contains("Sevam_Site1"));
		assertTrue(templatcontains1.contains("A12"));
		String templatcontains2=readDataFromXL("Upload_AccountRefTemplate.xls",0,3,2);
		System.out.println(templatcontains2);
		assertTrue(templatcontains2.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains2.contains("Sevam_Site1"));
		assertTrue(templatcontains2.contains("A13"));
		String templatcontains3=readDataFromXL("Upload_AccountRefTemplate.xls",0,4,2);
		System.out.println(templatcontains3);
		assertTrue(templatcontains3.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains3.contains("Sevam_Site2"));
		assertTrue(templatcontains3.contains("A14"));
		String templatcontains4=readDataFromXL("Upload_AccountRefTemplate.xls",0,5,2);
		System.out.println(templatcontains4);
		assertTrue(templatcontains4.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains4.contains("Sevam_Site2"));
		assertTrue(templatcontains4.contains("A15"));
		
	}
	
	@Test (dependsOnMethods={"download_Account_References"})
	public  void importUpload_Account_Ref_invalidDate() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_InvalidDate"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date. ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date is lesser than the end date. ", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_invalidDate"})
	public  void importUpload_Account_Ref_invalidDateFormat() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_InvalidDateFormat"));
		Boolean success =textPresent(driver, "Can't capture invalid date format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Can't capture invalid date format", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_invalidDateFormat"})
	public  void importUpload_Account_Ref_EmptyRefNo() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_EmptyRefNo"));
		Boolean success =textPresent(driver, "Account Reference Number missing for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Account Reference Number missing for meter not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_EmptyRefNo"})
	public  void importUpload_Account_Ref_EmptyAccountName() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_EmptyAccountName"));
		Boolean success =textPresent(driver, "Supplier name (Issuer) not selected for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Supplier name (Issuer) not selected for meter not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_EmptyAccountName"})
	public  void importUpload_Account_Ref_Empty() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_AccountDuplicate"));
		Boolean success =textPresent(driver, "Account Reference duplicated for meter ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Account Reference duplicated for meter not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_Empty"})
	public  void importUpload_DateOverlap() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_DateOverlap"));
		Boolean success =textPresent(driver, "Dates overlap for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Dates overlap for meter is not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_DateOverlap"})
	public  void importUpload_Account_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_Valid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported Unsuccessfully", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Valid"})
	public  void importUpload_Account_Delete() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_Delete"));
		Boolean success =textPresent(driver, "The record(s) will be permanently deleted and cannot be recovered", 10);
		if(success){
			safeClick(driver, By.id("ctl08_ctl05_fileImport_lbnDelAccRef"));
			Boolean success2 =textPresent(driver, "Records were successfully deleted", 10);
			if(success2){
				safeClick(driver, By.xpath("//span[text()='Ok']"));
			}else{
				String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
				System.out.println(errortest2);
				safeClick(driver, By.xpath("//span[text()='Ok']"));
				assertTrue("The record(s) will be permanently deleted and cannot be recovered not dsiplayed", false);
			}
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("The record(s) will be permanently deleted and cannot be recovered not dsiplayed", false);
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
