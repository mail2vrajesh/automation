package ethos.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class S58_CountryZones extends ETHOSDomainWraper{
	
	@BeforeClass
	public void startBrowser() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
	}
	@BeforeMethod
	public void init() throws Exception
	{
		goToCountryZonesPage();
	}
	@Test
	public void S58_1_verifyHeading() throws Exception
	{
		assertTrue("Heading doesnt contain Zones",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Zones"));
	}

	@Test
	public void S58_2_verifySubHeading() throws Exception
	{
		assertTrue("Subheading doesn't contain required text",driver.findElement(By.id("maincontent")).getText().contains("Definitions of zones associating a country with a product group"));
	}
	
	@Test
	public void S58_3_verifyDropDownsInZonePage() throws Exception
	{
		assertTrue("Zones Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlZone"), 3));		
		assertTrue("Country Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlCountry"), 3));
		assertTrue("ProductGroup Drop down not exist",elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 3));
	}

	
	@Test
	public void S58_4_verifyResetButn() throws Exception
	{
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3));
	}
	
	
	@Test (description="ds")
	public void S58_5_verifyCountryTable() throws Exception
	{
		assertTrue("table not available",elementVisible(driver, By.id("ctl00_cphMainContent_gvZone"), 1));
		assertTrue("Country column not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZone']//th[@scope='col']/a[text()='Country']"), 1));
		assertTrue("Product Group not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZone']//th[@scope='col']/a[text()='Product Group']"), 1));
		assertTrue("ZoneId not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZone']//th[@scope='col']/a[text()='Zone ID']"), 1));
		assertTrue("ZoneName info not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZone']//th[@scope='col']/a[text()='Zone Name']"), 1));
		
	}
	
	
	@Test
	public void S58_6_verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvZone']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		
		safeClick(driver, By.linkText("Select"));
		assertTrue("Din't move to ViewZone page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View Zone"));
	}
	
	@Test
	public void S58_7_verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	}


	

}
