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

public class CostToServe extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] CostToServeList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyCostToServeCountryDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
					navigateToScreen(driver, "Data Management","Cost To Serve Rates");
					
				 WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_ddlCountry"));
				 List<WebElement> rows = table.findElements(By.tagName("option"));
				 assertEquals(rows.size(), 51);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyCostToServeProductGroupDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(2)")),"Electricity");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(3)")),"Gas");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(4)")),"Oil");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyCostToServeRateTypeDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup"),"Electricity");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(2)")),"AAHEDC");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(3)")),"BSUoS");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(4)")),"Elexon (Market Participation)");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(5)")),"Feed in Tariff");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(6)")),"Imbalance Risk");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(7)")),"Management Fee");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType>option:nth-child(8)")),"Renewable Obligation");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyCostToServeDisplayAllRecords(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup"),"Electricity");
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_radioSelect_0"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyCostToServeDisplayRecentRecords(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup"),"Electricity");
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_radioSelect_1"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddNewCostToServe(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCountry"),"GB - United Kingdom");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType"), "Management Fee");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDateFrom"),"26-Mar-2014");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDateTo"), "26-Mar-2014");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCurrency"),"GBP - Pounds, United Kingdom");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtValue"), "100");
				safeSelectByText(driver,By.name("ctl00$cphMainContent$ddlChargingBasis"),"p/kVA");
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"verifyAddNewCostToServe"})
		public void verifyEditCostToServe(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_gvCostToServeRate>tbody>tr:nth-child(3)>td>a"));
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCountry"),"GB - United Kingdom");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateType"), "Management Fee");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDateFrom"),"26-Mar-2015");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDateTo"), "26-Mar-2015");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCurrency"),"GBP - Pounds, United Kingdom");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtValue"), "150");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlChargingBasis"),"p/kVA");
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"verifyEditCostToServe"})
		public void verifyDeleteCostToServe(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
				
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent_gvCostToServeRate>tbody>tr:nth-child(3)>td>a"));
				safeClick(driver,By.cssSelector("#ctl00_cphMainContent__btnDelete"));
				acceptAlert(driver,"Are you sure you want to delete this item?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "CostToServeList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportFactorIntervalDataRule(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Cost To Serve Rates");
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