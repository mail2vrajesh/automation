package ethos.test;
	
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class DataManagementRateAdjustmentFactors extends ETHOSDomainWraper {
	
		public RemoteWebDriver driver = null;
	
		@BeforeClass
		public void startSelenium() throws Exception {	
			File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
			driver = new InternetExplorerDriver();
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
		public Object [ ][ ] Rate() {
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
			screenshot(_result, driver);
		}
		
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Rate Adjustment Factor Maintenance");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifySubHeading_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("sub heading not exist",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]"), 3));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropBox_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Product Group: Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 3));		
				assertTrue("Country/Region: not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlCountry"), 3));
				assertTrue("Rate Type:not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlRateType"), 3));
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Reset button does not exist",elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyResetFunctionality_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_gvRateTypeFactor"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("Reset button does not working",elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyTableContents_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Table does not exist",elementVisible(driver, By.id("ctl00_cphMainContent_gvRateTypeFactor"), 3));
				assertTrue("Product Group column not there",elementVisible(driver, By.linkText("Product Group"), 3));
				assertTrue("Country/Region column not there",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/th[3]"), 3));
				assertTrue("Zone  column not there",elementVisible(driver, By.linkText("Zone"), 3));
				assertTrue("Rate Type  column not there",elementVisible(driver, By.linkText("Rate Type"), 3));
				assertTrue("Description  column not there",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/th[6]"), 3));
				assertTrue("Rate Category  column not there",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/th[7]"), 3));
				assertTrue("Date From  column not there",elementVisible(driver, By.linkText("Date From"), 3));
				assertTrue("Date To  column not there",elementVisible(driver, By.linkText("Date To"), 3));
				assertTrue("Factor  column not there",elementVisible(driver, By.linkText("Factor"), 3));
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifySelect_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "View Rate Adjustment Factor");		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyEdit_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtFactor"), "1");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				waitForPagetoLoad_Element(driver, 10, By.id("__lblctl00_cphMainContent_txtFactor"));
				assertEquals(safeGetText(driver, By.id("__lblctl00_cphMainContent_txtFactor")), "1.000");	
					
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyDelete_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
				waitTitle(driver, "ETHOS", 10);
				dismissAlert(driver, "Are you sure you want to delete");
				assertTrue("Back to list is there or not", elementPresent(driver, By.id("ctl00_cphMainContent__btnToList"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyBackToList_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnToList"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
				waitForPagetoLoad_Element(driver, 10, By.xpath("//div[2]/div[4]/div[2]/div/table"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Rate Adjustment Factor Maintenance");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyDeleteLink_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Delete link is not there", elementPresent(driver, By.id("ctl00_cphMainContent_gvRateTypeFactor_ctl02_lnkDelete"), 10));
				safeClick(driver, By.id("ctl00_cphMainContent_gvRateTypeFactor_ctl02_lnkDelete"));
				dismissAlert(driver, "Are you sure you want to delete Rate Type Factor for DistUnit?");
				assertTrue("Table data is not there", elementPresent(driver, By.id("ctl00_cphMainContent_gvRateTypeFactor"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();  
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRateTypeFactor']//a[text()='Export']"));
				eDownloader(driver, ".grid-pager>a");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Rate", dependsOnMethods = {"ethosSignin"})
		public void VerifyAddNew_RateAdjustmentFactors(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Rate adjustment factors");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[2]"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnSave"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Add Rate Adjustment Factor");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
}



