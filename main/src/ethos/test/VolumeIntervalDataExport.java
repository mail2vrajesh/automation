package ethos.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VolumeIntervalDataExport extends CommonUtils{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToVolumeDataIntervalExport();
	}
	
	@Test
	public void verifyProductGrp() throws Exception
	{
		String[] productGrps={"Electricity","Gas","Oil"};
		ArrayList<String> actProducts=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlProductGroup"));
		for(String product:productGrps)
			Assert.assertTrue(actProducts.contains(product), product +"doesn't contain in the drop down");
		
		Assert.assertTrue(actProducts.size()==productGrps.length, "Size of Expected & actual products don't match");
			
	}

	@Test
	public void verifySiteAsPerProductGrp() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		assertTrue("Site doesn't exist as per product Group",textPresent(driver, "043 - ICI", 4));
		assertTrue("Site doesn't exist as per product Group",textPresent(driver, "Greene King Brewing", 1));
		assertFalse("Site doesn't exist as per product Group",textPresent(driver, "Essex County Council", 1));
		
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Oil");
		waitForPageLoaded(driver);
		assertTrue("Site doesn't exist as per product Group",textPresent(driver, "Essex County Counci", 1));
		assertFalse("Site doesn't exist as per product Group",textPresent(driver, "043 - ICI", 4));
	}
	
	@Test
	public void findDeliveryPoint() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Find didn't work",textPresent(driver, "1620000714190", 2));
		//1620000714190
	}
	
	@Test
	public void chkResetBtnMainContent() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
		waitForPageLoaded(driver);
		assertTrue("Reset button didn't reset the values",new Select(driver.findElement(By.id("ctl00_cphMainContent_ddlProductGroup"))).getFirstSelectedOption().getText().contains("Select Product Group"));
		assertTrue("Reset button didn't reset the values",new Select(driver.findElement(By.id("ctl00_cphMainContent_ddlTimeBasis"))).getFirstSelectedOption().getText().contains("Select Time Basis"));
		assertTrue("Reset button didn't reset the values",safeGetText(driver,By.id("ctl00_cphMainContent_dateFrom")).length()<2);
		assertTrue("Reset button didn't reset the values",safeGetText(driver,By.id("ctl00_cphMainContent_dateTo")).length()<2);
		assertTrue("Reset button didn't reset the values",safeGetText(driver,By.id("ctl00_cphMainContent_txtExportPath")).length()<2);
		
	}
	
	@Test
	public void chkResetBtnSiteDP() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnTickAll"));
		waitForPageLoaded(driver);
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnReset"));
		waitForPageLoaded(driver);
		assertTrue("Reset button didn't reset the values",driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked")==null);
	}
	
	@Test
	public void chkInactive() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_chkShowInactive"));
		waitForPageLoaded(driver);
		
		// 1620000714190 shd be shown as it is inactive
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Inactive didn'twork",textPresent(driver, "1620000714190", 2));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_chkShowInactive"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
	}
	
	@Test
	public void tickAllUntickAll() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnTickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked").equals("true"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked")==null);
	}
	
	@Test
	public void verifyErrorForIncorrectData() throws Exception
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_dateFrom"), "01-Feb-2013");
		safeType(driver, By.id("ctl00_cphMainContent_dateTo"), "01-Mar-2014");
		safeSelectByText(driver,By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");

		safeType(driver, By.id("ctl00_cphMainContent_txtExportPath"), "D:\\test.xlsx");
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		
		assertTrue(textPresent(driver, "Please specify a folder on the file server", 5));
	}

}
