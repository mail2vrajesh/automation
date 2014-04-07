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
public class ProductGroupMaintenanceAddEditProductGroups extends ETHOSDomainWraper {
	
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
	public Object [ ][ ] ProductGroupList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "ProductGroupList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddNewProductGroups(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "System", "Product Groups");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			safeType(driver, By.id("ctl00_cphMainContent_txtID"),"AUTO");
			safeType(driver, By.id("ctl00_cphMainContent_txtDescription"),"CREATED BY AUTOMATION");
			
			safeClick(driver, By.id ("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProvider = "ProductGroupList", dependsOnMethods = {"verifyAddNewProductGroups"})
	public void verifyEditProductGroups(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "System", "Product Groups");
			
			int row = returnRowNumber(driver, "#ctl00_cphMainContent_gvProductGroup>tbody", "AUTO", 2);
			String cssId = "#ctl00_cphMainContent_gvProductGroup>tbody>tr:nth-child("+row+")>td>a";
			safeClick(driver, By.cssSelector(cssId));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
			safeType(driver, By.id("ctl00_cphMainContent_txtID"),"MODI");
			safeType(driver, By.id("ctl00_cphMainContent_txtDescription"),"MODIFIED BY AUTOMATION");
			safeClick(driver, By.id ("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "ProductGroupList", dependsOnMethods = {"verifyExportProductGroups"})
	public void verifyDeleteProductGroups(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "System", "Product Groups");
			int row = returnRowNumber(driver, "#ctl00_cphMainContent_gvProductGroup>tbody", "MODI", 2);
			String cssId = "#ctl00_cphMainContent_gvProductGroup>tbody>tr:nth-child("+row+")>td>a";
			safeClick(driver, By.cssSelector(cssId));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
			confirmDeleteOperation(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "ProductGroupList", dependsOnMethods = {"verifyEditProductGroups"})
	public void verifyExportProductGroups(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver, "System", "Product Groups");
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
