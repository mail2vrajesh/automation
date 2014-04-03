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

public class Countries extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToCountriesPage();
	}
	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Countries & Regions"));
	}

	@Test
	public void verifySubHeadings() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Countries & Regions"));
		driver.getPageSource().contains("<h1>Regions</h1>");
		driver.getPageSource().contains("<h1>Countries</h1>");
		
	}

	@Test
	public void verifyStatusDropDown() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlRegionActivityStatus"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlCountryActivityStatus"), 3));
	}
	
	@Test
	public void verifyResetButn() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_Button1"), 3));
	}
	@Test
	public void verifyRegionTable() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_gvRegion"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Name']"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Country ID']"), 1));
		assertTrue(elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Geographic Region Type']"), 1));
	}
	@Test
	public void verifyCountryTable() throws Exception
	{
		assertTrue("country table not available",elementVisible(driver, By.id("ctl00_cphMainContent_gvCountry"), 1));
		assertTrue("Name column not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Name']"), 1));
		assertTrue("CountryId not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Country ID']"), 1));
		assertTrue("Region not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Region']"), 1));
		assertTrue("Geography info not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Geographic Region Type']"), 1));
		assertTrue("Currency not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Currency']"), 1));
		assertTrue("Timezone not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Time Zone']"), 1));
		
		
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
	}
	
	@Test
	public void verifyExportLink() throws Exception
	{
		safeClick(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//a[text()='Export']"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnCountry"));
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
