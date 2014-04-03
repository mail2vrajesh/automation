package ethos.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class VolumeIntervalDataRemoval extends ETHOSDomainWraper{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void test1() throws Exception
	{
		goToVolumeDataRemoval();
	}

	
	@Test
	public void findDeliveryPoint() throws Exception
	{
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Find didn't work",textPresent(driver, "1620000714190", 2));
		//1620000714190
	}
	
	@Test
	public void chkResetBtn() throws Exception
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
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_chkShowInactive"));
		waitForPageLoaded(driver);
		
		// 1620000714190 shd be shown as it is inactive
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), "1620000714190");
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
		assertTrue("Inactive didn'twork",textPresent(driver, "1620000714190", 2));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnReset"));
	}
	
	@Test
	public void tickAllUntickAll() throws Exception
	{
		
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnTickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked").equals("true"));
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnUntickAll"));
		waitForPageLoaded(driver);
		assertTrue(driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).getAttribute("checked")==null);
	}
	
	@Test
	public void intervalDataRemoval() throws Exception
	{

		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).click();
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlType"), "Consumption");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnRemove"));
		assertTrue("Confirmation for delete didn't appear",elementVisible(driver, By.id("ctl00_cphMainContent_DeleteControl1"), 5));
		assertTrue("Dleete confirm message didnt appear",safeGetText(driver, By.id("ctl00_cphMainContent_DeleteControl1")).contains("You are about to PERMANENTLY remove interval data"));
		assertTrue("Delete confirm msg didnt appear",safeGetText(driver, By.id("ctl00_cphMainContent_DeleteControl1")).contains("To confirm the deletion, type 'DELETE' here:"));
		
		safeType(driver, By.id("ctl00_cphMainContent_DeleteControl1_txtConfirm"), "DELETE");
		safeClick(driver, By.id("ctl00_cphMainContent_DeleteControl1_btnConfirm"));
		assertTrue(textPresent(driver, "records were removed", 8));
	
	}
	
	@Test
	public void intervalDataRemovalCancel() throws Exception
	{

		safeType(driver, By.id("ctl00_cphMainContent_ymFrom"), "Jan-14");
		safeType(driver, By.id("ctl00_cphMainContent_ymTo"), "Feb-14");
		
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_DPTreeView1n0CheckBox")).click();
		
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlTimeBasis"), "Base Time (e.g. GMT, UTC)");
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlType"), "Consumption");
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnRemove"));
		assertTrue("Confirmation for delete didn't appear",elementVisible(driver, By.id("ctl00_cphMainContent_DeleteControl1"), 5));
		
		safeClick(driver, By.id("ctl00_cphMainContent_DeleteControl1_btnCancel"));
		assertFalse("Confirmation not closed after cancel Btn pressed",elementVisible(driver, By.id("ctl00_cphMainContent_DeleteControl1"), 3));
		
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
