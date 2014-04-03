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

public class Product_Group extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] RateTypeList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		// passed 
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void ClientMaintenance_VerifyEditProductGroup(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroupPage(driver);
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div/div/input[1]"));
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/input[1]"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div/div/input[1]"));
				String value=safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/span"));
				driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/input")).sendKeys("c"); 
				value= value+'c';
				safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div/div/input[1]"));// save 
				assertEquals(safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/span")),value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//passed
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void ClientMaintenance_VerifyAddProductGroup(String item) throws InterruptedException, ErrorPageException {
			
			try {
				safeClick(driver, By.linkText("System"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Product Groups"));
				safeClick(driver, By.linkText("Product Groups"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div/input"));
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/input"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div/div/input[1]"));
				driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/input")).sendKeys("010"); 
				driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/input")).sendKeys("newCar"); 
				safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div/div/input[1]"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div/div/input[1]"));
				assertEquals(safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/span")),"newCar");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//passed
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void ClientMaintenance_VerifyDeleteProductGroup(String item) throws InterruptedException, ErrorPageException {
			
			try {
				safeClick(driver, By.linkText("System"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Product Groups"));
				safeClick(driver, By.linkText("Product Groups"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div/input"));
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/table/tbody/tr[5]/td/a"));//select 
				waitForPagetoLoad_Element(driver, 10,By.cssSelector("#ctl00_cphMainContent__btnDelete"));		
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnDelete"));				
				//safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/input[2]")); // delete button in view delivery point page	
				driver.findElement(By.xpath("//div[2]/div[4]/div[2]/div/div/div/div/input[1]")).sendKeys("DELETE");
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/div/input[2]")); // confirm delete	
				waitForPagetoLoad_Element(driver, 10,By.xpath("/div[2]/div[4]/div[2]/div/input"));
				assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"), 10));  
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// passed but have confusion
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void ClientMaintenance_VerifyexportProductGroup(String item) throws InterruptedException, ErrorPageException {
			
			try {
				safeClick(driver, By.linkText("System"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Product Groups"));
				safeClick(driver, By.linkText("Product Groups"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div/input"));
				safeClick(driver, By.linkText("Export"));
				eDownloader(driver, ".grid-pager>a");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//passed
		@Test(dataProvider = "RateTypeList", dependsOnMethods = {"ethosSignin"})
		public void ClientMaintenance_VerifyBackToListInProductGroup(String item) throws InterruptedException, ErrorPageException {
			
			try {
				safeClick(driver, By.linkText("System"));
				waitForPagetoLoad_Element(driver, 10,By.linkText("Product Groups"));
				safeClick(driver, By.linkText("Product Groups"));
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div/input"));
				safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/div/table/tbody/tr[5]/td/a"));//select 
				waitForPagetoLoad_Element(driver, 10,By.cssSelector("#ctl00_cphMainContent__btnToList"));		
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));				
				waitForPagetoLoad_Element(driver, 10,By.xpath("/div[2]/div[4]/div[2]/div/input")); 
				assertEquals(safeGetText(driver,By.cssSelector("#ctl00_lblTitle")),"Product Group Maintenance");
				
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