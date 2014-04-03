package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class Currencies extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToCurrenciesPage();
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Currency Maintenance"));
	}


	@Test
	public void verifyStatusDropDown() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlActivityStatus"), 3));
	}
	
	@Test
	public void verifyResetButn() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3));
	}
	
	@Test
	public void verifyCurrencyTable() throws Exception
	{
		assertTrue("currency table not available",elementVisible(driver, By.id("ctl00_cphMainContent_gvCurrency"), 1));
		assertTrue("Currency ID column not there",elementVisible(driver, By.linkText("Currency ID"), 1));
		assertTrue("Currency Name not there",elementVisible(driver, By.linkText("Currency Name"), 1));
		assertTrue("Major Unit Name not there",elementVisible(driver, By.linkText("Major Unit Name"), 1));
		assertTrue("Major Unit Symbol info not there",elementVisible(driver, By.linkText("Major Unit Symbol"), 1));
		assertTrue("Divisor not there",elementVisible(driver, By.linkText("Divisor"), 1));
		
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCurrency']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
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
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add Currency"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_cphMainContent_txtCurrencyID"), 5));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
		
	}



}
