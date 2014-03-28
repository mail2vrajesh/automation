
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

public class OfferContractRuleMaintenanceForGas extends ETHOSDomainWraper {
	
		public RemoteWebDriver driver = null;
	
		@BeforeClass
		public void startSelenium() throws Exception {	
			File file = new File("exe\\IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
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
		public Object [ ][ ] OfferContractMaintenanceList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyRulesListingForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRule"),"Daily Report Delivery Cost");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(3)")),"Daily Report Delivery Cost");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyRuleTypeListingForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRuleType"),"Contract");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(4)")),"Contract");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyActiveListingForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Active");
				
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(12)")),"Active");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyInactiveListingForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
				
				boolean elementPresent = elementPresent(driver,By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr>td>b"),5);
				if (!elementPresent)
					assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(12)")),"Inactive");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyResetListingForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				assertEquals(safeGetSelectedValue(driver,By.cssSelector("#ctl00_cphMainContent_ddlRule")),"(Select Rule)");
				assertEquals(safeGetSelectedValue(driver,By.cssSelector("#ctl00_cphMainContent_ddlRuleType")),"(Select Rule Type)");
				assertEquals(safeGetSelectedValue(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus")),"Active");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddingRuleTypeForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRule"),"Alternative PPR title");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRuleType"),"Contract");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlCountry"),"IE - Ireland");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				
				if(elementPresent(driver,By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),5))
				{
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
				}
				else
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditRuleTypeForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRule"),"Alternative PPR title");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRuleType"),"Contract");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDataEntryTextLength"),"100");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyDeleteRuleTypeForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(13)>a"));
				acceptAlert(driver, "Are you sure you want to delete");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyCancelEditRuleTypeForOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvOfferAndContractRule>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRule"),"Alternative PPR title");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRuleType"),"Contract");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDataEntryTextLength"),"100");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "OfferContractMaintenanceList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportOfferContractMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductionGroupForGas(driver,"Offer & Contract Rules");
				
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
			screenshot(_result, driver);
		}
	
	
	
	}
