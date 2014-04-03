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

public class Site_Group extends MetricsDomainWraper {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		//driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
	}

	@Test 
	public  void download_Site_Group() throws Exception{
		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_SiteGroup.xls");
	    Downloader(driver, "ctl08_ctl05_lbnSiteGroupData");
		String templatcontains=readDataFromXL("Upload_SiteGroup.xls",0,2,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Site Name"));
		assertTrue(templatcontains.contains("Start Date"));
		assertTrue(templatcontains.contains("End Date"));

		
	}
	
	@Test (alwaysRun=true,dependsOnMethods={"download_Site_Group"})
	public  void importSite_GroupStartDateLess() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_StartDateLess"));
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
	
	
	@Test (alwaysRun=true,dependsOnMethods={"importSite_GroupStartDateLess"})
	public  void importSite_Group_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Incorrect date format. Please follow the datetime format as defined in the regional settings", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect date format. Please follow the datetime format as defined in the regional settings not displayed", false);
		}
	}
	
	@Test (alwaysRun=true,dependsOnMethods={"importSite_Group_dateRegionalSetting"})
	public  void importSite_Group_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_Valid"));
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
