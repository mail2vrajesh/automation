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

public class Site extends MetricsDomainWraper {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file = new File("exe\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		//driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, "Metrics", "Metrics");
	}

	
	@Test
	public  void download_Site_Blank() throws Exception{
		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Site_Blank.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnBlankSiteTemplate");
		String templatcontains=readDataFromXL("Upload_Site_Blank.xlsx",0,1,1);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("SitePK"));
		assertTrue(templatcontains.contains("Site Name"));
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Country"));
		assertTrue(templatcontains.contains("Address line 1"));
		assertTrue(templatcontains.contains("Address line 2"));
		assertTrue(templatcontains.contains("Address line 3"));
		assertTrue(templatcontains.contains("Address line 4"));
		assertTrue(templatcontains.contains("Post code"));
		assertTrue(templatcontains.contains("Note"));
		assertTrue(templatcontains.contains("Gas"));
		assertTrue(templatcontains.contains("Power"));
		assertTrue(templatcontains.contains("Water"));
		assertTrue(templatcontains.contains("Carbon"));
		assertTrue(templatcontains.contains("Oil"));
		assertTrue(templatcontains.contains("Coal"));
	}
				
			
	@Test (dependsOnMethods={"download_Site_Blank"})
	public  void importUpload_Site_CountryBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_CountryBlank"));
		Boolean success =textPresent(driver, "Country cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Site_CountryBlank"})
	public  void importUpload_Site_CommodityBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_CommodityBlank"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Site_CommodityBlank"})
	public  void importUpload_Site_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Valid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Site_Valid"})
	public  void importUpload_Site_Duplicate() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Valid"));
		Boolean success =textPresent(driver, "duplicated", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test(dependsOnMethods={"importUpload_Site_Duplicate"})
	public  void download_Site_Modify() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Site.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnSiteData");
		String templatcontains=readDataFromXL("Upload_Site.xlsx",1,2,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Sevam_Site"));
		assertTrue(templatcontains.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains.contains("ALL Countries"));
		
	}
	 
	@Test (dependsOnMethods={"download_Site_Modify"})
	public  void importUpload_Site_Modify_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Modify"));
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
