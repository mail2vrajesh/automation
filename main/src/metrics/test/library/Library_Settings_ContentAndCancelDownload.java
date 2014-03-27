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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class Library_Settings_ContentAndCancelDownload extends MetricsDomainWraper{
	
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


	@DataProvider
	public Object [ ][ ] AllSubHead() {
		return new Object [ ] [ ] {{ "Technical Parameter Settings"},{ "Group Settings"},{"Other Data"}};
	}
	
	@Test(dataProvider="AllSubHead")
	public  void library_Heading(String text) throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Library Settings']", "Library Settings");
		textPresent(driver, text, 2);
		
		
	}
				
			
	@DataProvider
	public Object [ ][ ] AllElements() {
		return new Object [ ] [ ] {{ "ctl08_ctl05_lbnTechParamtypes"},{ "ctl08_ctl05_lbnTechParamDefine"},{"ctl08_ctl05_lbnTechParamRule"},
				{"ctl08_ctl05_lbnBlankGroupDefineTemplate"},{"ctl08_ctl05_lbnBlankGroupTemplate"},{"ctl08_ctl05_lbnTariffCategoryType"},
				{"ctl08_ctl05_lbnTariffCategory"},{"ctl08_ctl05_lbnTariffLine"},{"ctl08_ctl05_lbnBillingElementMapping"},
				{"ctl08_ctl05_lbnExportReportSetting"}};
	}
	
	@Test(dataProvider="AllElements",dependsOnMethods={"library_Heading"})
	public  void library_SubMethods(String element) throws Exception{
				elementPresent(driver, By.id(element), 2);
			
	}
	
	@DataProvider
	public Object [ ][ ] AllDownload() {
		return new Object [ ] [ ] {{ "ctl08_ctl05_lbnTechParamtypes"},{ "ctl08_ctl05_lbnTechParamDefine"},{"ctl08_ctl05_lbnTechParamRule"},
				{"ctl08_ctl05_lbnBlankGroupDefineTemplate"},{"ctl08_ctl05_lbnBlankGroupTemplate"},{"ctl08_ctl05_lbnTariffCategoryType"},
				{"ctl08_ctl05_lbnTariffCategory"},{"ctl08_ctl05_lbnTariffLine"},{"ctl08_ctl05_lbnBillingElementMapping"},
				{"ctl08_ctl05_lbnExportReportSetting"}};
	}
	
	@Test(dataProvider="AllElements",dependsOnMethods={"library_Heading"})
	public  void library_AllDownload(String element) throws Exception{
				cancelDownloader(driver, element);
				//DeleteFile(FileName);
			
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
