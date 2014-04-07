	package ethos.test;
	
	import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class ClientMaintenance extends ETHOSDomainWraper {

	public RemoteWebDriver driver = null;
	Random rn = new Random();
	int minimum=1;
	int maximum=4;
	int n = maximum - minimum + 1;
	int i = rn.nextInt() % n;
	int randomNum = minimum + i;
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	@Test//Passed
	public void clientMaintenance_AddDeliveryPoint() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"));//add new
	    
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select"));
	    
	    org.openqa.selenium.support.ui.Select selectcompany = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select")));
	    selectcompany.selectByIndex(2);
		waitTitle(driver, " ETHOS ", 10);
		
		//safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select"));
	    org.openqa.selenium.support.ui.Select selectproduct = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select")));
	    selectproduct.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
	    
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select selectsite = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
		selectsite.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select selectsupply = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
		selectsupply.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
	
		driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/input")).sendKeys("1234");//giving random digit number
		
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));// save button
		
		waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/a"));
		assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/a"), 10));
	}
	
	@Test//passed
	public void clientMaintenance_AddDeliveryPointForGas() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"));//add new
	    
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select"));
	    
	    org.openqa.selenium.support.ui.Select selectcompany = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select")));
	    selectcompany.selectByIndex(2);
		waitTitle(driver, " ETHOS ", 10);
		
		//safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select"));
	    org.openqa.selenium.support.ui.Select selectproduct = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select")));
	    selectproduct.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
	    
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select selectsite = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
		selectsite.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select selectsupply = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
		selectsupply.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
	
		driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/input")).sendKeys("1235");//giving random digit number
		
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));// save button
		waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/a"));
		assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/table/tbody/tr[2]/td[2]/span[1]"), 10));
	}
	
	@Test//passed
	public void clientMaintenance_ModifyDeliveryPoint() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"));//add new
	    
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select"));
	    
	    org.openqa.selenium.support.ui.Select selectcompany = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select")));
	    selectcompany.selectByIndex(2);
		waitTitle(driver, " ETHOS ", 10);
		
		//safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select"));
	    org.openqa.selenium.support.ui.Select selectproduct = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select")));
	    selectproduct.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
	    
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select selectsite = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
		selectsite.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select selectsupply = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
		selectsupply.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
	
		driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/input")).sendKeys("1236");//giving random digit number
		
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));// save button
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/input[1]"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/input[1]"));//edit button
		

		driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/input")).sendKeys("1237");//editing mprn number
	 
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[4]/div/input[1]")); // save button in view delivery point page
		

		assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/div/div/table/tbody/tr[2]/td[2]/span[1]"), 10));
		
		/*int value=123456789045;
	    assertEquals(IntegerConverter(safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/div/table/tbody/tr[2]/td[2]/span"))), value );*/
	}
	
	@Test//passed
	public void clientMaintenance_DeleteDeliveryPoint() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"));//add new
	    
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select"));
	    
	    org.openqa.selenium.support.ui.Select selectcompany = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[1]/td[2]/select")));
	    selectcompany.selectByIndex(2);
		waitTitle(driver, " ETHOS ", 10);
		
		//safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select"));
	    org.openqa.selenium.support.ui.Select selectproduct = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[4]/select")));
	    selectproduct.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
	    
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select selectsite = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
		selectsite.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
		
		org.openqa.selenium.support.ui.Select selectsupply = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
		selectsupply.selectByIndex(1);
		waitTitle(driver, " ETHOS ", 10);
		
		driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/input")).sendKeys("12348");//giving random digit number
		
		safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));// save button
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/input[2]"));
	 
		safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/input[2]")); // delete button in view delivery point page	
		driver.findElement(By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/div/input[1]")).sendKeys("DELETE");
		safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/div/div/input[2]")); // confirm delete
		
		assertTrue(elementPresent(driver, By.xpath("/div[2]/div[4]/div[2]/div/input"), 10));
		
	}
	
	
	@Test// pop issue problem
	public void clientMaintenance_ExportDeliveryPointList() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/table/tbody/tr[12]/td/table/tbody/tr/td[16]/a"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/table/tbody/tr[12]/td/table/tbody/tr/td[16]/a"));//click export
	    		
		assertTrue(PopupMessageBox(driver));//Assert pop window
		
	}
	
	private boolean PopupMessageBox(RemoteWebDriver driver2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Test//passed
	public void clientMaintenance_ActivateViewDeliveryPointPage() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points
	  
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div[4]/table/tbody/tr[2]/td/a"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[4]/table/tbody/tr[2]/td/a"));//click select
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td[6]/input[2]"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td[6]/input[2]"));//Activate button
	    assertTrue(elementVisible(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td[6]/input[2]"), 10));
		
	}
	
	@Test//passed
	public void clientMaintenance_ActivateAddDeliveryPointPage() throws Exception  {
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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[4]"));//delivery points


	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/input"));//add new
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[6]/input[2]"));
	    safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[6]/input[2]"));//Activate button
	    
	    assertTrue(elementVisible(driver, By.xpath("//div[2]/div[5]/div[2]/div/table/tbody/tr/td[6]/input[2]"), 10));
	}

}


