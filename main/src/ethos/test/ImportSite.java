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

public class ImportSite extends ETHOSDomainWraper {

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
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[5]"));//product group


	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));
	    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select button of electricity
	    
		waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));
		safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));//down arrow-
	    safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div[4]/div/a[7]"));//import site data-
	}
	

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
	
	//Product Group settings
	
	@Test
	public void clientMaintenance_ExportTemplateInProductGroup() throws Exception  {
		
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"));
	    assertTrue(elementPresent(driver, By.xpath("//div[3]/div[4]/div[2]/div/div/input[4]"), 10));//export template
	}
	
	@Test//passed
	public void clientMaintenance_DownloadExportTemplateInProductGroup() throws Exception  {
	    
	    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/select"));
	    
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


