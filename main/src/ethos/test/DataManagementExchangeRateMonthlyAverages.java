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

public class DataManagementExchangeRateMonthlyAverages extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] Exchange() {
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
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Exchange Rate Monthly Averages");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropBox_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Type Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlExchangeRateType"), 3));		
				assertTrue("month not exist",elementVisible(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[2]/td[2]/span/img"), 3));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyApplyFunctionality_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlExchangeRateType"), "Dollar Euro Exchange Rate");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtYearMonth"), "Mar-14");
				safeClick(driver, By.id("ctl00_cphMainContent_btnYearMonth"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage"));
				assertTrue("There are no items to display",elementVisible(driver, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		///passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyApplyPresence_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Apply button is missing",elementVisible(driver, By.id("ctl00_cphMainContent_btnYearMonth"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Reset button does not exist",elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyResetFunctionality_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlExchangeRateType"), "Dollar Euro Exchange Rate");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtYearMonth"), "Mar-14");
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				assertEquals(safeGetText(driver, By.id("ctl00_cphMainContent_txtYearMonth")), "");		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyTableContents_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue("Table does not exist",elementVisible(driver, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage"), 3));
				assertTrue("Type column not there",elementVisible(driver, By.linkText("Type"), 3));
				assertTrue("month column not there",elementVisible(driver, By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr/th[3]"), 3));
				assertTrue("Monthly Average  column not there",elementVisible(driver, By.linkText("Monthly Average"), 3));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "View Exchange Rate Monthly Average");		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifySelect_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "View Exchange Rate Monthly Average");		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyButtons_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				assertTrue("Edit button does not exist",elementVisible(driver, By.id("ctl00_cphMainContent__btnEdit"), 3));
				assertTrue("Delete column not there",elementVisible(driver, By.id("ctl00_cphMainContent__btnDelete"), 3));
					
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyEdit_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnEdit"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtValue"), "1");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				waitForPagetoLoad_Element(driver, 10, By.id("__lblctl00_cphMainContent_txtValue"));
				assertEquals(safeGetText(driver, By.id("__lblctl00_cphMainContent_txtValue")), "1.0000");	
					
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyDelete_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
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
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyBackToList_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnToList"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
				waitTitle(driver, "ETHOS", 10);
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "View Exchange Rate Monthly Average");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyDeleteLink_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("Delete link is not there", elementPresent(driver, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage_ctl02_lnkDelete"), 10));
				safeClick(driver, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage_ctl02_lnkDelete"));
				dismissAlert(driver, "Are you sure you want to delete Monthly Average 201404?");
				assertTrue("Table data is not there", elementPresent(driver, By.id("ctl00_cphMainContent_gvExchangeRateMonthlyAverage"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();  
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[12]/td/table/tbody/tr/td[16]/a"));
				eDownloader(driver, ".grid-pager>a");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Exchange", dependsOnMethods = {"ethosSignin"})
		public void VerifyAddNew_ExchangeRateMonthlyAverages(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"Exchange Rate Monthly Averages");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnCancel"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Add Exchange Rate Monthly Average");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
}


