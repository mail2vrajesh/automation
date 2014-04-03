package ethos.test;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class ConversionFactor extends ETHOSDomainWraper {
	    public RemoteWebDriver driver = null;
		@BeforeClass
		public void startSelenium() throws Exception {	
			driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
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
		public Object [ ][ ] ConversionFactorList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddingNewConversionFactor(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlUnitBasisID"),"kVA");
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlCountry"),"AD - Andorra");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"),"26-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "26-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtValue"), "1000");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnReplace"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"verifyAddingNewConversionFactor"})
		public void verifyEditConversionFactor(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvConversionFactor>tbody>tr:nth-child(4)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlUnitBasisID"),"kVA");
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlCountry"),"AD - Andorra");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"),"27-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "27-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtValue"), "100");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnReplace"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"verifyEditConversionFactor"})
		public void verifyDeleteConversionFactor(String item) throws InterruptedException, ErrorPageException {
			Thread.sleep(1000);
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_gvConversionFactor>tbody>tr:nth-child(4)>td:nth-child(8)>a"));
				acceptAlert(driver,"Are you sure you want to delete");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"ethosSignin"})
		public void verifyUnitsFilteringConversionFactor(String item) throws InterruptedException, ErrorPageException {
			String returnText="";
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlUnitBasis"),"kVA");
				returnText = safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_gvConversionFactor>tbody>tr:nth-child(2)>td:nth-child(2)"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals(returnText,"kVA");
		}
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"ethosSignin"})
		public void verifyDisplayFactorConversionFactor(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_radioSelect_0"));
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_radioSelect_1"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ConversionFactorList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportFactorConversionFactor(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Conversion Factors");;
				eDownloader(driver, ".grid-pager>a");
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