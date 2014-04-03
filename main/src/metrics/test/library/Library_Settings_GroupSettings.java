package metrics.test.library;

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

public class Library_Settings_GroupSettings extends MetricsDomainWraper{
	
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
	public  void download_library_GroupType() throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Library Settings']", "Library Settings");
		DeleteFile("Upload_GroupDefine_Blank.xls");
		Downloader(driver, "ctl08_ctl05_lbnBlankGroupDefineTemplate");
		String templatcontains=readDataFromXL("Upload_GroupDefine_Blank.xls",0,1,1);
		System.out.println(templatcontains);
		
		
	}
	
	
	@Test (dependsOnMethods={"download_library_GroupType"})
	public  void importlibrary_GroupTypeCountryBlank() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_GroupType_CountryBlank"));
		Boolean success =textPresent(driver, "Country cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Country cannot be blank not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importlibrary_GroupTypeCountryBlank"})
	public  void importlibrary_GroupTypePriority () throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_GroupType_Priority"));
		Boolean success =textPresent(driver, "Priority number must be numeric", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Priority number must be numeric not displayed", false);
		}
	
	
	}
	
	@Test (dependsOnMethods={"importlibrary_GroupTypePriority"})
	public  void importlibrary_GroupTypeBlank() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_GroupType_Blank"));
		Boolean success =textPresent(driver, "'Group type' cannot be blank or spaces", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("'Group type' cannot be blank or spaces not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importlibrary_GroupTypeBlank"})
	public  void importlibrary_GroupTypeValueAttachableBlank() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_GroupType_Value_Blank"));
		Boolean success =textPresent(driver, "Value attachable cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Value attachable cannot be blank not displayed", false);
		}
 
	}
	
	@Test (dependsOnMethods={"importlibrary_GroupTypeValueAttachableBlank"})
	public  void importlibrary_GroupTypeValid() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_GroupTypeValid"));
		Boolean success =textPresent(driver, "Value attachable cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Value attachable cannot be blank not displayed", false);
		}
 
	}
	
	@Test (dependsOnMethods={"importlibrary_GroupTypeValid"})
	public  void download_library_Groups() throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Library Settings']", "Library Settings");
		DeleteFile("Upload_Group.xls");
		Downloader(driver, "ctl08_ctl05_lbnBlankGroupTemplate");
		String templatcontains=readDataFromXL("Upload_Group.xls",0,1,1);
		System.out.println(templatcontains);
		
		
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
