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
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class ControlDates extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
		goToPage(new String[]{"Data Management","Control Dates"});
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(textPresent(driver,"Definitions of public and industry holidays for a country",2));
	}

	@Test
	public void verifyDropDowns() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlCountry"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnNextYear"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnPrevYear"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlDateType"), 3));
		
		
		
	}
	@Test
	public void verifyResetBtn() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 1));
	}
	
	@Test
	public void verifyTable() throws Exception
	{
		for(int i=1;i<30;i++)
		assertTrue("Select not available ",elementVisible(driver, By.id("cg"+i), 1));		
	}
	
	
	@Test
	public void verifyEdit() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent__btnEdit"));
		elementVisible(driver, By.id("ctl00_cphMainContent_chk0_0"	), 10);
		for(int i=0;i<12;i++)
			for(int j=0;j<27;j++)
				assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_chk"+i+"_"+j), 2));
		
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
