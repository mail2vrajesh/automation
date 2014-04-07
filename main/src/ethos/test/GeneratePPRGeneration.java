package ethos.test;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;


public class GeneratePPRGeneration extends ETHOSDomainWraper {
	
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@DataProvider
	public Object [ ][ ] users() {
		return new Object [ ] [ ] {{"madhva","madhva"} };
	}

	@Test(dataProvider="users")
	public void ethosSignin(String username, String password) throws Exception {
		getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
	    ethosLogin(driver,username,password);
	    
		if(username.equals("madhva") || username.equals("sachin")){
			waitTitle(driver, "ETHOS Main Page", 10);
			elementPresent(driver, By.id("ctl00_btnLogout"), 10); 
		}else{
			textPresent(driver, "Your login attempt was not successful. Invalid User Name", 10);
		}
	}
	
	@DataProvider
	public Object [ ][ ] GeneratePPRList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "GeneratePPRList", dependsOnMethods = {"ethosSignin"})
	public void verifyPPRGeneration(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Generate Purchase Position Report (PPR)");
			
			safeType(driver,By.id("ctl00_cphMainContent_txtPPRRecommendComm"),"Purchase Position Report Generated by the Automation Script.");
			safeType(driver, By.id("ctl00_cphMainContent_txtPPRRecommendExpiry"),"01-Apr-2015");
			safeCheck(driver, By.id("ctl00_cphMainContent_radioPPRBriefing_0"));
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnGenerate"));
			
		} catch (Exception e) {
			e.printStackTrace();
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
