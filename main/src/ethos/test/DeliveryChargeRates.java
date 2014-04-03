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

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class DeliveryChargeRates extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");

		goToPage(new String[]{"Data Management","Delivery Charges"});
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Delivery Charges"));
	}

	@Test
	public void verifySubHeadings() throws Exception
	{
		driver.getPageSource().contains("Delivery costs for offer analysis and bill checking");
		
	}

	@Test
	public void verifyDropDowns() throws Exception {
		
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 1));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlZone"), 1));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlSupplyType"), 1));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlDataFrom"), 1));
		
	}
	@Test
	public void verifyDisplayRadioBtn() throws Exception {
		
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_radioSelect"), 1));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_radioSelect_0"), 1));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_radioSelect_1"), 1));
		assertTrue(textPresent(driver, "Display:", 1));
		
	}
	
	@Test
	public void verifyResetBtn() throws Exception {
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 1));
	}
	
	@Test
	public void verifyTable() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_gvZoneDeliveryCharge"), 1));
		assertTrue(elementVisible(driver, By.linkText("Distributor"), 1));
		assertTrue(elementVisible(driver, By.linkText("Supply Type"), 1));
		assertTrue(elementVisible(driver, By.linkText("Data From"), 1));
		assertTrue(elementVisible(driver, By.linkText("Data To"), 1));
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		for(int i=1;i<=8;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZoneDeliveryCharge']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));		
		safeClick(driver, By.linkText("Select"));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View Delivery Charge"));
		safeClick(driver, By.linkText("Back to list"));
	}
	
	@Test
	public void verifyDeleteLink() throws Exception
	{
		for(int i=1;i<=8;i++)
			assertTrue("Delete not available at "+ i,elementVisible(driver, By.id("ctl00_cphMainContent_gvZoneDeliveryCharge_ctl0"+(i+1)+"_lnkDelete"), 1));
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
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add Delivery Charge"));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	}

	@Test
	public void verifyExportWithRates() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_lnkExportWithRates"));
		assertTrue("link Click to download the report not exist after clicking export button",elementVisible(driver, By.id("ctl00_cphMainContent_lnkViewReport"), 10));
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
