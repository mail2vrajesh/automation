package ethos.test;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class AddRulesToOfferTemplate extends ETHOSDomainWraper {
	
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
	public Object [ ][ ] AddRulesToOfferTemplateList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "AddRulesToOfferTemplateList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddRulesToOfferTemplate(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToScreen(driver,"Supplier","Supplier Offer Templates");
			
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"),"Electricity");
			Thread.sleep(5000);
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"),"Half Hourly Electricity");
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvTemplate>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphParentContent_SupplierOfferTemplateHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.linkText("Rules"));
			
			safeType(driver, By.id("ctl00_cphMainContent_rptOffer_ctl00_rptCategory_ctl04_ctlValue_txtValue"),"AUTO");
			
			safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
			/*Thread.sleep(5000);
			String warningMessage = safeGetText(driver, By.id("ctl00_cphMainContent_CustVal_Validate"));
			if (warningMessage.contains("Duplicate rates found."))
				safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
