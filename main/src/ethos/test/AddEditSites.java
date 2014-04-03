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

public class AddEditSites extends ETHOSDomainWraper {
	
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
	public Object [ ][ ] AddEditSitesList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"ethosSignin"})
	public void verifySitesProductGroupDropdown(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(2)")),"Electricity");
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(3)")),"Gas");
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(4)")),"Oil");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"ethosSignin"})
	public void verifySitesProductDropdown(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Electricity");
			Thread.sleep(2000);
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Half Hourly Electricity");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(3)")),"Non Half Hourly Electricity");
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Gas");
			Thread.sleep(2000);
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Gas Supply");
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Oil");
			Thread.sleep(2000);
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Diesel");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(3)")),"Gas Oil");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(4)")),"Heavy Fuel Oil");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(5)")),"Kerosene");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(6)")),"Medium Fuel Oil");
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(7)")),"Unleaded");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifyActiveListingForSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Active");
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(8)")),"Active");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifyInactiveListingForSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(8)")),"Inactive");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifySiteIDListingForSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeType(driver,By.cssSelector("#ctl00_cphMainContent_txtSiteID"),"SITE");
			safeClick(driver, By.id("ctl00_cphMainContent_btnSiteID"));
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(3)")),"SITE_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifySiteNameListingForSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeType(driver,By.cssSelector("#ctl00_cphMainContent_txtSiteID"),"Short Name");
			safeClick(driver, By.id("ctl00_cphMainContent_btnSiteID"));
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(5)")),"Site Short Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifyTownNameListingForSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeType(driver,By.cssSelector("#ctl00_cphMainContent_txtSiteID"),"Town Name");
			safeClick(driver, By.id("ctl00_cphMainContent_btnSiteID"));
			
			assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(6)")),"Town Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddNewSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_fields_ddlCompany"));
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteID"), "SITE_ID");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteShortName"), "Site Short Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteName"),"Site Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_fields_ddlCountry"), "GB - United Kingdom");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtTypeOfOperation"), "Digging");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtRegCompanyNumber"), "Site Name");
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			String warningMessage = safeGetText(driver, By.id("ctl00_cphMainContent__duplicateValidator"));
			if (warningMessage.contains("This item already exists in the system"))
				safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"ethosSignin"})
	public void verifyResetListingForSite(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			assertEquals(safeGetSelectedValue(driver,By.id("ctl00_cphMainContent_ddlActivityStatus")),"Active");
			assertEquals(safeGetSelectedValue(driver,By.id("ctl00_cphMainContent_ddlCompany")),"(Select Company)");
			assertEquals(safeGetSelectedValue(driver,By.id("ctl00_cphMainContent_ddlCompanyStatus")),"Active");
			assertEquals(safeGetSelectedValue(driver,By.id("ctl00_cphMainContent_ddlProductGroup")),"(Select Product Group)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifyActivateASite(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlActivityStatus"),"Active");
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent_fields_txtStatus_btnToggle"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyActivateASite"})
	public void verifyDeactivateASite(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent_fields_txtStatus_btnToggle"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyAddNewSites"})
	public void verifyEditSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			safeType(driver, By.id("ctl00_cphMainContent_txtSiteID"),"SITE");
			safeClick(driver, By.id("ctl00_cphMainContent_btnSiteID"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
			
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_fields_ddlCompany"));
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteID"), "SITE_MOD");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteShortName"), "Site Short Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtSiteName"),"Site Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_fields_ddlCountry"), "GB - United Kingdom");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtTypeOfOperation"), "Digging");
			safeType(driver, By.id("ctl00_cphMainContent_fields_txtRegCompanyNumber"), "Site Name");
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			String warningMessage = safeGetText(driver, By.id("ctl00_cphMainContent__duplicateValidator"));
			if (warningMessage.contains("This item already exists in the system"))
				safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"verifyEditSites"})
	public void verifyDeleteSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			safeType(driver, By.id("ctl00_cphMainContent_txtSiteID"),"SITE");
			safeClick(driver, By.id("ctl00_cphMainContent_btnSiteID"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSite>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
			
			confirmDeleteOperation(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditSitesList", dependsOnMethods = {"ethosSignin"})
	public void verifyExportSites(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Sites");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			
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
