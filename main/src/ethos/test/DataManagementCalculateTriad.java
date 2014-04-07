package ethos.test;
	
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class DataManagementCalculateTriad extends ETHOSDomainWraper {
	
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
	public Object [ ][ ] CalculateTriadList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadPageHeading(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")),"Calculate Triad Demand - Select Groups");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadPageSubHeading(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			assertTrue(safeGetText(driver, By.id("ctl00_cphMainContent_pnlMain")).contains("Allows Triad Demands to be re-calculated en masse for individual delivery point records across multiple Group records."));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadPageTableHeaders(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			String actualId = safeGetText(driver, By.cssSelector(".grid-header>th:nth-child(1)"));
			
			assertEquals(actualId,"Group ID");
			assertEquals(safeGetText(driver, By.cssSelector(".grid-header>th:nth-child(2)")),"Group Name");
			assertEquals(safeGetText(driver, By.cssSelector(".grid-header>th:nth-child(3)")),"Update?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadPageButtons(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			assertTrue(isElementPresent(driver,By.id("ctl00_cphMainContent_btnTickAll")));
			assertTrue(isElementPresent(driver,By.id("ctl00_cphMainContent_btnTickNone")));
			assertTrue(isElementPresent(driver,By.id("ctl00_cphMainContent_btnNext")));
			assertTrue(isElementPresent(driver,By.id("ctl00_cphMainContent_btnCancel")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadCancelButton(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
			assertTrue(safeGetText(driver,By.id("ctl00_lblTitle")).contains("View Triad Period"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "CalculateTriadList", dependsOnMethods = {"ethosSignin"})
	public void verifyCalculateTriadNextButton(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToCalculateTriad(driver);
			safeClick(driver, By.id("ctl00_cphMainContent_btnTickNone"));
			safeClick(driver,By.id("ctl00_cphMainContent_btnNext"));
			assertTrue(safeGetText(driver,By.id("ctl00_lblTitle")).contains("Calculate Triad Demand"));
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