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

public class VolumeIntervalDataCheck extends CommonUtils{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToVolumeDataIntervalCheck();
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
	public void intervalDataSummaryReportForIntervalData() throws Exception
	{
		selectElectricityProducts();
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Mar-14");
		
		safeCheck(By.id("ctl00_cphMainContent_chkSummary"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnGenerate"));
		for(int i=0;i<18;i++)
		{
			driver.navigate().refresh();
			if(textPresent(driver, "Complete", 2))
				break;
		}
		
		safeClick(driver, By.id("ctl00_cphMainContent_TaskStatusControl1_btnCancel"));
		//Clicking summary Report here is not working.. once it works we need to add that
		safeClick(driver, By.id("ctl00_cphMainContent_btnFinish"));
	}
	
	@Test
	public void intervalDataSummaryReportForInvoices() throws Exception
	{
		selectElectricityProducts();
		
			safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
	
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Mar-14");
		
		safeCheck(By.id("ctl00_cphMainContent_chkSummary"));
		safeClick(driver, By.id("ctl00_cphMainContent_rdoSummaryReport_1"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnGenerate"));
		for(int i=0;i<18;i++)
		{
			driver.navigate().refresh();
			if(textPresent(driver, "Complete", 2))
				break;
		}
		
		safeClick(driver, By.id("ctl00_cphMainContent_TaskStatusControl1_btnCancel"));
		//Clicking summary Report here is not working.. once it works we need to add that
		safeClick(driver, By.id("ctl00_cphMainContent_btnFinish"));
	}
	
	@Test
	public void intervalDataSummaryReportForBoth() throws Exception
	{
		selectElectricityProducts();
		
			safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Mar-14");
		
		safeCheck(By.id("ctl00_cphMainContent_chkSummary"));
		safeClick(driver, By.id("ctl00_cphMainContent_rdoSummaryReport_1"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnGenerate"));
		for(int i=0;i<18;i++)
		{
			driver.navigate().refresh();
			if(textPresent(driver, "Complete", 2))
				break;
		}
		
		safeClick(driver, By.id("ctl00_cphMainContent_TaskStatusControl1_btnCancel"));
		//Clicking summary Report here is not working.. once it works we need to add that
		safeClick(driver, By.id("ctl00_cphMainContent_btnFinish"));
	}
	
	@Test
	public void intervalDataSummaryReportForMissing() throws Exception
	{
		selectElectricityProducts();
		
		safeCheck(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox"));
	
	
		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-13");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Mar-14");
		
		safeCheck(By.id("ctl00_cphMainContent_chkSummary"));
		safeClick(driver, By.id("ctl00_cphMainContent_rdoSummaryReport_1"));
		
		//Missing data
		safeClick(driver, By.id("ctl00_cphMainContent_chkDetailed"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnGenerate"));
		for(int i=0;i<18;i++)
		{
			driver.navigate().refresh();
			if(textPresent(driver, "Complete", 2))
				break;
		}
		
		safeClick(driver, By.id("ctl00_cphMainContent_TaskStatusControl1_btnCancel"));
		//Clicking summary Report here is not working.. once it works we need to add that
		safeClick(driver, By.id("ctl00_cphMainContent_btnFinish"));
	}
}
