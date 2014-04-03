package ethos.test;
	
	import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class ImportLevelAdjustments extends ETHOSDomainWraper {

	public RemoteWebDriver driver = null;
	
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
	    ethosLogin(driver,"madhva","madhva");
	    waitTitle(driver, "ETHOS Main Page", 10);
	    safeClick(driver, By.xpath("//div[2]/ul/li[7]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/ul/li[2]/div/b/a"));
	    
	    safeClick(driver, By.xpath("//div[2]/ul/li[2]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/ul/li[1]/div/b/a"));
	    
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/ul/li[1]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[3]/td/input[1]"));
	   
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[3]/td/input[1]"));//view
	    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));
	    
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));
	    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[3]/div/div/span/div/img"));
	    
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div/img"));
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[5]"));//product group
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select button of electricity
	    
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));
		safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));//down arrow-
		
		safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div[4]/div/a[8]"));//import level adjustments 
	}
	

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
	
	
	@Test//passed
	public void clientMaintenance_VerifyProductGroupList() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    /*String Valueofproduct= new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"))).toString();*/
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[3]/div/div[2]/span/div[2]/div/span"), 10));//assert >product group
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[3]/div/div[1]/span/div[2]/div/span"), 10));//assert client
	}
	
	/*@Test//Not passed
	public void clientMaintenance_VerifyProductGroupListForProduct() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    
	    String Valueofproduct= new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"))).toString();
	    assertTrue(elementVisible(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[4]/td[2]/selectz" ), 10));//assert 
	    
	}*/
	
	
	@Test//passed
	public void clientMaintenance_VerifyUpdateLevelAsGroup() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td/input"));//group radio button
	    waitTitle(driver, " ETHOS ", 10);
	    /*String Valueofproduct= new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"))).toString();*/
	    assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input" ), 10));//assert 
	    
	}
	
	
	@Test//passed
	public void clientMaintenance_VerifyUpdateLevelAsCompany() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[2]/td/input"));//group radio button
	    waitTitle(driver, " ETHOS ", 10);
	    /*String Valueofproduct= new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"))).toString();*/
	    assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input" ), 10));//assert 
	    
	}
	@Test// passed
	public void clientMaintenance_VerifyUpdateLevelAsSite() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/input"));//group radio button
	    waitTitle(driver, " ETHOS ", 10);
	   assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input" ), 10));//assert 
	    
	}
	@Test//passed
	public void clientMaintenance_VerifyUpdateLevelAsDeliveryPoint() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[4]/td/input"));//group radio button
	    waitTitle(driver, " ETHOS ", 10);
	   assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input" ), 10));//assert 
    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyDeliveryPointAndLevelAdjustment() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[4]/td/input"));//group radio button
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select leveladjustnment = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[4]/td[2]/select")));
	    leveladjustnment.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	   assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input" ), 10));//assert reset
	    
	}
	
	/*@Test//
	public void clientMaintenance_VerifyResetButton() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product1.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[4]/td/input"));//delivery point
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[6]/td[2]/span/img"));//default date to 
	    safeClick(driver, By.xpath("//div[2]/table/tbody/tr[7]/td[5]/a"));//select default date to
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[7]/td/input"));//
	    waitTitle(driver, " ETHOS ", 10);
	    String active="";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[6]/td[2]/span/input")));    
	}*/
	
	@Test//passed
	public void clientMaintenance_VerifyImportFileErrorMessage() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
		org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
		level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10); 
	    driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table[2]/tbody/tr/td[2]/div/input[1]")).sendKeys("Bharath");
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table[2]/tbody/tr/td[2]/div/input[3]"));//
	    waitTitle(driver, " ETHOS ", 10);
	    assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table[2]/tbody/tr/td[2]/div/span" ), 10));//assert erroe message    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyRemoveButton() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
		org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
		level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10); 
	    org.openqa.selenium.support.ui.Select leveladjust = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[4]/td[2]/select")));
	    leveladjust.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/table[2]/tbody/tr[5]/td[2]/input"));//remove buttton
	    waitTitle(driver, " ETHOS ", 10);    
	    assertTrue(elementPresent(driver,By.xpath("//div[3]/div[4]/div[2]/table[2]/tbody/tr[5]/td[2]/input" ), 10));//assert error message    
	}

	
}


