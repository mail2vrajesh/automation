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


public class AddEditClientGroup  extends ETHOSDomainWraper {
	
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
	public Object [ ][ ] ClientGroupList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "ClientGroupList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddNewClientGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenance(driver);
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupID"), "B4_");
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupName"), "Before_Group_Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"), "GB - United Kingdom");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlBuyer"),"a.chebac - Adrian Chebac (JHA Buyer)");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlRollingCoverPlanProfileNumber"),"3");
			safeClick(driver, By.id("ctl00_cphMainContent_chkCreateCompany"));
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
	
	@Test(dataProvider = "ClientGroupList", dependsOnMethods = {"verifyAddNewClientGroup"})
	public void verifyEditClientGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenance(driver);
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupName"),"B4_");
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvClientGroup>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupID"), "AFTR_");
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupName"), "Before_Group_Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress1"),"Address Line 1");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress2"),"Address Line 2");
			safeType(driver, By.id("ctl00_cphMainContent_txtAddress3"),"Address Line 3");
			safeType(driver, By.id("ctl00_cphMainContent_txtTown"), "Town Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtCounty"), "County Name");
			safeType(driver, By.id("ctl00_cphMainContent_txtPostCode"),"ABCDE");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"), "GB - United Kingdom");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlBuyer"),"a.chebac - Adrian Chebac (JHA Buyer)");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlRollingCoverPlanProfileNumber"),"3");
			
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
	
	@Test(dataProvider = "ClientGroupList", dependsOnMethods = {"verifyEditClientGroup"})
	public void verifyDeleteClientGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenance(driver);
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeType(driver, By.id("ctl00_cphMainContent_txtGroupName"),"AFTR_");
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvClientGroup>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
			
			confirmDeleteOperation(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProvider = "ClientGroupList", dependsOnMethods = {"ethosSignin"})
	public void verifyClientGroupExport(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenance(driver);
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			eDownloader(driver, ".grid-pager>a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "ClientGroupList", dependsOnMethods = {"ethosSignin"})
	public void verifyClientGroupExportSiteCount(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToClientMaintenance(driver);
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			eDownloader(driver, "#ctl00_cphMainContent_lnkExportSiteCount", 60000);
			
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
