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

public class SystemAndSubCategories extends ETHOSDomainWraper {
	
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
		
		// passed 
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_FuturesIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Futures Index");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
				assertEquals(safeGetText(driver,By.xpath("//div[2]/div[4]/div/span")),"Futures Index Maintenance for Electricity");
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySubHeading_FuturesIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Futures Index");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
				assertEquals(safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[1]/td[1]")),"Primary Market Price Index:");
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyEditButton_FuturesIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Futures Index");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_ddlDataType"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlDataType"),"ELELEBADAYAHEADALLDAY - ELE LEBA Day Ahead All Day");
				safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div/input[1]"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnEdit"));
				assertEquals(safeGetText(driver,By.id("__lbl__lblctl00_cphMainContent_ddlDataType")),"ELELEBADAYAHEADALLDAY - ELE LEBA Day Ahead All Day");
				
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		// passed 
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_DayAheadIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToProductGroup(driver, "ELE", "Day Ahead Index");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertEquals(safeGetText(driver,By.id("ctl00_lblTitle")),"DayAhead Index Maintenance for Electricity");
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
		}
		// passed 
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySubHeading_DayAheadIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToProductGroup(driver, "ELE", "Day Ahead Index");
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertEquals(safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td[1]")),"Market Price Index:");
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		// passed 
				@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
				public void VerifyEditButton_DayAheadIndexMaintenance(String item) throws InterruptedException, ErrorPageException {
							
					try {
						navigateToProductGroup(driver, "ELE", "Day Ahead Index");
						waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
						safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
						waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
						safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlDataType"),"ELEEFAPEAKLOAD - EFA Electricity Peak Load Prices");
						safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
						waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div/span"));
						assertEquals(safeGetText(driver,By.id("__lbl__lblctl00_cphMainContent_ddlDataType")),"ELEEFAPEAKLOAD - EFA Electricity Peak Load Prices");
						} 
						catch (Exception e) {
						e.printStackTrace();
						}
				}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_CountriesAndRegions(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToSecurityRoles(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertEquals(safeGetText(driver,By.id("ctl00_lblTitle")),"Roles");
						
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyTable_CountriesAndRegions(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToSecurityRoles(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/table/tbody"), 10));  
				assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/table/tbody/tr[1]"), 10)); 
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_CountriesAndRegions(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToSecurityRoles(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				clickLink(driver,"Select");
				assertTrue(elementPresent(driver, By.id("ctl00_lblTitle"), 10));  
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_CountriesAndRegions(String item) throws InterruptedException, ErrorPageException {
			try 		{
				navigateToSecurityRoles(driver);
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
		public void VerifyAddnew_CountriesAndRegions(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToSecurityRoles(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_lblTitle"), 10));  
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertTrue(elementPresent(driver, By.id("ctl00_lblTitle"), 10));  
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySubHeading_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_lblTitle"), 10));  
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropDown_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlDistributor"),"14 - Central Networks West (ELE)");
				waitTitle(driver, "Ethos", 10);
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlProfileClass"),"05 - Commercial Maximum Demand - Low Load Factor");
				waitTitle(driver, "Ethos", 10);
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyTable_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_gvDPConfiguration"), 10));   
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				clickLink(driver,"Select");
				waitTitle(driver, "Ethos", 10);
				assertEquals(safeGetText(driver,By.id("ctl00_lblTitle")),"View NHH Configuration");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyDeleteLink_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				clickLink(driver,"Select");
				waitTitle(driver, "Ethos", 10);
				safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
				driver.findElement(By.xpath("//div[2]/div[4]/div[2]/div/div/div/div/input[1]")).sendKeys("DELETE");
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/div/input[2]"));
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnAddNew"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
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
		public void VerifyAddNewLink_NHHConfiguration(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToNHHConfiguration(driver);
				waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Export"));
				assertEquals(safeGetText(driver,By.id("ctl00_lblTitle")),"Add NHH Configuration");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Currency Maintenance");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyDropDown_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"), "Inactive");
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyReset_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnResetFilter"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyTableContents_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_gvCurrency"), 10));
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifySelectLink_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				clickLink(driver, "Select");
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_lblTitle"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "View Currency");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
		//passed
		@Test(dataProvider = "Future", dependsOnMethods = {"ethosSignin"})
		public void VerifyExportLink_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
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
		public void VerifyAddNew_Currencies(String item) throws InterruptedException, ErrorPageException {
					
			try {
				navigateToCurrencies(driver);
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
				safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
				waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent__btnSave"));
				assertEquals(safeGetText(driver, By.id("ctl00_lblTitle")), "Add Currency");
				} 
				catch (Exception e) {
					e.printStackTrace();
					}
			}
}
