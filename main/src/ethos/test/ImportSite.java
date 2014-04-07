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

public class ImportSite extends ETHOSDomainWraper {

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
	public Object [ ][ ] ImportSite() {
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
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ExportTemplateInProductGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
				navigateToClientMaintenanceGroup(driver, "Import Site Data");
				assertTrue(isElementPresent(driver, By.id("ctl00_cphMainContent_btnExport")));
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_DownloadExportTemplateInProductGroup() throws Exception  {
		try {
			navigateToClientMaintenanceGroup(driver, "Import Site Data");
			eDownloader(driver, ".grid-pager>a");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ImportSiteAddressSelected(String item) throws InterruptedException, ErrorPageException {
		
		try {
				navigateToClientMaintenanceGroup(driver, "Import Site Data");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeCheck(driver, By.id("ctl00_cphMainContent_chkImportAddress"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeType(driver, By.id("ctl00_cphMainContent_txtFile"), "74640302Daily_Interval.xls");
				safeClick(driver, By.id("ctl00_cphMainContent_btnImport"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ImportSiteAddressNotSelected(String item) throws InterruptedException, ErrorPageException {
		
		try {
				navigateToClientMaintenanceGroup(driver, "Import Site Data");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeUnCheck(driver, By.id("ctl00_cphMainContent_chkImportAddress"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeType(driver, By.id("ctl00_cphMainContent_txtFile"), "74640302Daily_Interval.xls");
				safeClick(driver, By.id("ctl00_cphMainContent_btnImport"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ImportSiteProductDeatailsSelected(String item) throws InterruptedException, ErrorPageException {
		
		try {
				navigateToClientMaintenanceGroup(driver, "Import Site Data");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeCheck(driver, By.id("ctl00_cphMainContent_chkImportProduct"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeType(driver, By.id("ctl00_cphMainContent_txtFile"), "74640302Daily_Interval.xls");
				safeClick(driver, By.id("ctl00_cphMainContent_btnImport"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Test(dataProvider = "ImportSite", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ImportSiteProductDeatailsNotSelected(String item) throws InterruptedException, ErrorPageException {
		
		try {
				navigateToClientMaintenanceGroup(driver, "Import Site Data");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeUnCheck(driver, By.id("ctl00_cphMainContent_chkImportProduct"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				safeType(driver, By.id("ctl00_cphMainContent_txtFile"), "74640302Daily_Interval.xls");
				safeClick(driver, By.id("ctl00_cphMainContent_btnImport"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnExport"));
				
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
}


