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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class S12 extends ETHOSDomainWraper{


	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		goToSummaryData();
	}
	
	@Test
	public void test1() throws Exception
	{
		String[] productGrps={"Electricity","Gas","Oil"};
		ArrayList<String> actProducts=getOptionsDropdown(By.xpath("//select[contains(@id,'ddlProductGroup')]"));
		for(String product:productGrps)
			Assert.assertTrue(actProducts.contains(product), product +"doesn't contain in the drop down");
		
		Assert.assertTrue(actProducts.size()==productGrps.length, "Size of Expected & actual products don't match");
			
	}
	
	@Test
	public void test2() throws Exception
	{
			HashMap<String, String[]> prdGrpProdMap=new HashMap<String, String[]>();
			prdGrpProdMap.put("Electricity",new String[]{"Half Hourly Electricity","Non Half Hourly Electricity"});
			prdGrpProdMap.put("Gas",new String[]{"Gas Supply"});
			prdGrpProdMap.put("Oil",new String[]{"Diesel","Gas Oil","Heavy Fuel Oil","Kerosene","Medium Fuel Oil","Unleaded"});
			for(String prdGrp:prdGrpProdMap.keySet())
			{
				selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), prdGrp);
				waitForPageLoaded(driver);
				ArrayList<String> actProducts=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlProduct"));
				for(String actPrd:actProducts)
					assertTrue("Product "+actPrd+"is shown in products drop down even though not part of prdgroup", Arrays.asList(prdGrpProdMap.get(prdGrp)).contains(actPrd));
			}
	}
	
	@Test
	public void test3() throws Exception
	{
			HashMap<String, String[]> prdGrpDataTypeMap=new HashMap<String, String[]>();
			prdGrpDataTypeMap.put("Electricity",new String[]{"BTMonthlyRawData","Capacity Level","Estimated Annual Consumption","Volume Adjustment Figure"});
			prdGrpDataTypeMap.put("Gas",new String[]{"Actual","Tender Forecast"});
			prdGrpDataTypeMap.put("Oil",new String[]{"Average Load Size (Litres)","Estimated Annual Consumption"});
			for(String prdGrp:prdGrpDataTypeMap.keySet())
			{
				selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), prdGrp);
				waitForPageLoaded(driver);
				ArrayList<String> actDataTypes=getOptionsDropdown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"));
				for(String actDataType:actDataTypes)
					assertTrue("Product "+actDataType+"is shown in products drop down even though not part of prdgroup"+prdGrp, Arrays.asList(prdGrpDataTypeMap.get(prdGrp)).contains(actDataType));
			}
	}
	
	@Test
	public void test4() throws Exception
	{
			HashMap<String, String[]> prdGrpUnitBaseMap=new HashMap<String, String[]>();
			prdGrpUnitBaseMap.put("Electricity",new String[]{"Factor","kVA","kVArh","kW","kWh","MW","MWh","Percentage","YearMonth"});
			prdGrpUnitBaseMap.put("Gas",new String[]{"kW","kWh","M3","MWh","Percentage","therm"});
			prdGrpUnitBaseMap.put("Oil",new String[]{"barrel","litre","Percentage","tonne"});
			for(String prdGrp:prdGrpUnitBaseMap.keySet())
			{
				selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), prdGrp);
				waitForPageLoaded(driver);
				ArrayList<String> actDataTypes=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlUnitBasis"));
				for(String actUnitType:actDataTypes)
					assertTrue("unitType "+actUnitType+"is shown in products drop down even though not part of prdgroup"+prdGrp, Arrays.asList(prdGrpUnitBaseMap.get(prdGrp)).contains(actUnitType));
			}
	}
	@Test
	public void test5() throws Exception
	{
		fillDropDownsForGas();
		//selecting site
		safeClick(driver, By.id("ctl00_cphMainContent_RdoDataLoadLevel_1"));
		safeClick(driver, By.xpath("//img[contains(@alt,'043 - ICI')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'043DDC - Dulux Decorator Centres')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'003 - Edinburgh (Gas Supply)')]"));
		assertFalse("Delivery Point is allowed to select even though site is selected",elementPresent(driver, By.xpath("//span[text()='8820875008']/preceding-sibling::input"), 2));
	}

	@Test
	public void test6() throws Exception
	{
		fillDropDownsForGas();
		//selecting site
		safeClick(driver, By.id("ctl00_cphMainContent_RdoDataLoadLevel_0"));
		safeClick(driver, By.xpath("//img[contains(@alt,'Expand 017 - Greene King Brewing & Retailing Ltd')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'GK - Greene King')]"));
		safeClick(driver, By.xpath("//img[contains(@alt,'0115 - PE11 1BE (Gas Supply)')]"));
		assertTrue("Delivery Point is not allowed to select even though DP is selected",elementPresent(driver, By.xpath("//span[text()='1234567890456']/preceding-sibling::input"), 3));
	}

	@Test
	public void test7() throws Exception
	{
		goToSummaryData();
		fillDropDownsVolumeSummaryData();

		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		safeClick(driver, By.xpath("//span[text()='043 - ICI']/preceding-sibling::input[@type='checkbox']"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		
		List<WebElement> txtVolumeElements=driver.findElements(By.xpath("//input[@type='text']"));
		for(WebElement e: txtVolumeElements)
		{
			e.clear();
			e.sendKeys("10");
			
		}
			
		for(WebElement e: txtVolumeElements)
		{
			assertTrue("Not able to edit values in text boxes",e.getAttribute("value").equals("10"));
			e.clear();
		}
		
	}
	@Test
	public void test8() throws Exception
	{
		goToSummaryData();
		fillDropDownsVolumeSummaryData();

		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		safeClick(driver, By.xpath("//span[text()='043 - ICI']/preceding-sibling::input[@type='checkbox']"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		
		List<WebElement> txtVolumeElements=driver.findElements(By.xpath("//input[@type='text']"));
		for(WebElement e: txtVolumeElements)
		{
			e.clear();
			e.sendKeys("10");
			
		}
			
		safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
		
		assertTrue("Successful save mesg not present", textPresent(driver,"Volume summary data was updated successfully",4));
		goToSummaryData();
		
		fillDropDownsVolumeSummaryData();
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		safeClick(driver, By.xpath("//span[text()='043 - ICI']/preceding-sibling::input[@type='checkbox']"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		txtVolumeElements=driver.findElements(By.xpath("//input[@type='text']"));
		
		for(WebElement e: txtVolumeElements)
		{
			assertTrue("Data not saved in db correctly",e.getAttribute("value").equals("10.0"));
			e.clear();
		}
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
	}
	

	public void test9() throws Exception
	{
		goToSummaryData();
		fillDropDownsVolumeSummaryData();

		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		safeClick(driver, By.xpath("//span[text()='043 - ICI']/preceding-sibling::input[@type='checkbox']"));
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnNext"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));
		assertTrue("Didn't move back after clicking cancel button",elementPresent(driver,By.id("ctl00_cphMainContent_ddlUnitBasis"),5));
	}
	
	@Test
	public void test11() throws Exception
	{
		goToSummaryData();
		fillDropDownsVolumeSummaryData();
		safeClick(driver, By.linkText("Load from sheet"));
		
		driver.findElement(By.xpath("//input[@id='ctl00_cphMainContent_DPTreeControl1_FileUpload1' and @type ='file']")).sendKeys(new java.io.File( "." ).getCanonicalPath()+"\\resources\\metricsdata\\Metrics_Data.xls");
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtRow"), "1");
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtColumn"), "1");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnOKPopup"));
		assertTrue("Wrong excel format erro not thrown",textPresent(driver, "Failed to read spreadsheet:", 3));
	}
	
	@Test
	public void test13() throws Exception
	{
		goToSummaryData();
		fillDropDownsVolumeSummaryData();
		safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Reset button didn't reset the values",new Select(driver.findElement(By.xpath("//select[contains(@id,'ddlProductGroup')]"))).getFirstSelectedOption().getText().contains("Select Product Group"));
	}
	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

}
