package ethos.test;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;


public class verifyLinkToTDS extends ETHOSDomainWraper {
	
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@DataProvider
	public Object [ ][ ] users() {
		return new Object [ ] [ ] {{"madhva","madhva"} };
	}

	@Test(dataProvider="users")
	public void ethosSignin(String username, String password) throws Exception {
		getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
	    ethosLogin(driver,username,password);
	    
		if(username.equals("madhva") || username.equals("sachin")){
			waitTitle(driver, "ETHOS Main Page", 10);
			elementPresent(driver, By.id("ctl00_btnLogout"), 10); 
		}else{
			textPresent(driver, "Your login attempt was not successful. Invalid User Name", 10);
		}
	}
	
	@DataProvider
	public Object [ ][ ] AddRatesToOfferTemplateList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifyEditTDSFunctionality(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			String portfolio = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolio"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolio"),portfolio);
			String portfolioDetails = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"),portfolioDetails);
			//safeType(driver, By.id("__lblctl00_cphMainContent_txtSupplyPeriod"),"01-04-2014 to 31-03-2017");
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifySelectDeselectFlexibleData(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			
			String portfolio = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolio"));
			String portfolioDetails = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			
			safeUnCheck(driver, By.id("ctl00_cphMainContent_chkUseTDS"));
			Thread.sleep(3000);
			
			int noOfPortfolioEntries;
			WebElement table = driver.findElement(By.id("ctl00_cphMainContent_ddlPortfolio"));
			List<WebElement> rows = table.findElements(By.tagName("option"));
			noOfPortfolioEntries = rows.size();
			
			int noOfPortfolioDescEntries;
			WebElement tableDetails = driver.findElement(By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			List<WebElement> rowsDetails = tableDetails.findElements(By.tagName("option"));
			noOfPortfolioDescEntries = rowsDetails.size();
			
			assertEquals(noOfPortfolioEntries,0);
			assertEquals(noOfPortfolioDescEntries,0);
			
			safeCheck(driver, By.id("ctl00_cphMainContent_chkUseTDS"));
			Thread.sleep(3000);
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolio"),portfolio);
			Thread.sleep(3000);
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"),portfolioDetails);
			Thread.sleep(3000);
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifyPurchasingPeriodsForTDS(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			String portfolio = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolio"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolio"),portfolio);
			Thread.sleep(3000);
			
			int noOfPortfolioDescEntries;
			WebElement tableDetails = driver.findElement(By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			List<WebElement> rowsDetails = tableDetails.findElements(By.tagName("option"));
			noOfPortfolioDescEntries = rowsDetails.size();
			
			assertTrue(noOfPortfolioDescEntries > 0);
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifySupplyPeriodsTDS(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			String portfolio = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolio"));
			String portfolioDetails = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolio"),portfolio);
			Thread.sleep(3000);
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"),portfolioDetails);
			Thread.sleep(3000);
			String supplyPeriod = safeGetText(driver, By.id("__lblctl00_cphMainContent_txtSupplyPeriod"));
			
			assertTrue(supplyPeriod.length() > 0);
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifySelectResidualTDS(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			safeUnCheck(driver, By.id("ctl00_cphMainContent_chkIgnoreResidual"));
			safeCheck(driver, By.id("ctl00_cphMainContent_chkIgnoreResidual"));
			safeClick(driver,By.id("ctl00_cphMainContent_btnCancel"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddRatesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifySaveTDSFunctionality(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToFlexPurchase(driver,"Link to TDS");
			safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
			String portfolio = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolio"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolio"),portfolio);
			String portfolioDetails = safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlPortfolioDetails"),portfolioDetails);
			safeClick(driver,By.id("ctl00_cphMainContent_btnSave"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 		Flex Purchasing.
	 * safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvFlexPurchase>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			
	 * 		selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlRecordType"));
			safeType(driver, By.id("ctl00_cphMainContent_txtTransactionDate"),"01-Apr-2014");
			safeClick(driver, By.id("ctl00_cphMainContent_lnkPurchasePeriod"));
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlPurchaseBlock"));
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlPurchasePeriod"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnOKPopup"));
			safeType(driver, By.id("__lblctl00_cphMainContent_txtFromDate"),"01-Apr-2014");
			safeType(driver, By.id("__lblctl00_cphMainContent_txtToDate"),"01-Apr-2015");
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlVolumeEntryFormat"));
			safeType(driver, By.id("ctl00_cphMainContent_txtVolumeEntered"),"10000");
			safeType(driver, By.id("ctl00_cphMainContent_txtVolume"),"75000");
			safeType(driver, By.id("ctl00_cphMainContent_txtUnitPrice"),"5.75");
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlClientConfirmationCode"));
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlSupplierConfirmationCode"));
			selectFirstValueFromDropdown(driver, By.id("ctl00_cphMainContent_ddlInternalSignoffBy"));
	 */
	
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
