package ethos.test;
	
	import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.crypto.SealedObject;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.beust.jcommander.converters.IntegerConverter;
import com.domain.ETHOSDomainWraper;

public class LevelsAndAdjustments extends ETHOSDomainWraper {

	public RemoteWebDriver driver = null;
	
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		File file = new File("exe\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
	    ethosLogin(driver,"madhva","madhva");
	    waitTitle(driver, "ETHOS Main Page", 10);
	    safeClick(driver, By.xpath("//div[2]/ul/li[7]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/ul/li[2]/div/b/a"));
	    
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/ul/li[2]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/ul/li[1]/div/b/a"));
	    
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/ul/li[1]/div/b/a"));
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[3]/td/input[1]"));
	   
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[3]/td/input[1]"));//view
	    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));
	    
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));
	    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[3]/div/div/span/div/img"));
	    
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div/img"));
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[8]"));//levels and adjustments
	}
	

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
	@Test//passed
	public void clientMaintenance_VerifyProductGroupListInLevlesAdjustments() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    /*String Valueofproduct= new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"))).toString();*/
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[3]/div/div/span/div[2]/div/span"), 10));//assert client
	    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyListLevelAndAdjustments() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    String active="There are no items to display";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td/b")));
	    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyAllRecords() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/input"));//all records
	    String active="There are no items to display";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td/b")));
	    
	}
	
	
	@Test//passed
	public void clientMaintenance_VerifyLatestRecords() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/input"));//latest records
	    String active="There are no items to display";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr/td/b")));
	    
	}
	
	
	@Test//passed
	public void clientMaintenance_VerifyResetLevels() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    level.selectByIndex(1);
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[4]/td/input"));//resett
	    String active="(Select Product)";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    
	}
	@Test// passed
	public void clientMaintenance_VerifyAddLevels() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select")));
	    product.selectByIndex(1);//product
	    waitTitle(driver, " ETHOS ", 10);
	    org.openqa.selenium.support.ui.Select level = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]/select")));
	    level.selectByIndex(1);// level adjustments
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/input"));//Add new
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[4]/td[2]/span/img"));//Date
	    safeClick(driver, By.xpath("//div[2]/table/tbody/tr[5]/td[4]/a")); //Date selected
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[5]/td[2]/span/img")); //Date
	    safeClick(driver, By.xpath("//div[2]/table/tbody/tr[5]/td[5]/a")); //Date selected
	    driver.findElement(By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[6]/td[2]/input")).sendKeys("12");  
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[1]")); 
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"));
	   
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"), 10));//back to list
	}
	@Test//passed
	public void clientMaintenance_VerifyModifyLevels() throws Exception  {
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select client 
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));//click edit button
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
	    product.selectByIndex(2);//product
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[1]"));//click save
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"));		   
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"), 10));//back to list
	    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyDeleteLevels() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select client 
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));//click edit button
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
	    product.selectByIndex(2);//product
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[1]"));//click save
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[2]"));		
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[2]"));//click delete
	    acceptAlert(driver,"Are you sure you want to delete this item");
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));	
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/input"), 10));//assert add new
	    
	}
	
	@Test//passed
	public void clientMaintenance_VerifyReplaceLevels() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select client 
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/input[1]"));//click edit button
	    org.openqa.selenium.support.ui.Select product = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[2]/td[2]/select")));
	    product.selectByIndex(2);//product
	    waitTitle(driver, " ETHOS ", 10);
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[1]"));//click save
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div[2]/input"));		
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div[2]/input"));//click replace
	    driver.findElement(By.xpath("//div[3]/div[5]/div[2]/div/table/tbody/tr[6]/td[2]/input")).sendKeys("0");// add extra 0 to value
	    safeClick(driver, By.xpath("//div[3]/div[5]/div[2]/div/div/div/input[1]"));//click save	
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"));		   
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"), 10));//back to list
	    
	}
	@Test//passed
	public void clientMaintenance_VerifyBackList() throws Exception  {
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]/select"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select client	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"));		 
	    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/div/a"));//click back list
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));	
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div[2]/input"), 10));//add new assert
	    
	}
	/*@Test// Not passed
	public void clientMaintenance_VerifyExportList() throws Exception  {
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[5]/td/table/tbody/tr/td[5]/a"));
		safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[5]/td/table/tbody/tr/td[5]/a"));//select export	
		assertTrue(PopupMessageBox(driver));
		
	}*/
	
	
}


