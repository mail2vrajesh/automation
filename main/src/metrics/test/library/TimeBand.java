package metrics.test.library;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class TimeBand extends MetricsDomainWraper{
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {	
	File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
	DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
	driver = new InternetExplorerDriver(capability);
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	getApp(driver,cachedProperties.value("Metrics_url"),"Login");
	driver.manage().window().maximize();
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
	}
//Navigation to Time Band Template screen									
 @Test
   public void NavigationToTimeBandTemplateScreen() throws Exception{
   gotoSubMenu(driver,"//a[text()='Library']","//a[text()='Timeband Settings']","Timeband Templates");   
	boolean p1=isElementPresent(driver, By.xpath("//a [text()='P1']"));
	boolean p2=isElementPresent(driver, By.xpath("//a [text()='P2']"));
	boolean p3=isElementPresent(driver, By.xpath("//a [text()='P3']"));
	boolean p4=isElementPresent(driver, By.xpath("//a [text()='P4']"));
	boolean p5=isElementPresent(driver, By.xpath("//a [text()='P5']"));
	boolean p6=isElementPresent(driver, By.xpath("//a [text()='P6']"));
	Assert.assertTrue(p1);
	Assert.assertTrue(p2);
	Assert.assertTrue(p3);
	Assert.assertTrue(p4);
	Assert.assertTrue(p5);
	Assert.assertTrue(p6);
	safeClick(driver, By.xpath("//a [text()='P6']"));
	WebElement table=driver.findElement(By.xpath("//ul [@id='ctl08_ctl05_gridTemplateLine_grid']"));
	List<WebElement> rows=table.findElements(By.tagName("li"));
	for(WebElement row:rows){
		System.out.println(row.getText());
		}
	}
   //Time Band Profile screen
   @DataProvider
   public Object [ ][ ] TimeBandName() throws Exception {
	   return new Object [ ] [ ] {{"Uk-Name"+putuser()}
			   };
	   }
   @Test(dataProvider="TimeBandName",dependsOnMethods="NavigationToTimeBandTemplateScreen")
   public void TimeBandProfilescreen(String newTimeBand) throws Exception{
	   gotoSubMenu(driver,"//a[text()='Library']","//a[text()='Timeband Settings']","Timeband Templates");
	   safeClick(driver, By.xpath("//a[text()='Timeband Profiles']"));
	   //verify the title 
	   String actual=driver.getTitle();
	   Assert.assertEquals(actual,"Timeband Profiles");
	   //verify the '+add' button displayed or not
	   boolean addButton=driver.findElement(By.xpath("//div[17]/div/div/span")).isDisplayed();
       Assert.assertTrue(addButton);
       //create the new TimeBand
       safeClick(driver, By.xpath("//div[17]/div/div/span"));
       boolean saveButton=driver.findElement(By.xpath("//div[17]/div[2]/a/span")).isDisplayed();
       Assert.assertTrue(saveButton);
       boolean cancleButton=driver.findElement(By.xpath("//div[17]/div[2]/div/span")).isDisplayed();
       Assert.assertTrue(cancleButton);
       
	   safeType(driver, By.xpath("//input [@id='ctl08_ctl05_ucSTTimebandProfile_txtNew']"), newTimeBand);
	   safeClick(driver,By.xpath("//div[17]/div[2]/div/span"));
	   
	  /* safeClick(driver,By.xpath("//li/div[2]/div[2]/span"));
	   new Select(driver.findElement(By.xpath("//select[@name='ctl08$ctl05$ddlTemplate$dropDownList']"))).selectByVisibleText("P1");
	   
	  
	   safeType(driver, By.xpath("//input[@name='compnent-name']"),"Working");
<<<<<<< .mine
	   safeClick(driver,By.xpath("//div[17]/div[2]/div/span"));
	   }    
=======
	   safeClick(driver,By.xpath("//div[17]/div[2]/div/span"));*/
	   }   

   //Holiday Screen
   @DataProvider
   public Object [ ][ ] HolidayName() throws Exception {
	   return new Object [ ] [ ] {{"client","AAATest"}//,"Sevam_Client" ,String name
			   };
	   }
   @Test(dataProvider="HolidayName"/*,dependsOnMethods={"TimeBandProfilescreen"}*/)
   public void Holidaysscreen(String clientName,String name) throws Exception{
	   gotoSubMenu(driver,"//a[text()='Library']","//a[text()='Timeband Settings']","Timeband Templates");
	   safeClick(driver, By.xpath("//a[text()='Holidays']"));
	   //verify the title 
	   String actual=driver.getTitle();
	   Assert.assertEquals(actual,"Holidays");
	   safeClick(driver, By.xpath("//div[text()='Click to filter the list']"));
	   safeType(driver, By.xpath("//*[@id='ctl08_ctl05_gridGroups_txtSearchFilter']"),clientName);
	   System.out.println(clientName);
	   findByList(driver, name,"//*[@id='ctl08_ctl05_gridGroups_grid']","//li/div[3]/span");
	   safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-plus"));
	   safeType(driver, By.name("ctl08$ctl05$gridHoliday$ctl02"), putDate(0));
	   safeType(driver, By.name("ctl08$ctl05$gridHoliday$ctl04"), "testing");
	   safeClick(driver, By.xpath("//div[4]/span"));
       alertBox(driver);
	   
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
