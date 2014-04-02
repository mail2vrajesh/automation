package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NHHConfigurationBands extends CommonUtils{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
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
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Non Half Hourly Meter Configuration for Non Half Hourly Electricity"));
	}

	@Test
	public void verifySubHeading() throws Exception
	{
		assertTrue("Subheading doesn't contain required text",driver.findElement(By.id("maincontent")).getText().contains("Used for storing valid NHH MPAN combinations and for identifying meter type"));
	}
	
	@Test
	public void verifyTablePresent() throws Exception
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
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvDPConfiguration tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvDPConfiguration']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		safeClick(driver, By.linkText("Select"));
		elementVisible(driver, By.id("__lblctl00_cphMainContent_txtID"), 5);
		assertTrue("Page not moved to View NHH page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View NHH Configuration"));
	}

	@Test
	public void verifyDeleteLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvDPConfiguration tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=8;i++)
			assertTrue("Delete not available at "+ i,elementVisible(driver, By.id("ctl00_cphMainContent_gvDPConfiguration_ctl0"+(i+1)+"_lnkDelete"), 1));
		safeClick(driver, By.linkText("Delete"));
		assertTrue(textPresent(driver, "Are you sure you want to delete NHH Configuration Record", 3));
		cancelPopupMessageBox(driver);
	}

	@Test
	public void verifyExportLink() throws Exception
	{
		elementVisible(driver, By.linkText("Export"), 5);
		//safeClick(driver,By.linkText("Export"));
		
	}
	
	@Test
	public void verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add NHH Configuration"));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnSave"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnCancel"), 3));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	
	}


}
