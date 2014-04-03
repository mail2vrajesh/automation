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

public class Library_Settings_Technical extends MetricsDomainWraper{
	
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
	public  void download_library_ParameterType() throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Library Settings']", "Library Settings");
		DeleteFile("Upload_TechParamTypes.xls");
		Downloader(driver, "ctl08_ctl05_lbnTechParamtypes");
		String templatcontains=readDataFromXL("Upload_TechParamTypes.xls",0,1,1);
		System.out.println(templatcontains);
		
		
	}
	
	
	@Test (dependsOnMethods={"download_library_ParameterType"})
	public  void importlibrary_ParameterTypeValid() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_ParameterTypeValid"));
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
	
	
	@Test (dependsOnMethods={"importlibrary_ParameterTypeValid"})
	public  void importlibrary_ParameterTypeDuplicate() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_ParameterTypeValid"));
		Boolean success =textPresent(driver, "duplicate", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Duplicate  message not displayed", false);
		}
	}
	
	
	
	@Test(dependsOnMethods={"importlibrary_ParameterTypeDuplicate"})
	public  void download_library_ParameterDefinition() throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Library Settings']", "Library Settings");
		DeleteFile("Upload_TechParamDefine.xls");
		Downloader(driver, "ctl08_ctl05_lbnTechParamDefine");
		String templatcontains=readDataFromXL("Upload_TechParamDefine.xls",0,1,1);
		System.out.println(templatcontains);
	}
		
	@Test (dependsOnMethods={"download_library_ParameterDefinition"})
	public  void importlibrary_ParameterDefinitionDatatypeBlank() throws Exception{
		importXL(driver,cachedProperties.value("LSParameterDefinitionDatatypeBlank"));
		Boolean success =textPresent(driver, "'Data type' cannot be blank or spaces", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("'Data type' cannot be blank or spaces not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importlibrary_ParameterDefinitionDatatypeBlank"})
	public  void importlibrary_ParameterDefinitionParamTypeBlank() throws Exception{
		importXL(driver,cachedProperties.value("LSParameterDefinitionParamTypeBlank"));
		Boolean success =textPresent(driver, "'Technical parameter type' cannot be blank or spaces", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("'Technical parameter type' cannot be blank or spaces not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importlibrary_ParameterDefinitionParamTypeBlank"})
	public  void importlibrary_ParameterDefinitionValid() throws Exception{
		importXL(driver,cachedProperties.value("LSParameterDefinitionValid"));
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
	
	@Test (dependsOnMethods={"importlibrary_ParameterDefinitionValid"})
	public  void importlibrary_ParameterDefinitionDuplicate() throws Exception{
		importXL(driver,cachedProperties.value("LSParameterDefinitionValid"));
		Boolean success =textPresent(driver, "duplicate", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("duplicate not displayed", false);
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
