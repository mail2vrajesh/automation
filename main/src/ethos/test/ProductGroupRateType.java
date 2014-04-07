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

public class ProductGroupRateType extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] RateTypeList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyRateTypeForCountry(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCountry"),"GB - United Kingdom");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(2)")),"GB");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyRateTypeForCategory(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateCategory"),"Distribution");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(5)")),"Distribution");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyRateTypeForClass(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateTypeClass"),"Distribution Loss");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(6)")),"Distribution Loss");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyRateTypeActive(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Active");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(17)")),"Active");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyRateTypeInactive(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus"),"Inactive");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(17)")),"Inactive");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditRateTypeSave(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(4)>td>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRateTypeClass"),"Capacity Charge");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlChargePeriod"),"Month");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditRateTypeCancel(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(4)>td>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRateTypeClass"),"Capacity Charge");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlChargePeriod"),"Month");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddNewRateType(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlCountry"),"GB - United Kingdom");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtID"), "New Rate Type");
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDescription"), "New Rate Type Description");
				
				safeType(driver, By.cssSelector("#ctl00_cphMainContent_txtDecimalPlaces"),"2");
				safeSelectByText(driver,By.cssSelector("#ctl00_cphMainContent_ddlRateCategory"),"Distribution");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlRateTypeClass"),"Capacity Charge");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlChargeBasis"),"Unit Charge");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlUnitBasis"),"kVA");
				safeSelectByText(driver, By.cssSelector("#ctl00_cphMainContent_ddlChargePeriod"),"Month");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				
				if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
				else
					safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportProductGroupRateType(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Rate Types");
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