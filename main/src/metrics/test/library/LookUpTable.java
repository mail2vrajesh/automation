package metrics.test.library;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class LookUpTable extends MetricsDomainWraper{
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {	
	File file = new File("exe\\IEDriverServer.exe");
	DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
	//driver = new InternetExplorerDriver(capability);
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	getApp(driver,cachedProperties.value("Metrics_url"),"Login");
	metricsLogin(driver,"Metrics","Metrics");
	}
	
	//Create Lookup Table	
	@DataProvider
	public Object [ ][ ] TableName() throws Exception {
		return new Object [ ] [ ] {{ "Uk-Suplier"+putuser()} };
	}
	//@Test(dataProvider="TableName")
	public void createTheNewLooUpTable(String name) throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		//Select'+(add)'symbol in Lookup Admin table.
		safeClick(driver, By.xpath("//li[3]/div/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$ucFlexTables$gridFlexTables$ctl01']"), name);
        safeClick(driver,By.xpath("//div[3]/span"));
        //verify the admin name added or not
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[1]");
        //Click on 'Active the table' button with out creating the Data & Index columns.
        safeClick(driver, By.xpath("//a[text()='Activate Table']"));
        alertBox(driver);
        //give the values in tableSettings create column with index
        safeClick(driver,By.xpath("//li[3]/div/span"));
        safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridTableColumns$ctl01']"),"Metrics1");
        safeClick(driver,By.xpath("//div[3]/input"));
        safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridTableColumns$ctl07']"),"1");
        safeClick(driver, By.xpath("//div[5]/span"));
       //give the values in tableSettings create column with with out index
        safeClick(driver,By.xpath("//li[3]/div/span"));
        safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridTableColumns$ctl01']"),"Metrics2");
        safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridTableColumns$ctl07']"),"2");
        safeClick(driver, By.xpath("//div[5]/span"));
        //click the active table butoon and close it
        safeClick(driver, By.xpath("//a[text()='Activate Table']"));
        safeClick(driver, By.xpath("//div[3]/span"));
        //Try to delete the Lookup table with 'Active' Status/Uploading Lookup data for the selected Lookup table.
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[3]");
        boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
		if(isPresent==true){
			Thread.sleep(1000);
			String errortest=driver.findElement(By.xpath("//*[@id='accesscontrol-auth']/div[1]/div[2]")).getText(); 
			driver.findElement(By.xpath("//span[text()='Yes']")).click(); 
			System.out.println(errortest);
			alertBox(driver);
			}
	}
	
   //Try to delete the Lookup table with 'Inactive' Status.
	@DataProvider
	public Object [ ][ ] TableInActiveName() throws Exception {
		return new Object [ ] [ ] {{ "Uk-Suplier1"+putuser()} };
	}
	//@Test(dataProvider="TableInActiveName",dependsOnMethods="createTheNewLooUpTable")
	public void deleteInactiveName(String name) throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		//Select'+(add)'symbol in Lookup Admin table.
		safeClick(driver, By.xpath("//li[3]/div/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$ucFlexTables$gridFlexTables$ctl01']"), name);
        safeClick(driver,By.xpath("//div[3]/span"));
        //verify the admin name added or not
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[1]");
        safeClick(driver, By.xpath("//div[3]/span"));
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[3]");
        boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
		if(isPresent==true){
			Thread.sleep(1000);
			String errortest=driver.findElement(By.xpath("//*[@id='accesscontrol-auth']/div[1]/div[2]")).getText(); 
			driver.findElement(By.xpath("//span[text()='Yes']")).click(); 
			System.out.println(errortest);
			
			}
	
	}
	//@Test(dependsOnMethods="deleteInactiveName")
	public void  NavigationtoLookupDatascreen() throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		safeClick(driver, By.xpath("//a[text()='Look Up Data']"));
		boolean ImportFile=isElementPresent(driver,By.xpath("//a[text()='Import File']"));
		Assert.assertTrue(ImportFile);
		String actual=driver.getTitle();
		Assert.assertEquals(actual, "Look Up Data");
	}
	//Upload Lookup Data	
	
	@DataProvider
	public Object [ ][ ] DataName() throws Exception {
		return new Object [ ] [ ] {{ "Uk-Suplier"} };
	}
	@Test(dataProvider="DataName"/*dependsOnMethods="NavigationtoLookupDatascreen"*/)
	public void  uploadLookupData(String name) throws Exception{
	
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		safeClick(driver, By.xpath("//a[text()='Look Up Data']"));
		findByList(driver,name,"//ul [@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span");
		boolean ExportData=isElementPresent(driver,By.xpath("//a[text()='Export Data']"));
		boolean ExportBlankTemplate=isElementPresent(driver,By.xpath("//a[text()='Export Blank Template']"));
		Assert.assertTrue(ExportData);
		Assert.assertTrue(ExportBlankTemplate);
		DeleteFile("CustomizeTable_Blank_Table.xls");
		Downloader(driver, "ctl08_ctl03_lbnExportBlankTemplate");
		String templatcontains=readDataFromXL("CustomizeTable_Blank_Table.xls",0,1,1);
		assertTrue(templatcontains.contains("Data PK"));
		assertTrue(templatcontains.contains("StartDate"));
		assertTrue(templatcontains.contains("EndDate"));

}
}