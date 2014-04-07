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

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class DeliveryChargeRates extends ETHOSDomainWraper{
	
	public String ethosUrl;
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ethosUrl = cachedProperties.value("Ethos_url");
		openUrl(ethosUrl);
		login( "madhva", "madhva");

	}
	
	@BeforeMethod
	public void init()
	{
		driver.get(ethosUrl+"/DataManagement/ZoneDeliveryChargeRateGrid.aspx?ZoneDeliveryChargePK=1784");
	}
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Delivery Charge Rates"));
	}

	
	
	@Test
	public void verifyTable() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_gvRate"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRate']//th[text()='Rate Type']"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRate']//th[text()='Value']"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRate']//th[text()='Charging Basis']"), 1));
	}
	
	@Test
	public void verifyEdit() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
		safeClick(driver, By.id("ctl00_cphMainContent_gvRate_ctl02_ctlExpand"));
		assertTrue("Date dropdown not exist for selecting",elementVisible(driver, By.id("ctl00_cphMainContent_gvRate_ctl02_gvBreakdown_ctl02_ddlFromMonth"), 4));
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
