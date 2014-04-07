package ethos.test;
	
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class SupplierCharges extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] Supplier() {
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
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Supplier Charges");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropdown_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("supplier Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlSupplier"), 3));		
				assertTrue("Product group Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 3));
				assertTrue("Charge Type Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlChargeType"), 3));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyRadio_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("Display radio all records button not exist",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[4]/td[2]/table/tbody/tr/td[1]"),1 ));	
				assertTrue("Display radio latest records button not exist",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[4]/td[2]/table/tbody/tr/td[2]"),1 ));
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyTableContents_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("Supplier charges Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvSupplierCharge"),3));
				assertTrue("Product Group column not there",elementVisible(driver, By.linkText("Product Group"), 1));
				assertTrue("Supplier column not there",elementVisible(driver, By.linkText("Supplier"), 1));
				assertTrue("Volume From  not there",elementVisible(driver, By.linkText("Volume From"), 1));
				assertTrue("Volume To info not there",elementVisible(driver, By.linkText("Volume To"), 1));
				assertTrue("Date From not there",elementVisible(driver, By.linkText("Date From"), 1));
				assertTrue("Charge Type not there",elementVisible(driver, By.linkText("Charge Type"), 1));
				assertTrue("Rate not there",elementVisible(driver, By.linkText("Rate"), 1));
				assertTrue("Charging Basis not there",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/th[10]"), 1));
				assertTrue("Zone not there",elementVisible(driver, By.linkText("Zone"), 1));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "BPO - B.P.");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitTitle(driver, "ETHOS", 10);
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Supplier Charges");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyAddNew_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnSave"));
				assertEquals(safeGetText(driver, By.xpath("//div[3]/div[5]/div/span")), "Add Supplier Charge");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		// not passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifySave_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnCancel"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlRateType"), "BSUoS Charge");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCurrency"), "CHF - Francs, Switzerland");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_txtRate"), "100");
				waitTitle(driver, "ETHOS", 10);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnCancel"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "BG - British Gas");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlChargingBasis"), "Fr/MW");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "03-Apr-2014");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "09-Apr-2014");
				waitTitle(driver, "ETHOS", 10);
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("BackToList"));
				assertTrue(elementPresent(driver, By.linkText("BackToList"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyCancel_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent__btnCancel"));
				safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_gvSupplierCharge"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnAddNew"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvSupplierCharge']//a[text()='Export']"));
				Thread.sleep(2000);
				Robot robot=new Robot();			
				robot.keyPress(KeyEvent.VK_ENTER);
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_SupplierCharges(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Charges");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue("error in apge",elementPresent(driver, By.id("ctl00_lblTitle"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
}
