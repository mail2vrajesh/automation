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

public class ProductGroup extends ETHOSDomainWraper {

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
	}
	

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
	
	//Product Group settings
	
	@Test//passed
	public void clientMaintenance_AddProductgroup() throws Exception  {
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


	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/input"));//add new
	    
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
	    productgroup.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/select")));
		productype1.selectByIndex(3);  
		waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype2 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[3]/td[2]/select")));
		productype2.selectByIndex(6);
		waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype3 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[4]/td[2]/select")));
		productype3.selectByIndex(5);
		waitTitle(driver, " ETHOS ", 10);
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/div[3]/div/a"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/div[3]/div/a"));
	    String active="Oil";
	    assertEquals(active,safeGetText(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td[2]")));
	}
	
	@Test//not passed
	public void clientMaintenance_EditProductgroup() throws Exception  {
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


	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/input"));//add new
	    
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
	    productgroup.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[2]/td[2]/select")));
		productype1.selectByIndex(3);
		 waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype2 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[3]/td[2]/select")));
		productype2.selectByIndex(6);
		 waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype3 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[4]/td[2]/select")));
		productype3.selectByIndex(5);
		 waitTitle(driver, " ETHOS ", 10);
		
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/input"));//edit button
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div/span"), 10));
	}
	
	@Test//passed editing cant be done for the other values
	public void clientMaintenance_DeleteProductgroup() throws Exception  {
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

	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td[4]/a"));
	    PopUpWindowHandle(driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td[4]/a"));
	    
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td[4]/a"));//Delete
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));	    
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"), 10));
	}
	
	@Test//not passed
	public void clientMaintenance_ExportProductgroupList() throws Exception  {
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
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td/table/tbody/tr/td[5]/a"));
	    try{
		safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td/table/tbody/tr/td[5]/a"));
	    acceptAlert(driver,"Are you sure you want to delete");
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//passed
	public void clientMaintenance_BackToListinProductgroup() throws Exception  {
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


	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/input"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/input"));//add new
	    
	    org.openqa.selenium.support.ui.Select productgroup = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[1]/td[2]/select")));
	    productgroup.selectByIndex(3);
	    waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype1 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("//*[@id='ctl00_cphMainContent_ddlIndustryType1']")));
		productype1.selectByIndex(3);
		 waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype2 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[3]/td[2]/select")));
		productype2.selectByIndex(6);
		 waitTitle(driver, " ETHOS ", 10);
		org.openqa.selenium.support.ui.Select productype3 = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath("//div[2]/div[5]/div[2]/div/div/table/tbody/tr[4]/td[2]/select")));
		productype3.selectByIndex(5);
		 waitTitle(driver, " ETHOS ", 10);
		
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    safeClick(driver, By.xpath("//div[2]/div[5]/div[2]/div/div[3]/div/input[1]"));
	    
	    assertTrue(elementPresent(driver, By.xpath("//div[2]/div[4]/div/span"), 10));
	}
}


