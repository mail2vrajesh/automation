package ethos.test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class VerifyLoadingOfIntervalData extends ETHOSDomainWraper {
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
		public Object [ ][ ] Interval() {
			return new Object [ ] [ ] {{ "System"}};
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
		//passed
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_ProductList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				assertTrue("Product group options is missing", safeGetText(driver, By.id("ctl00_cphMainContent_ddlProductGroup")).contains("(Select Product Group)"));
				assertTrue("Electricity option is missing in product group", safeGetText(driver, By.id("ctl00_cphMainContent_ddlProductGroup")).contains("Electricity"));
				assertTrue("Gas option is missing in product group", safeGetText(driver, By.id("ctl00_cphMainContent_ddlProductGroup")).contains("Gas"));
				assertTrue("Oil option is missing in product group", safeGetText(driver, By.id("ctl00_cphMainContent_ddlProductGroup")).contains("Oil"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_LoadAsManual(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_0"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnStart"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnStart"));
			/*	assertTrue("Loading of data is not succesfull",elementPresent(driver, By.id("ctl00_cphMainContent_Repeater1_ctl00_lblFile"), 10));		
			*/} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_LoadAsAutoImServ(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_1"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnStart"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnStart"));
			/*	assertTrue("Loading of data is not succesfull",elementPresent(driver, By.id("ctl00_cphMainContent_Repeater1_ctl00_lblFile"), 10));		
			*/} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_LoadAsAutoImGenric(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_2"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnStart"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnStart"));
			/*	assertTrue("Loading of data is not succesfull",elementPresent(driver, By.id("ctl00_cphMainContent_Repeater1_ctl00_lblFile"), 10));		
			*/} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//passed
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_AddFile(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_1"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				assertTrue("Add file is not succesfull",elementPresent(driver, By.id("ctl00_cphMainContent_Repeater1_ctl00_lblFile"), 10));		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_LoadIntervalData(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_0"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnStart"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnStart"));
				assertTrue("Loading of data is not succesfull",elementPresent(driver, By.id("ctl00_cphMainContent_Repeater1_ctl00_lblFile"), 10));		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//passed
		@Test(dataProvider = "Interval", dependsOnMethods = {"ethosSignin"})
		public void verify_LoadingError(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToLoadingIntervalData(driver);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_radioMethod_0"));
				String value="\\\\192.168.185.43\\Automation_import\\madhva1.xls";
				safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddFile"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnNext"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
				waitForPagetoLoad_Element(driver, 5, By.id("ctl00_cphMainContent_btnStart"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnStart"));
				assertTrue("No error message", safeGetText(driver, By.xpath("//*[@id='ctl00_cphMainContent_pnlErrors']/span")).contains("There were errors in the import:"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
