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

public class VolumeIntervalDataSummarise extends CommonUtils{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToVolumeDataIntervalSummarise();
		safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
		waitForPageLoaded(driver);
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
		selectElectricityProducts();
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
		selectElectricityProducts();
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Find didn't work",textPresent(driver, "1620000714190", 2));
		//1620000714190
	}
	
	@Test
	public void chkResetBtn() throws Exception
	{
		selectElectricityProducts();
		safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
		waitForPageLoaded(driver);
		assertTrue("Reset button didn't reset the values",new Select(driver.findElement(By.id("ctl00_cphMainContent_ddlProductGroup"))).getFirstSelectedOption().getText().contains("Select Product Group"));
	}
	
	@Test
	public void chkInactive() throws Exception
	{
		selectElectricityProducts();
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
		selectElectricityProducts();
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnTickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked").equals("true"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked")==null);
	}

	@Test
	public void findDeliveryPointNumber() throws Exception
	{
		selectElectricityProducts();
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Find didn't work",textPresent(driver, "1620000714190", 2));
		//1620000714190
	}

	@Test
	public void verifyAggregateToSiteLevel() throws Exception
	{
		selectElectricityProducts();
		//selecting site
		safeClick(driver, By.xpath("//img[contains(@alt,'043 - ICI')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'043DDC - Dulux Decorator Centres')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'003 - Edinburgh (Gas Supply)')]"));
		assertFalse("Delivery Point is allowed to select even though site is selected",elementPresent(driver, By.xpath("//span[text()='8820875008']/preceding-sibling::input"), 2));
	}

	@Test
	public void verifyDataLoadLevelDP() throws Exception
	{
		selectElectricityProducts();
		//selecting site
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		safeClick(driver, By.xpath("//img[contains(@alt,'Expand 017 - Greene King Brewing & Retailing Ltd')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'GK - Greene King')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'0115 - PE11 1BE (Gas Supply)')]"));
		assertTrue("Delivery Point is not allowed to select even though DP is selected",elementPresent(driver, By.xpath("//span[text()='1234567890456']/preceding-sibling::input"), 5));
	}
	
	@Test
	public void reportEntirePeriod() throws Exception
	{
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "01-Dec-2013");
		safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "01-Mar-2014");
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeClick(driver, By.id("ctl00_cphMainContent_radioSum_0"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioToFile_1"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		textPresent(driver, "Please configure how you want to sum the interval data", 7);
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		assertTrue("Summary Results not shown",textPresent(driver, "Summarise Interval Data - Results", 8));
	}
	

}
