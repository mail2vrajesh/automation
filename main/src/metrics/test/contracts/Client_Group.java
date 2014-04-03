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

public class Client_Group extends MetricsDomainWraper {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
	}

	@Test 
	public  void download_Client_Group() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_ClientGroup.xls");
		Downloader(driver, "ctl08_ctl05_lbnClientGroupData");
		String templatcontains=readDataFromXL("Upload_ClientGroup.xls",0,0,1);
		System.out.println(templatcontains);
		String templatcontains1=readDataFromXL("Upload_ClientGroup.xls",0,2,1);
		System.out.println(templatcontains1);
		assertTrue(templatcontains.contains("EQ Excel Template"));
		assertTrue(templatcontains.contains("1.0.0.0"));
		assertTrue(templatcontains.contains("DataWareHouse"));
		assertTrue(templatcontains.contains("Template Name"));
		assertTrue(templatcontains.contains("Client groups modify and upload"));
		assertTrue(templatcontains1.contains("Client Name"));
		assertTrue(templatcontains1.contains("Start Date"));
		assertTrue(templatcontains1.contains("End Date"));
	}
	
	
	@Test (dependsOnMethods={"download_Client_Group"})
	public  void importClient_Group_InvalidClientName() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_GroupInvalidClientName"));
		Boolean success =textPresent(driver, "Client Sevam not found", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Client Sevam not found not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importClient_Group_InvalidClientName"})
	public  void importClient_GroupStartDateLess() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_StartDateLess"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date must be lesser than the end date not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importClient_GroupStartDateLess"})
	public  void importClient_Group_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Incorrect date format. Please follow the datetime format as defined in the regional settings.", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect date format. Please follow the datetime format as defined in the regional settings. not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importClient_Group_dateRegionalSetting"})
	public  void importClient_Group_GroupReq() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_NameReq"));
		Boolean success =textPresent(driver, "Group name not found. ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Group name not found not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importClient_Group_GroupReq"})
	public  void importClient_Group_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_Valid"));
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
