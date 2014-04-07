package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class LinkedContracts extends ETHOSDomainWraper{
	
	public String ethosUrl;
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ethosUrl=cachedProperties.value("Ethos_url");
		openUrl(ethosUrl);
		login( "madhva", "madhva");
		driver.get(ethosUrl+"/Clients/Contracts/LinkedContract.aspx?ContractPK=5537");
	}
	

	@Test
	public void verifyLinkedContracts() throws Exception {
		
		assertTrue(safeGetText(driver, By.id("ctl00_lblTitle")).contains("Contracts Linked to Contract"));
		
	}
	@Test
	public void verifyDropDownList() throws Exception {

		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlContract"), 4));
		ArrayList<String> contractList=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlContract"));
		assertTrue("contractList doesn't exist",contractList.size()>=0);
	}
	@Test
	public void verifyAddContract() throws Exception {

		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlContract"), 4));
		ArrayList<String> contractList=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlContract"));
		int nLnksBefore=driver.findElementsByLinkText("Remove").size();
		if(contractList.size()>0)
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlContract"));
		
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		int nLnksAfter=driver.findElementsByLinkText("Remove").size();
		assertTrue(nLnksAfter==nLnksBefore+1);
	}	

	@Test
	public void verifyRemoveContract() throws Exception {

		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent_ddlContract"), 4));
		ArrayList<String> contractList=getOptionsDropdown(By.id("ctl00_cphMainContent_ddlContract"));
		int nLnksBefore=driver.findElementsByLinkText("Remove").size();

		safeClick(driver, By.linkText("Remove"));
		acceptAlert(driver, "Are you sure");
		int nLnksAfter=driver.findElementsByLinkText("Remove").size();
		assertTrue(nLnksAfter==nLnksBefore-1);
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
