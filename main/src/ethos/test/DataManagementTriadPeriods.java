package ethos.test;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class DataManagementTriadPeriods extends ETHOSDomainWraper {
	
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
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
	public Object [ ][ ] TriadPeriodsList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodPageHeading(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")),"Triad Periods");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodPageSubHeading(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			assertTrue(safeGetText(driver, By.id("maincontent")).contains("The three half hourly slots of national peak electricity demand on which National Grid transmission (Triad) charges are based for UK half hourly electricity consumers."));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodPageDistributorDateDropDowns(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent_ddlZone")));
			assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent_ddlDateFrom")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodPageViewTriadPage(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent_gvZoneTriadPeriod_ctl02_lnkSelect")));
			safeClick(driver, By.id("ctl00_cphMainContent_gvZoneTriadPeriod_ctl02_lnkSelect"));
			assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")),"View Triad Period");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodViewDeleteButtonOnTriadPage(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent_gvZoneTriadPeriod_ctl02_lnkSelect")));
			safeClick(driver, By.id("ctl00_cphMainContent_gvZoneTriadPeriod_ctl02_lnkSelect"));
			assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent__btnDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "TriadPeriodsList", dependsOnMethods = {"ethosSignin"})
	public void verifyTriadPeriodAddButton(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "Data Management", "Triad Periods");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")),"Add Triad Period");
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