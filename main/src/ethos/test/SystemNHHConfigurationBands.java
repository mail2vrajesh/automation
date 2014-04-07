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

public class SystemNHHConfigurationBands extends ETHOSDomainWraper {
	
		public RemoteWebDriver driver = null;
	
		@BeforeClass
		public void startSelenium() throws Exception {	

/*			driver=(RemoteWebDriver) getDriver(cachedProperties.value("browser"));
*/
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
		public Object [ ][ ] Future() {
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
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				assertEquals(safeGetText(driver,By.id("ctl00_lblTitle")),"Non Half Hourly Meter Time Bands");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySubHeading_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				String value=safeGetText(driver,By.id("maincontent"));
				assertTrue(elementPresent(driver, By.id("maincontent"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyTable_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_gvDPConfigDetail"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				clickLink(driver, "Select");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnToList"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent__btnToList"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyDeleteLink_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				clickLink(driver, "Select");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnToList"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
				dismissAlert(driver, "Are you sure you want to delete this item?");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnToList"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent__btnToList"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Export"));
				safeClick(driver, By.linkText("Export"));
				eDownloader(driver, ".grid-pager>a");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyAddNew_NHHConfigurationBands(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfigurationBrands(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnSave"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlRateType"), "BSUoS Charge");
				waitTitle(driver, "Ethos", 10);
				safeSelectByText(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"), "BSUoS Charge");
				waitTitle(driver, "Ethos", 10);
				driver.findElement(By.id("ctl00_cphMainContent_txtConsumptionFactor")).sendKeys("555");
				driver.findElement(By.id("ctl00_cphMainContent_txtTimePatternRegimeCode")).sendKeys("55");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				assertEquals(safeGetText(driver, By.id("__lbl__lblctl00_cphMainContent_ddlRateType")), "BSUoS Charge");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		
}
