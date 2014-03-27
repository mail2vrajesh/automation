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

public class Upload_Client extends MetricsDomainWraper {
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
	public  void download_Upload_Client() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Client_Blank.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnBlankClientTemplate");
		String templatcontains=readDataFromXL("Upload_Client_Blank.xlsx",0,1,1);
		assertTrue(templatcontains.contains("ClientPK"));
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Country"));
		assertTrue(templatcontains.contains("Address line 1"));
		assertTrue(templatcontains.contains("Address line 2"));
		assertTrue(templatcontains.contains("Address line 3"));
		assertTrue(templatcontains.contains("Address line 4"));
		assertTrue(templatcontains.contains("Post code"));
		assertTrue(templatcontains.contains("Note"));
	}
	

	
	@Test (dependsOnMethods={"download_Upload_Client"})
	public  void importUpload_Client_CountryBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankCountryBlank"));
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

	
	@Test (dependsOnMethods={"importUpload_Client_CountryBlank"})
	public  void importUpload_Client_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankValid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));

		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported Unsuccessfully", false);
		}
		simpleFilter(driver,"Client",cachedProperties.value("ClientName"));
		Thread.sleep(2000);
		String ClientDisplayed=driver.findElement(By.xpath("//ul[@id='multiSelection-grid']/li")).getText();
		assertEquals(ClientDisplayed, cachedProperties.value("ClientName"));

	}
	

	@Test (dependsOnMethods={"importUpload_Client_Valid"})
	public  void importUpload_Client_Duplicate() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankValid"));
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
		screenshot(_result, driver);
	}
	
}
