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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class SystemUsers extends ETHOSDomainWraper{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToSystemUsersPage();
	}

	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Users"));
	}

	@Test
	public void verifyRoleDropDown() throws Exception
	{
		assertTrue("Role Drop down doesn't exist",elementVisible(driver,By.id("ctl00_cphMainContent_ddlRole"),3));
	}
	
	@Test
	public void verifyUserIdSearch() throws Exception
	{
		assertTrue("UserId search Not available",elementVisible(driver,By.id("ctl00_cphMainContent_txtUserID"),3));
	}
	
	@Test
	public void verifyResetBtn() throws Exception
	{
		assertTrue("Reset Button Not available",elementVisible(driver,By.id("ctl00_cphMainContent_btnResetFilter"),3));
	}
	
	@Test
	public void verifyTablePresent() throws Exception
	{
		assertTrue("User Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvUser"),3));
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvUser']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		safeClick(driver, By.linkText("Select"));
		elementVisible(driver, By.id("__lblctl00_cphMainContent_txtID"), 5);
		assertTrue("Page not moved to Users page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View User"));
	}
	
	@Test
	public void verifyExportLink() throws Exception
	{
		elementVisible(driver, By.linkText("Export"), 5);
		safeClick(driver,By.linkText("Export"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
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
