package ethos.test;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Countries extends CommonUtils{
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToCountries();
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
		elementVisible(driver, By.id("ctl00_cphMainContent_ddlRegionActivityStatus"), 3);
		elementVisible(driver, By.id("ctl00_cphMainContent_ddlCountryActivityStatus"), 3);
	}
	
	@Test
	public void verifyResetButn() throws Exception
	{
		elementVisible(driver, By.id("ctl00_cphMainContent_btnResetFilter"), 3);
		elementVisible(driver, By.id("ctl00_cphMainContent_Button1"), 3);
	}
	@Test
	public void verifyRegionTable() throws Exception
	{
		elementVisible(driver, By.id("ctl00_cphMainContent_gvRegion"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Name']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Country ID']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRegion']//th[@scope='col']/a[text()='Geographic Region Type']"), 1);
	}
	@Test
	public void verifyCountryTable() throws Exception
	{
		elementVisible(driver, By.id("ctl00_cphMainContent_gvCountry"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Name']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Country ID']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Region']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Geographic Region Type']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Currency']"), 1);
		elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//th[@scope='col']/a[text()='Time Zone']"), 1);
		
		
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		for(int i=1;i<=10;i++)
			elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//tr["+(i+1)+"]//td["+i+"]/a[text()='Select']"), 1);
	}
	
	@Test
	public void verifyExportLink() throws Exception
	{
		for(int i=1;i<=10;i++)
			elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvCountry']//tr["+(i+1)+"]//td["+i+"]/a[text()='Export']"), 1);
	}
}
