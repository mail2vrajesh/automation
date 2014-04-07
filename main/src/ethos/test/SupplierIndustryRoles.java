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

public class SupplierIndustryRoles extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] SupplierIndustryRolesList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "SupplierIndustryRolesList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddingNewSupplierRole(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Supplier Industry Roles");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlRoleType"), "Supplier");
				safeType(driver, By.id("ctl00_cphMainContent_txtID"), "Supplier");
				safeType(driver, By.id("ctl00_cphMainContent_txtDescription"),"Description of the Supplier");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				
				if (elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"), 5))
				{
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
				}
				else
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierIndustryRolesList", dependsOnMethods = {"verifyAddingNewSupplierRole"})
		public void verifyEditingSupplierIndustryRole(String item) throws InterruptedException, ErrorPageException {
			Thread.sleep(1000);
			
			try {
				navigateToProductGroup(driver, "ELE", "Supplier Industry Roles");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSupplierIndustryRole>tbody>tr:nth-child(4)>td>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlRoleType"), "Supplier");
				safeType(driver, By.id("ctl00_cphMainContent_txtID"), "Supplier_ID");
				safeType(driver, By.id("ctl00_cphMainContent_txtDescription"),"Modified Description of the Supplier");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierIndustryRolesList", dependsOnMethods = {"verifyEditingSupplierIndustryRole"})
		public void verifyDeletingSupplierIndustryRole(String item) throws InterruptedException, ErrorPageException {
			Thread.sleep(1000);
			
			try {
				navigateToProductGroup(driver, "ELE", "Supplier Industry Roles");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvSupplierIndustryRole>tbody>tr:nth-child(4)>td>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnDelete"));
				acceptAlert(driver,"Are you sure you want to delete this item?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "SupplierIndustryRolesList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportSupplierIndustryRole(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Supplier Industry Roles");
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