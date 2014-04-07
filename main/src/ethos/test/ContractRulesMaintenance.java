package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class ContractRulesMaintenance extends ETHOSDomainWraper{
	
	public String ethosUrl;
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ethosUrl=cachedProperties.value("Ethos_url");
		openUrl(ethosUrl);
		login( "madhva", "madhva");

	}
	@BeforeMethod
	public void init()
	{
		driver.get(ethosUrl+"/Clients/Contracts/Rules.aspx?ContractPK=3664");
	}
	

	@Test
	public void verifyLinkToTDSFlexPurchasing() throws Exception
	{
		driver.get(ethosUrl+"/Clients/Contracts/FlexPurchasing/TDSConfig.aspx?ContractPK=5537");
		safeClick(driver, By.id("ctl00_cphMainContent_btnUpdateFromTDS"));
		assertTrue("Success message not present",textPresent(driver, "Success", 8));
	
	}
	@Test
	public void verifyContractRules() throws Exception {
		
		assertTrue(safeGetText(driver, By.id("ctl00_lblTitle")).contains("Contract Rules"));
		assertTrue(textPresent(driver, "Here you can set up the rules for the contract", 5));
				
	}
	
	@Test
	public void saveRule() throws Exception
	{
		String str=Integer.toString(new Random().nextInt(99));
		safeType(driver, By.id("ctl00_cphMainContent_rptProduct_ctl00_rptContract_ctl06_rptCategory_ctl00_ctlValue_numValue"), str);
		safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
		assertTrue(safeGetText(driver, By.id("ctl00_lblTitle")).contains("View Contract"));
		mouseMoveTo(By.id("ctl00_cphParentContent_ContractHeader1_Repeater1_ctl00_btnOptions"));
		mouseMoveTo(By.linkText("Rules"));
		safeClick(driver, By.linkText("Rules"));
		
		assertTrue("Save didnt save values succsfully",driver.findElement(By.id("ctl00_cphMainContent_rptProduct_ctl00_rptContract_ctl06_rptCategory_ctl00_ctlValue_numValue")).getAttribute("value").equals(str));
	}


	@Test
	public void cancelRule() throws Exception
	{
		String str=Integer.toString(new Random().nextInt(99));
		safeType(driver, By.id("ctl00_cphMainContent_rptProduct_ctl00_rptContract_ctl06_rptCategory_ctl00_ctlValue_numValue"), str);
		safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));
		assertTrue("Didn't move back to vie contract page",safeGetText(driver, By.id("ctl00_lblTitle")).contains("View Contract"));
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
