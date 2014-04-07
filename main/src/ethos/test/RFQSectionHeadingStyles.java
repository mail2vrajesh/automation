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

public class RFQSectionHeadingStyles extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
		goToPage(new String[]{"System","RFQ Section Sub Heading Styles"});
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("RFQ Section Sub-Heading Styles"));
	}


	@Test
	public void verifyTable() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_gvRFQSubSectionHeading"), 1));
		assertTrue(elementVisible(driver, By.linkText("Section Sub-Heading Style Description"), 1));
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		assertTrue("Select not available ",elementVisible(driver, By.linkText("Select"), 1));		
		safeClick(driver, By.linkText("Select"));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View RFQ Section Sub-Heading Styles"));
		safeClick(driver, By.linkText("Back to list"));
	}
	
	
	@Test
	public void verifyExportLink() throws Exception
	{
		safeClick(driver, By.linkText("Export"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add RFQ Section Sub-Heading Styles"));
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
