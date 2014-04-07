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

public class ScheduledTasks extends ETHOSDomainWraper {
	
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
		public Object [ ][ ] ScheduledTasksList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddingNewScheduledTasks(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"));
				safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlTask"), "Get Spectrometer CSV files via FTP and import");
				safeType(driver, By.id("ctl00_cphMainContent_txtTime"), "1200");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_chkDays_0"));
				
				safeType(driver, By.id("ctl00_cphMainContent_txtEmailOnFailure"),"abc@xyz.com");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"verifyAddingNewScheduledTasks"})
		public void verifyEditingScheduledTasks(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks>tbody>tr:nth-child(3)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnEdit"));
				
				safeType(driver, By.id("ctl00_cphMainContent_txtTime"), "1300");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_chkDays_1"));
				
				safeType(driver, By.id("ctl00_cphMainContent_txtEmailOnFailure"),"abcd@wxyz.com");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnSave"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"verifyEditingScheduledTasks"})
		public void verifyDeletingScheduledTasks(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks>tbody>tr:nth-child(3)>td:nth-child(9)>a"));
				acceptAlert(driver,"Delete the scheduled task?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"verifyAddingNewScheduledTasks"})
		public void verifyDeactivatingScheduledTasks(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks>tbody>tr:nth-child(3)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_ctrlActivityStatus_btnToggle"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"verifyDeactivatingScheduledTasks"})
		public void verifyActivatingScheduledTasks(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks>tbody>tr:nth-child(3)>td:nth-child(1)>a"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_ctrlActivityStatus_btnToggle"));
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "ScheduledTasksList", dependsOnMethods = {"ethosSignin"})
		public void verifyActivateDeactivateScheduledTasksMainPage(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToScreen(driver, "Data Management","Scheduled Tasks");
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks_ctl03_ctrlActivityStatus_btnToggle"));
				Thread.sleep(3000);
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTasks_ctl03_ctrlActivityStatus_btnToggle"));
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