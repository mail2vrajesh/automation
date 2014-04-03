package ethos.test;
	
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class SupplierAndSubCategories extends ETHOSDomainWraper {
	
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
		public void VerifyHeading_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Offer Templates");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropBox_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlCountry"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProduct"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyViewAndReset_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnApply"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyListRecords_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnApply"));
				assertEquals(safeGetText(driver, By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b")), "There are no items to display");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitTitle(driver, "ETHOS'",10);
				assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlProduct"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyView_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliers(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitTitle(driver, "ETHOS'",10);
				assertEquals(safeGetText(driver, By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b")), "There are no items to display");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				safeClick(driver, By.linkText("Select"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_lblTitle"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")),"View Offer Template");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyButtons_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				assertTrue("NHH Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvTemplate"),3));
				assertTrue("Supplier column not there",elementVisible(driver, By.linkText("Supplier"), 1));
				assertTrue("Country not there",elementVisible(driver, By.linkText("Country"), 1));
				assertTrue("Purchase Basis not there",elementVisible(driver, By.linkText("Purchase Basis"), 1));
				assertTrue("Product info not there",elementVisible(driver, By.linkText("Product"), 1));
				assertTrue("Offer Description info not there",elementVisible(driver, By.linkText("Offer Description"), 1));
				assertTrue("Short Offer Description info not there",elementVisible(driver, By.linkText("Short Offer Description"), 1));
				assertTrue("Status info not there",elementVisible(driver, By.linkText("Status"), 1));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyEdit_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				safeClick(driver, By.linkText("Select"));
				waitTitle(driver, "ETHOS'",10);
				String value=safeGetText(driver, By.id("__lblctl00_cphMainContent_txtTemplateID"));
				value=value+"G";
				safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtTemplateID"), value);
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				waitForPagetoLoad_Element(driver, 10,By.id("__lblctl00_cphMainContent_txtTemplateID"));
				assertEquals(safeGetText(driver, By.id("__lblctl00_cphMainContent_txtTemplateID")), value);
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDelete_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				safeClick(driver, By.linkText("Select"));
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent__confirmControl_txtConfirm"), "DELETE");
				safeClick(driver, By.id("ctl00_cphMainContent__confirmControl_btnConfirm"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_gvTemplate"), 10));
				} 
			catch (Exception e) {
					e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDeletePresence_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				safeClick(driver, By.linkText("Select"));
				waitTitle(driver, "ETHOS'",10);
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent__btnDelete"), 10));
				} 
			catch (Exception e) {
					e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyBackToList_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
				safeClick(driver, By.linkText("Select"));
				waitTitle(driver, "ETHOS'",10);
				safeClick(driver, By.linkText("Back to list"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnAddNew"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyExport_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnApply"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_gvSupplier"));
				safeClick(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvSupplier']//a[text()='Export']"));
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
		public void VerifyAddNew_Suppliers(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersProperdata(driver,"Suppliers");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"), "Gas Supply");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[3]/td[2]/select"), "BG  - Bulgaria");
				
				safeType(driver, By.id("ctl00_cphMainContent_txtTemplateID"),"555");
				safeType(driver, By.id("ctl00_cphMainContent_txtOfferDescription"),"BGB");
				safeType(driver, By.id("ctl00_cphMainContent_txtShortOfferDescription"),"BGBB");
				safeType(driver, By.id("ctl00_cphMainContent_txtFileName"),"Filename");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlLayoutType"), "Fixed Length");
				safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_gvTemplate"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		
		//Supplier market share
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Supplier Market Share");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropDown_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlSupplier"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProduct"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyButtons_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnRun"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnExport"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyRunButton_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "AIR - Airtricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"), "MWh");
				safeClick(driver, By.id("ctl00_cphMainContent_btnRun"));
				waitForPagetoLoad_Element(driver, 10, By.xpath("//div[2]/div[4]/div[2]/div/h3[1]"));
				assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/h3[1]"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportButton_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "AIR - Airtricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"), "MWh");
				safeClick(driver, By.id("ctl00_cphMainContent_btnExport"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_lnkReport"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_lnkReport"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyResetButton_SuppliersMarketShare(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Market Share");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnRun"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "AIR - Airtricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
				waitTitle(driver, "ETHOS'",10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"), "MWh");
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_lnkReport"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		
		//supplier site list
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Supplier Site List");
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropDowns_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlSupplier"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlProduct"), 10));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyDate_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Oil");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "BAY - BAYFORD");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"),"27-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "27-Mar-2014");
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.id("//div[3]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b"));
				assertEquals(safeGetText(driver, By.xpath("//div[3]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b")), "There are no items to display");
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyViewAndReset_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnApply"), 10));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyView_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Oil");
				waitTitle(driver, "ETHOS", 10);
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"), "BAY - BAYFORD");
				waitTitle(driver, "ETHOS", 10);
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"),"27-Mar-2014");
				safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "27-Mar-2014");
				safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.id("//div[3]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b"));
				assertEquals(safeGetText(driver, By.xpath("//div[3]/div[4]/div[2]/div[2]/div/table/tbody/tr/td/b")), "There are no items to display");
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"),"27-Mar-2014");
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitTitle(driver, "ETHOS", 10);
				String value=safeGetText(driver, By.id("ctl00_cphMainContent_txtDateFrom"));
				assertEquals("", value);
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed data problem
		@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
		public void VerifyExport_SuppliersSiteList(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToSuppliersMarketshare(driver,"Supplier Site List");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnApply"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Export"));
				safeClick(driver, By.linkText("Export"));
				eDownloader(driver, ".grid-pager>a");
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		
		
}
