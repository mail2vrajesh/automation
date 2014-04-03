package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class ExchangeRateTypes extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToExchangeRateTypes();
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Exchange Rate Maintenance"));
	}


	
	@Test
	public void verifyExchangeRateTable() throws Exception
	{
		assertTrue("ExchangeRate table not available",elementVisible(driver, By.id("ctl00_cphMainContent_gvExchangeRateType"), 1));
		assertTrue("Type ID column not there",elementVisible(driver, By.linkText("Type ID"), 1));
		assertTrue("Type Name not there",elementVisible(driver, By.linkText("Type Name"), 1));
		assertTrue("Currency from not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvExchangeRateType']//th[text()='Currency From']"), 1));
		assertTrue("Currency To not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvExchangeRateType']//th[text()='Currency To']"), 1));
		
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=4;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvExchangeRateType']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
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
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add Exchange Rate Type"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_cphMainContent_txtExchangeRateTypeID"), 5));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
		
	}



}
