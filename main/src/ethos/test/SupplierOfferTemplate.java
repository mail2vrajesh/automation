package ethos.test;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class SupplierOfferTemplate extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] SupplierOfferTemplateList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplateProductGroupDropdown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(2)")),"Electricity");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(3)")),"Gas");
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_cphMainContent_ddlProductGroup>option:nth-child(4)")),"Oil");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplateProductDropdown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Electricity");
				Thread.sleep(2000);
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Half Hourly Electricity");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(3)")),"Non Half Hourly Electricity");
				
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Gas");
				Thread.sleep(2000);
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Gas Supply");
				
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Oil");
				Thread.sleep(2000);
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(2)")),"Diesel");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(3)")),"Gas Oil");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(4)")),"Heavy Fuel Oil");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(5)")),"Kerosene");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(6)")),"Medium Fuel Oil");
				assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlProduct>option:nth-child(7)")),"Unleaded");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplateSupplierDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
					
				 WebElement table = driver.findElement(By.id("ctl00_cphMainContent_ddlSupplier"));
				 List<WebElement> rows = table.findElements(By.tagName("option"));
				 assertEquals(rows.size(), 113);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplateCountryDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
					
				 WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_ddlCountry"));
				 List<WebElement> rows = table.findElements(By.tagName("option"));
				 assertEquals(rows.size(), 51);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplatePurchaseBasisDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
					
				 assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlPurchaseBasis>option:nth-child(2)")),"Fixed");
				 assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlPurchaseBasis>option:nth-child(3)")),"Flexible");
				 assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlPurchaseBasis>option:nth-child(4)")),"Index");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifySupplierOfferTemplateStatusDropDown(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
					
				 assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus>option:nth-child(2)")),"Active");
				 assertEquals(safeGetText(driver, By.cssSelector("#ctl00_cphMainContent_ddlActivityStatus>option:nth-child(3)")),"Inactive");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddNewSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				 
				 safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Oil");
				 Thread.sleep(2000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"),"Unleaded");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"),"ESS - ESSO");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"),"IE - Ireland");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPurchaseBasis"),"Fixed");
				 Thread.sleep(1000);
				 safeType(driver, By.id("ctl00_cphMainContent_txtTemplateID"),"Automation");
				 safeType(driver, By.id("ctl00_cphMainContent_txtOfferDescription"),"Automation_Description");
				 safeType(driver, By.id("ctl00_cphMainContent_txtShortOfferDescription"),"Automation_Description_With_Description");
				 safeType(driver, By.id("ctl00_cphMainContent_txtFileName"),"File Names");
				 
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlLayoutType"),"Fixed Length");
				 safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				 
				 String conflictMesg = "";
				 conflictMesg = safeGetText(driver, By.id("ctl00_cphMainContent_CustVal_Conflict"));
				 
				 System.out.println("Conflict Message is "+conflictMesg);
				 if(conflictMesg.contains("This offer template already exists"))
					 safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
				 else
					 safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				 safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				 
				 safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				 safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTemplate>tbody>tr:nth-child(11)>td>a"));
				 safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
				 
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Oil");
				 Thread.sleep(2000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"),"Unleaded");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlSupplier"),"ESS - ESSO");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"),"IE - Ireland");
				 Thread.sleep(1000);
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPurchaseBasis"),"Fixed");
				 Thread.sleep(1000);
				 safeType(driver, By.id("ctl00_cphMainContent_txtTemplateID"),"Modified_Oil");
				 safeType(driver, By.id("ctl00_cphMainContent_txtOfferDescription"),"Modified_Description");
				 safeType(driver, By.id("ctl00_cphMainContent_txtShortOfferDescription"),"Modified_Description");
				 safeType(driver, By.id("ctl00_cphMainContent_txtFileName"),"File Names");
				 
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlLayoutType"),"Fixed Length");
				 safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
				 
				 String conflictMesg = "";
				 conflictMesg = safeGetText(driver, By.id("ctl00_cphMainContent_CustVal_Conflict"));
				 
				 if(conflictMesg.contains("This offer template already exists"))
					 safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
				 else
					 safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyDeleteSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				 safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				 safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlCountry"),"IE - Ireland");
				 safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				 
				 int rowNumber = returnRowNumber(driver, "#ctl00_cphMainContent_gvTemplate>tbody", "AUTOMATION", 5);
				 
				 safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTemplate>tbody>tr:nth-child("+rowNumber+")>td>a"));
				 safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
				 confirmDeleteOperation(driver);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyExportSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				eDownloader(driver, ".grid-pager>a");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyActivateSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				 safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				 
				 safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				 safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTemplate>tbody>tr:nth-child(11)>td>a"));
				 safeClick(driver, By.id("ctl00_cphMainContent_txtStatus_btnToggle"));
				 safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "SupplierOfferTemplateList", dependsOnMethods = {"ethosSignin"})
		public void verifyDeactivateSupplierOfferTemplate(String item) throws InterruptedException, ErrorPageException {
			
			try {
				 navigateToScreen(driver,"Supplier","Supplier Offer Templates");
				 safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				 
				 safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
				 safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTemplate>tbody>tr:nth-child(11)>td>a"));
				 safeClick(driver, By.id("ctl00_cphMainContent_txtStatus_btnToggle"));
				 safeClick(driver, By.id("ctl00_cphMainContent__btnToList"));
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