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

public class GenericLookupItems extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToGenericLookupItems();
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Generic Lookup Class Maintenance"));
	}

	@Test
	public void verifySubHeadings() throws Exception
	{
		driver.getPageSource().contains("Used to maintain the list of high level generic look up classes");
		
	}

	@Test
	public void verifyLookupTable() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_gvGenericLookupClass"), 1));
		assertTrue(elementVisible(driver, By.linkText("Lookup Class"), 1));
		assertTrue(elementVisible(driver, By.linkText("Description"), 1));
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		for(int i=1;i<=8;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvGenericLookupClass']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));		
		safeClick(driver, By.linkText("Select"));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View Generic Lookup Class"));
		safeClick(driver, By.linkText("Back to list"));
	}
	
	@Test
	public void verifyDeleteLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvDPConfiguration tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=8;i++)
			assertTrue("Delete not available at "+ i,elementVisible(driver, By.id("ctl00_cphMainContent_gvGenericLookupClass_ctl0"+(i+1)+"_lnkDelete"), 1));
		safeClick(driver, By.linkText("Delete"));
		assertTrue(textPresent(driver, "Are you sure you want to delete", 3));
		cancelPopupMessageBox(driver);
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
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add Generic Lookup Class"));
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
