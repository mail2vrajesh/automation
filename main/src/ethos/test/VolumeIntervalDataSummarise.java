package ethos.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class VolumeIntervalDataSummarise extends ETHOSDomainWraper{

	
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
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Diesel");
		waitForPageLoaded(driver);
	/*	assertTrue("Site doesn't exist as per product Group",textPresent(driver, "Essex County Counci", 1));*/
/*		assertFalse("Site doesn't exist as per product Group",textPresent(driver, "043 - ICI", 4));*/
	}

	@Test
	public void findDeliveryPoint() throws Exception
	{
		selectElectricityProducts();
		findDelPoint("1620000714190");
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
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n4CheckBox")).getAttribute("checked").equals("true"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked")==null);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n4CheckBox")).getAttribute("checked")==null);
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
/*		safeClick(driver, By.xpath("//img[contains(@alt,'043DDC - Dulux Decorator Centres')]"));*/
/*		safeClick(driver, By.xpath("//img[contains(@alt,'003 - Edinburgh (Gas Supply)')]"));*/
		assertFalse("Delivery Point is allowed to select even though site is selected",elementPresent(driver, By.xpath("//span[text()='8820875008']/preceding-sibling::input"), 2));
	}

	@Test
	public void verifyDataLoadLevelDP() throws Exception
	{
		selectElectricityProducts();
		//selecting site
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		findDelPoint("1620000714190");
		assertTrue("Delivery Point is not allowed to select even though DP is selected",elementPresent(driver, By.xpath("//span[text()='1620000714190']/preceding-sibling::input"), 5));
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
	
	
	@Test
	public void reportAggSiteWithMultiDPs() throws Exception
	{	
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
	
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
	
	@Test
	public void reportAggSiteNoWithMultiDPs() throws Exception
	{	
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "01-Dec-2013");
		safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "01-Mar-2014");
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeClick(driver, By.id("ctl00_cphMainContent_radioSum_0"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioToFile_1"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		textPresent(driver, "Please configure how you want to sum the interval data", 7);
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		assertTrue("Summary Results not shown",textPresent(driver, "Summarise Interval Data - Results", 8));
	}
	
	@Test
	public void reportOutPutFileYes() throws Exception
	{	
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "01-Dec-2013");
		safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "01-Mar-2014");
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeClick(driver, By.id("ctl00_cphMainContent_radioSum_0"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioToFile_0"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		textPresent(driver, "Please configure how you want to sum the interval data", 7);
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		assertTrue("Summary Results not shown",textPresent(driver, "Summarise Interval Data - Results", 8));
		assertTrue("Export link not available",elementVisible(driver, By.id("ctl00_cphMainContent_lnkExport"), 4));
	}
	
	@Test
	public void reportOutPutFileNo() throws Exception
	{	
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "01-Dec-2013");
		safeType(driver, By.id("ctl00_cphMainContent_txtDateTo"), "01-Mar-2014");
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeClick(driver, By.id("ctl00_cphMainContent_radioSum_0"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioToFile_1"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		textPresent(driver, "Please configure how you want to sum the interval data", 7);
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		assertTrue("Summary Results not shown",textPresent(driver, "Summarise Interval Data - Results", 8));
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
