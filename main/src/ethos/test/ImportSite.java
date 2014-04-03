package ethos.test;
	
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class ImportSite extends ETHOSDomainWraper {

	public RemoteWebDriver driver = null;
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
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
	public Object [ ][ ] Supplier() {
		return new Object [ ] [ ] {{ "System"}};
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
	
	//Product Group settings
	//passed
	@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ExportTemplateInProductGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
			 navigateToImportSiteData(driver);
			 waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));
			 assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//export template
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Test
	public void clientMaintenance_DownloadExportTemplateInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));	    
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product
	    safeClick(driver, By.id("ctl00_cphMainContent_btnExport"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test(dataProvider = "Supplier", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyExportTemplateImportSite(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToSuppliersProperdata(driver,"Suppliers");
			waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
			waitTitle(driver, "ETHOS'",10);
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("Select"));
			safeClick(driver, By.linkText("Select"));
			waitTitle(driver, "ETHOS'",10);
			safeClick(driver, By.linkText("Back to list"));
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnAddNew"));
			assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent_btnAddNew"), 10));
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	
	
	/*@Test//not passed
	public void clientMaintenance_VerifyImportAddreessDetailsInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));

		safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[1]/td[2]/input"));//check box
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);
	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product

	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));//export template
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/a"));//download export link
	    clickLink(driver, "Click to download the exported template");
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//pop up handle assert
	}*/
	
	/*@Test//not passed
	public void clientMaintenance_VerifyImportAddreessDetailsNotSelectedInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));

	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/input"));//check box
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);
	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product

	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));//export template
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/a"));//download export link
	    clickLink(driver, "Click to download the exported template");
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//pop up handle assert
	}*/
	
	/*@Test//not passed
	public void clientMaintenance_NewSitewithoutImportaddressInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));

	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/input"));//check box
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);
	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product

	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));//export template
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/a"));//download export link
	    clickLink(driver, "Click to download the exported template");
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//pop up handle assert
	}*/
	
	/*@Test //not passed
	public void clientMaintenance_NewSitewithImportaddressInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));

	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/input"));//check box
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);
	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product

	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));//export template
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/a"));//download export link
	    clickLink(driver, "Click to download the exported template");
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//pop up handle assert
	}
	
	@Test // not passed
	public void clientMaintenance_OverwritingExistingSiteProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));

	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[2]/input"));//check box
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select")));
	    productgroup.selectByIndex(1);//product group drop down -
	    waitTitle(driver, " ETHOS ", 10);
	    
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/select")));
	    product.selectByIndex(1);//product

	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));//export template
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/a"));//download export link
	    clickLink(driver, "Click to download the exported template");
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//pop up handle assert
	}*/
	
}


