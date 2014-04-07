package ethos.test;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class AddEditCompanies extends ETHOSDomainWraper {
	
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver = new FirefoxDriver();
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
	public Object [ ][ ] AddEditCompaniesList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddNewCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtCompanyID"), "B4_");
			safeType(driver, By.id("ctl00_cphMainContent_txtCompanyName"), "Before_Company_Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"), "GB - United Kingdom");
			safeType(driver, By.id("ctl00_cphMainContent_txtGoverningLaw"), "UK");
			
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
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"verifyAddNewCompanies"})
	public void verifyEditCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtCompanyID"), "After_");
			safeType(driver, By.id("ctl00_cphMainContent_txtCompanyName"), "After_Company_Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"), "GB - United Kingdom");
			safeType(driver, By.id("ctl00_cphMainContent_txtGoverningLaw"), "UK");
			
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
	
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"verifyEditCompanies"})
	public void verifyDeactivateCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"Active");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent_txtStatus_btnToggle"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"verifyDeactivateCompanies"})
	public void verifyActivateCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent_txtStatus_btnToggle"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"verifyEditCompanies"})
	public void verifyDeleteCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"Active");
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
			
			confirmDeleteOperation(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"ethosSignin"})
	public void verifyOnlyActiveCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"Active");
			
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(4)")),"Active");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"verifyOnlyActiveCompanies"})
	public void verifyOnlyInactiveCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
			
			assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_gvCompany>tbody>tr:nth-child(2)>td:nth-child(4)")),"Inactive");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"ethosSignin"})
	public void verifyExportCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"),"(Select)");
			eDownloader(driver, ".grid-pager>a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditCompaniesList", dependsOnMethods = {"ethosSignin"})
	public void verifyResetOperationForCompanies(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenanceGroup(driver, "Companies");
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			String returnText = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"));
			assertEquals(returnText,"Active");
		
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
