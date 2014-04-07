package ethos.test;
	
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class DataManagementEFACalendar extends ETHOSDomainWraper {
	
		public RemoteWebDriver driver = null;
	
		@BeforeClass
		public void startSelenium() throws Exception {	
			File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
			driver = new FirefoxDriver();
			/*driver = new InternetExplorerDriver();*/
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
		public Object [ ][ ] EFA() {
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
		@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
		public void VerifyHeading_EFACalendar(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"EFA Calendar");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]"));
				assertTrue(driver.findElement(By.xpath("//div[2]/div[4]/div[2]")).getText().contains("The EFA calendar used for UK power."));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
		public void VerifyYear_EFACalendar(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"EFA Calendar");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]"));
				assertTrue("Previous year button not exist",elementVisible(driver, By.id("ctl00_cphMainContent_btnPrevYear"), 3));
				assertTrue("Next year button not exist",elementVisible(driver, By.id("ctl00_cphMainContent_btnNextYear"), 3));
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
		public void VerifyNextYearFunctionality_EFACalendar(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"EFA Calendar");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]"));
				int value=Integer.parseInt(safeGetText(driver, By.id("ctl00_cphMainContent_lblYear")));
	            value=value+1;
				safeClick(driver, By.id("ctl00_cphMainContent_btnNextYear"));
				waitTitle(driver, "ETHOS", 10);
				assertEquals(safeGetText(driver, By.id("ctl00_cphMainContent_lblYear")), value);
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		//passed
		@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
		public void VerifyPreiviousYearFunctionality_EFACalendar(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"EFA Calendar");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]"));
				int value=Integer.parseInt(safeGetText(driver, By.id("ctl00_cphMainContent_lblYear")));
	            value=value-1;
				safeClick(driver, By.id("ctl00_cphMainContent_btnPrevYear"));
				waitTitle(driver, "ETHOS", 10);
				assertEquals(safeGetText(driver, By.id("ctl00_cphMainContent_lblYear")), value);
			} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		//passed
		@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
		public void VerifyTable_EFACalendar(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToDataManagement(driver,"EFA Calendar");
				waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]"));
				int i=Integer.parseInt(safeGetText(driver, By.id("ctl00_cphMainContent_lblYear")));
				assertTrue("Table does not exist",elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table[2]"), 3));		
				assertTrue(" column does not exist Winter-"+(i-1),elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table[2]/tbody/tr/td[2]"), 3));		
				assertTrue(" column does not exist Summer-"+i,elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table[2]/tbody/tr/td[3]"), 3));		
				assertTrue(" column does not exist Winter-"+i,elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/table[2]/tbody/tr/td[2]"), 3));		
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
		}
		
}




