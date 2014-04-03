	
package ethos.test;
	
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class RequirementFlexibility extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] RequirementFlexibilityList() {
			return new Object [ ] [ ] {{ "System"}};
		}
	
		@Test(dataProvider = "RequirementFlexibilityList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddNewRequirementFlexibility(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Requirement Flexibility");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvOption>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size();
				
				String descriptionCssID = "#ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_txtDescriptionAdd";
				String percentageCssID = "#ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_txtDefaultWeightAdd";
				String addButtonCssID = "#ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_btnAdd";
				
				safeType(driver,By.cssSelector(descriptionCssID),"Automation Entry "+noOfRows);
				safeType(driver,By.cssSelector(percentageCssID),"0");
				safeClick(driver, By.cssSelector(addButtonCssID));
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RequirementFlexibilityList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditRequirementFlexibility(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Requirement Flexibility");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvOption>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size()-1;
				
				String descriptionCssID = "#ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_txtDescription";
				String percentageCssID = "#ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_txtDefaultWeight";
				
				safeType(driver,By.cssSelector(descriptionCssID),"Modified Automation Entry "+noOfRows);
				safeType(driver,By.cssSelector(percentageCssID),"0");
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RequirementFlexibilityList", dependsOnMethods = {"ethosSignin"})
		public void verifyDeleteRequirementFlexibility(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "Requirement Flexibility");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvOption>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size()-1;
				
				String deleteButtonId = "ctl00_cphMainContent_gvOption_ctl"+noOfRows+"_btnDelete";
				
				safeClick(driver, By.id(deleteButtonId));
				Thread.sleep(2000);
				acceptAlert(driver, "Are you sure");
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				
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
		