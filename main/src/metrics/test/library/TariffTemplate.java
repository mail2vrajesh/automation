package metrics.test.library;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;
public class TariffTemplate  extends MetricsDomainWraper{
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
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
	}

//Navigation to Tariff Template screen									
@Test
public void navigation_TariffTemplate() throws Exception{
gotoSubMenu(driver, "//a[text()='Library']","//a[text()='Tariff Templates']","Tariff Templates");	
//Tariff Template screen should display with List of Existing tariff templates.
WebElement table=driver.findElement(By.xpath("//div [@id='divDetail']/div[1]/div[1]/div/div/div[1]"));
List<WebElement>rows=table.findElements(By.tagName("div"));
System.out.println("List of Existing tariff templates");
for(WebElement row:rows){
	System.out.println(row.getText());
}
}
//Tariff Template creation								
@DataProvider
public Object [ ][ ] TariffTemplateName() throws Exception {
return new Object [ ] [ ] {{"ukclient"+putuser()}
		   };
	   }
@Test(dataProvider="TariffTemplateName",dependsOnMethods={"navigation_TariffTemplate"})
public void createNew_TariffTemplate(String TrariffName)throws Exception{
safeClick(driver, By.xpath("//*[@id='ucSTTariffLibrary_btnNew']/span"));
//Specify Tariff Template in Edit Box and Click on Save button.
safeType(driver, By.xpath("//input [@id='ctl08_ctl05_ucSTTariffLibrary_txtNew']"),TrariffName);
safeClick(driver, By.xpath("//div[17]/div[2]/a/span"));
System.out.println("The Name is successfully added");	
//verify created TariffTemplateName	and Create Tariff Lines for the Created Tariff template.
safeClick(driver, By.xpath("//a[contains(text(),'"+TrariffName+"')]"));
safeClick(driver, By.xpath("//li[2]/div/span"));
} 
//create the tariffLines
@Test(dependsOnMethods={"createNew_TariffTemplate"})
public void createNew_TariffLines()throws Exception{
	Thread.sleep(1000);
	safeClick(driver, By.xpath("//*[@id='ctl08_ctl05_gridReference_btnDisplayNew']/span"));
	new Select (driver.findElement(By.xpath("//select[@name='ctl08$ctl05$gridReference$ctl01']"))).selectByVisibleText("Ref_DUoS 2013");
    safeClick(driver, By.xpath("//span[contains(@class,'ui-icon-disk buttomtype')]"));
	
    safeClick(driver, By.xpath("//*[@id='ctl08_ctl05_gridReference_btnDisplayNew']/span"));
    new Select (driver.findElement(By.xpath("//select[@name='ctl08$ctl05$gridReference$ctl01']"))).selectByVisibleText("Ref_DE_HH_D_N");
	safeClick(driver, By.xpath("//span[contains(@class,'ui-icon-disk buttomtype')]"));
	
	safeClick(driver, By.xpath("//*[@id='ctl08_ctl05_gridReference_btnDisplayNew']/span"));
	new Select (driver.findElement(By.xpath("//select[@name='ctl08$ctl05$gridReference$ctl01']"))).selectByVisibleText("UK_NHH_SR+SC+FiT");
	safeClick(driver, By.xpath("//span[contains(@class,'ui-icon-disk buttomtype')]"));
}
//Delete Tariff Template Lines	
@DataProvider
public Object [ ][ ] deleteTariffLines() throws Exception {
return new Object [ ] [ ] {{"UK_NHH_SR+SC+FiT"}
		   };
	   }
@Test(dataProvider="deleteTariffLines",dependsOnMethods={"createNew_TariffLines"})
public void delete_TariffLines(String line)throws Exception{
	findByList(driver,line,"//*[@id='ctl08_ctl05_divDetails']/div[1]/div[1]/div[1]/div/div","//li/div[2]/span");	
boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
if(isPresent==true){
	Thread.sleep(1000);
	String errortest=driver.findElement(By.xpath("//div[@class='ui-dialog-content ui-widget-content']")).getText(); 
	driver.findElement(By.xpath("//span[text()='Yes']")).click(); 
	System.out.println("The TariffTemplateLine delected");
	System.out.println(errortest);
	}
	
}


@Test(dependsOnMethods={"delete_TariffLines"})
public void tariffTemplate() throws Exception{
	safeClick(driver, By.xpath("//a[@id='ucSTTariffLibrary_btnModify']/span"));
	safeClick(driver, By.xpath("//span[contains(@class,'ui-icon ui-icon-trash')]"));
	boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
	if(isPresent==true){
		Thread.sleep(1000);
		String errortest=driver.findElement(By.xpath("//div[@class='ui-dialog-content ui-widget-content']")).getText(); 
		driver.findElement(By.xpath("//span[text()='Yes']")).click(); 
		System.out.println("The TariffTemplate delected");
		System.out.println(errortest);
	    safeClick(driver, By.xpath("//span[text()='Ok']"));
		}
		}
//Validations on Tariff Template									
@DataProvider
public Object [ ][ ] DuplicateTariffName() throws Exception {
return new Object [ ] [ ] {{"Ref_UK_E_Supplier Charges"}
		   };
	   }

@Test(dataProvider="DuplicateTariffName",dependsOnMethods={"delete_TariffLines"})
public void tariffTemplate(String duplicate) throws Exception{
	gotoSubMenu(driver, "//a[text()='Library']","//a[text()='Tariff Templates']","Tariff Templates");		
	safeClick(driver, By.xpath("//*[@id='ucSTTariffLibrary_btnNew']/span"));
	//Specify Tariff Template in Edit Box and Click on Save button.
	safeType(driver, By.xpath("//input [@id='ctl08_ctl05_ucSTTariffLibrary_txtNew']"),duplicate);
	safeClick(driver, By.xpath("//div[17]/div[2]/a/span"));
	alertBox(driver);
}
/*//create Timeband Profiles 
@Test(dependsOnMethods={"delete_TariffLines"})
public void tariffTemplate_TimeBand() throws Exception{
	safeClick(driver, By.xpath("//*[@id='ctl08_ctl05_gridFactorProfile_btnDisplayNew']/span"));
	safeType(driver, By.xpath("//li[2]/div[1]/input"),"01/01/2012");
	safeType(driver, By.xpath("//li[2]/div[2]/input"),"31/12/2014");
	new Select(driver.findElement(By.xpath("//li[2]/div[3]/select"))).selectByVisibleText("P3");
	safeType(driver, By.xpath("//input[@name='ctl08$ctl05$gridFactorProfile$ctl07']"),"20");
	safeType(driver, By.xpath("//input[@name='ctl08$ctl05$gridFactorProfile$ctl09']"),"clientName");
    safeClick(driver, By.xpath("//li/div[6]/span[1]"));
    System.out.println("TimeBand is created");
	
}*/
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


