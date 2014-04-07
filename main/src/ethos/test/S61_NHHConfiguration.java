package ethos.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class S61_NHHConfiguration extends ETHOSDomainWraper{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		if(elementVisible(driver, By.id("ctl00_cphMainContent__btnCancel"), 2))
			safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
		goToNHHConfiguration();
	}

	
	@Test
	public void S61_1_verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Non Half Hourly Meter Configuration for Non Half Hourly Electricity"));
	}

	@Test
	public void S61_2_verifySubHeading() throws Exception
	{
		assertTrue("Subheading doesn't contain required text",driver.findElement(By.id("maincontent")).getText().contains("Used for storing valid NHH MPAN combinations and for identifying meter type"));
	}
	
	@Test
	public void S61_3_verifyDropDowns() throws Exception
	{
		assertTrue("Distributor drop down not there",elementVisible(driver,By.id("ctl00_cphMainContent_ddlDistributor"),2));
		assertTrue("ProfileClass drop down not there",elementVisible(driver,By.id("ctl00_cphMainContent_ddlProfileClass"),2));
	}
	
	@Test
	public void S61_4_verifyButtons() throws Exception
	{
		assertTrue("Reset button not there",elementVisible(driver,By.id("ctl00_cphMainContent_btnResetFilter"),2));
	}
	@Test
	public void S61_5_verifyTablePresent() throws Exception
	{
		assertTrue("NHH Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvDPConfiguration"),3));
		assertTrue("Distributor column not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvDPConfiguration']//th[text()='Distributor']"), 1));
		assertTrue("ProfileClass not there",elementVisible(driver, By.linkText("Profile Class"), 1));
		assertTrue("MTC not there",elementVisible(driver, By.linkText("MTC"), 1));
		assertTrue("SSC info not there",elementVisible(driver, By.linkText("SSC"), 1));
		assertTrue("Estimated Annual Consumption not there",elementVisible(driver, By.partialLinkText("Estimated"), 1));
		//assertTrue("Timezone not there",elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvDPConfiguration']//th[text()='Distributor']"), 1));
	}
	
	@Test
	public void S61_6_verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvDPConfiguration tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvDPConfiguration']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		safeClick(driver, By.linkText("Select"));
		elementVisible(driver, By.id("__lblctl00_cphMainContent_txtID"), 5);
		assertTrue("Page not moved to View NHH page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View NHH Configuration"));
	}

	@Test
	public void S61_7_verifyDeleteLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvDPConfiguration tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=8;i++)
			assertTrue("Delete not available at "+ i,elementVisible(driver, By.id("ctl00_cphMainContent_gvDPConfiguration_ctl0"+(i+1)+"_lnkDelete"), 1));
		safeClick(driver, By.linkText("Delete"));
		assertTrue(textPresent(driver, "Are you sure you want to delete NHH Configuration Record", 3));
		cancelPopupMessageBox(driver);
	}

	@Test
	public void S61_8_verifyExportLink() throws Exception
	{
		elementVisible(driver, By.linkText("Export"), 5);
		//safeClick(driver,By.linkText("Export"));
		
	}
	
	@Test
	public void S61_9_verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add NHH Configuration"));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnSave"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnCancel"), 3));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	
	}


}
