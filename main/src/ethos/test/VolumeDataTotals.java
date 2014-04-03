package ethos.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class VolumeDataTotals extends ETHOSDomainWraper{


	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
	}
	@BeforeMethod
	public void init() throws Exception
	{
		goToVolumeDataTotalsPage();	
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
	public void verifyProductInProdGrps() throws Exception
	{
			HashMap<String, String[]> prdGrpProdMap=new HashMap<String, String[]>();
			prdGrpProdMap.put("Electricity",new String[]{"Half Hourly Electricity","Non Half Hourly Electricity"});
			prdGrpProdMap.put("Gas",new String[]{"Gas Supply"});
			prdGrpProdMap.put("Oil",new String[]{"Diesel","Gas Oil","Heavy Fuel Oil","Kerosene","Medium Fuel Oil","Unleaded"});
			for(String prdGrp:prdGrpProdMap.keySet())
			{
				selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), prdGrp);
				waitForPageLoaded(driver);
				ArrayList<String> actProducts=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlProduct"));
				for(String actPrd:actProducts)
					assertTrue("Product "+actPrd+"is shown in products drop down even though not part of prdgroup", Arrays.asList(prdGrpProdMap.get(prdGrp)).contains(actPrd));
			}
	}
	
	@Test
	public void verifyVolumeDataTotals() throws Exception
	{
		selectElectricityProducts();
		safeUnCheck(By.id("ctl00_cphMainContent_chkBestAvailable"));
		safeCheck(By.id("ctl00_cphMainContent_chkDetailedVolume"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		assertTrue(textPresent(driver, "There are no items to display", 5));
		
	}

	@Test
	public void verifyRegisteredAQData() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyMonthlyActualData() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		
		safeCheck(By.id("ctl00_cphMainContent_ChkMonthlyActualId"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyMonthlyTenderForecast() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		
		safeCheck(By.id("ctl00_cphMainContent_ChkMonthlyTenderId"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyBilledDataVolData() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		
		safeCheck(By.id("ctl00_cphMainContent_ChkBilledId"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	
	@Test
	public void verifyEmptyVolumeDataTotals() throws Exception
	{
		selectElectricityProducts();
		safeUnCheck(By.id("ctl00_cphMainContent_chkBestAvailable"));
		safeCheck(By.id("ctl00_cphMainContent_chkDetailedVolume"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		assertTrue(textPresent(driver, "There are no items to display", 5));
		
	}

	
	@Test
	public void verifyDataForMultipleSites() throws Exception
	{
		selectElectricityProducts();
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		//select All
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		assertTrue(textPresent(driver, "There are no items to display", 5));
		
	}
	
	//Need site with monthly data
	@Test
	public void verifyVolDataWithSiteMonthlyData() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		
		safeCheck(By.id("ctl00_cphMainContent_ChkBilledId"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	//Need site with Daily interval data
	@Test
	public void verifyVolDataWithSiteDailyData() throws Exception
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		
		safeCheck(By.id("ctl00_cphMainContent_ChkBilledId"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	//Need site with Daily interval data
	@Test
	public void verifyVolDataWithSiteHourlyData() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
		
	@Test
	public void verifyAggDataSiteLevel() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_0"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyAggDataSiteLevelForSingleDP() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_0"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyAggDataSiteLevelForMultipleDP() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_0"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyDpSelectionNotAllowed() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_0"));
		findDelPoint("1620000714190");
		assertFalse("Delivery Point is allowed to select even though site is selected",elementPresent(driver, By.xpath("//span[text()='1620000714190']/preceding-sibling::input[@type='checkbox']"), 2));
	}
	
	
	@Test
	public void verifyAggDataSiteLevelNo() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	
	@Test
	public void verifyDpSelectionAllowed() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		findDelPoint("1620000714190");
		assertTrue("Delivery Point is allowed to select even though site is selected",elementPresent(driver, By.xpath("//span[text()='1620000714190']/preceding-sibling::input[@type='checkbox']"), 2));
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
		findDelPoint( "1620000714190");
		assertTrue("Inactive didn'twork",textPresent(driver, "1620000714190", 2));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnReset"));
		waitForPageLoaded(driver);
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
	public void verifyUnitBasisList() throws Exception
	{
			HashMap<String, String[]> prdGrpUnitBaseMap=new HashMap<String, String[]>();
			prdGrpUnitBaseMap.put("Electricity",new String[]{"Factor","kVA","kVArh","kW","kWh","MW","MWh","Percentage","YearMonth"});
			prdGrpUnitBaseMap.put("Gas",new String[]{"kW","kWh","M3","MWh","Percentage","therm"});
			prdGrpUnitBaseMap.put("Oil",new String[]{"barrel","litre","Percentage","tonne"});
			for(String prdGrp:prdGrpUnitBaseMap.keySet())
			{
				selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), prdGrp);
				waitForPageLoaded(driver);
				ArrayList<String> actDataTypes=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlUnitBasis"));
				for(String unitType:prdGrpUnitBaseMap.get(prdGrp))
					assertTrue("unitType "+unitType+"is shown in products drop down even though part of prdgroup"+prdGrp, actDataTypes.contains(unitType));
			}
	}

	@Test
	public void verifyNoToOutputFile() throws Exception
	{
		selectElectricityProducts();
		safeUnCheck(By.id("ctl00_cphMainContent_chkBestAvailable"));
		safeCheck(By.id("ctl00_cphMainContent_chkDetailedVolume"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		assertTrue(textPresent(driver, "There are no items to display", 5));
		
	}

	@Test
	public void verifyChangeInUnitBasis() throws Exception
	{
		selectElectricityProducts();
		
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		//AggregateSite level
		safeClick(driver, By.id("ctl00_cphMainContent_radioSiteLevel_1"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n1CheckBox"));
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"), "MWh");
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	}
	@Test
	public void verifyYesToOutputFile() throws Exception
	{
		selectElectricityProducts();
		safeUnCheck(By.id("ctl00_cphMainContent_chkBestAvailable"));
		safeCheck(By.id("ctl00_cphMainContent_chkDetailedVolume"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_radioToFile_0"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		assertTrue(textPresent(driver, "There are no items to display", 5));
		
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
	public void verifyBackButton() throws Exception
	{
		selectElectricityProducts();
		safeUnCheck(By.id("ctl00_cphMainContent_chkBestAvailable"));
		safeCheck(By.id("ctl00_cphMainContent_chkDetailedVolume"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Jan-14");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
	
		//assertTrue(textPresent(driver, "There are no items to display", 5));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnBack"));
		assertTrue("Back not moved to previous page",elementVisible(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), 5));
		
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
