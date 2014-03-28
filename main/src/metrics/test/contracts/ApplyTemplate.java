package metrics.test.contracts;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class ApplyTemplate extends MetricsDomainWraper{
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
	
	File file = new File("exe\\IEDriverServer.exe");
	DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
	driver = new InternetExplorerDriver(capability);
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	getApp(driver,cachedProperties.value("Metrics_url"),"Login");
	metricsLogin(driver,"Metrics1","Metrics1");
	}
  
	// Attach Template to Group	
	@DataProvider
	public Object [ ][ ] TemplateName() throws Exception {
	return new Object [ ] [ ] {{"Client","CCI International Ltd"}
			   };
		   }
@Test(dataProvider="TemplateName")
public void  AttachTemplate(String clientname,String templateName) throws Exception{
	gotoSubMenu(driver,"//a[text()='Contracts']","//a[text()='Apply Template']","Allocate Template");
    safeClick(driver, By.xpath("//div[@id='ctl08_ctl05_gridGroups_divSearchText']"));
    String actual=driver.getTitle();
    Assert.assertEquals(actual,"Allocate Template");		
    safeClick(driver, By.xpath("//a[text()='"+clientname+"']"));
    WebElement table=driver.findElement(By.xpath("//ul [@id='ctl08_ctl05_gridGroups_grid']"));
    List<WebElement> rows=driver.findElements(By.tagName("li"));
    for(WebElement row:rows){
    	String str=row.getText();
    	System.out.println(str);
      }
    Thread.sleep(2000);
    findByList(driver,templateName,"//ul [@id='ctl08_ctl05_gridGroups_grid']","//li/div[3]/span");
       
}
@DataProvider
public Object [ ][ ] AllocateTemplateName() throws Exception {
return new Object [ ] [ ] {{"Ref_UK_E_Supplier Charges","Ref_UK_NHH_DN+SC"}
		   };
	   }
@Test(dataProvider="AllocateTemplateName",dependsOnMethods={"AttachTemplate"})
public void allocateTemplate(String allocateTemp,String AllocateTemp1) throws InterruptedException{
	String actual=driver.findElement(By.xpath("//div[2]/ul/li[2]")).getText();
	System.out.println(actual);
	String actual1=driver.findElement(By.xpath("//div[3]/div[2]/div/ul/li[2]")).getText();
	System.out.println(actual1);
	Assert.assertEquals(actual,"Templates");
	Assert.assertEquals(actual1,"Attached");
	//which temp you want to allocate
	System.out.println("Trace1");
	Thread.sleep(2000);
	findByList(driver,allocateTemp,"//ul[@id='ctl08_ctl05_gridTemplate_grid']","//span[contains(@class,'ui-icon ui-icon-circle-arrow-e')]");
	alertBox(driver);
	
}
//Back to the apply Attached Template
@Test(dataProvider="AllocateTemplateName",dependsOnMethods={"allocateTemplate"})
public void sendBackToAttachedTemplet(String allocateTemp,String AllocateTemp1) throws Exception{
	Thread.sleep(2000);
	findByList(driver,allocateTemp, "//ul [@id='ctl08_ctl05_gridAttachedTemplate_grid']", "//div[5]/span");
	Thread.sleep(1000);
	safeClick(driver, By.xpath("//li/div[3]/span"));
	}
@AfterClass
public void closeSelenium() throws Exception {
	driver.close();
	driver.quit();
}
}
