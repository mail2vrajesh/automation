package metrics.test.library;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class LookUpTable extends MetricsDomainWraper{
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
	
	//Create Lookup Table	
	@DataProvider
	public Object [ ][ ] TableName() throws Exception {
		return new Object [ ] [ ] {{ "Uk-Suplier"+putuser()} };
	}
	@Test(dataProvider="TableName")
	public void createTheNewLooUpTable(String name) throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		//Select'+(add)'symbol in Lookup Admin table.
		safeClick(driver, By.xpath("//li[3]/div/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$ucFlexTables$gridFlexTables$ctl01']"), name);
        safeClick(driver,By.xpath("//div[3]/span"));
        //verify the admin name added or not
        Thread.sleep(1000);
        findByList(driver,name,"//*[@id='divDetail']/div/div[1]/div/div[1]/div/div[1]","//div[3]/span[1]");
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
        //click the active table button and close it
        safeClick(driver, By.xpath("//a[text()='Activate Table']"));
        safeClick(driver, By.xpath("//div[3]/span"));
        //Try to delete the Lookup table with 'Active' Status/Uploading Lookup data for the selected Lookup table.
        Thread.sleep(1000);
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
	@Test(dataProvider="TableInActiveName",dependsOnMethods="createTheNewLooUpTable")
	public void deleteInactiveName(String name) throws Exception{
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		//Select'+(add)'symbol in Lookup Admin table.
		safeClick(driver, By.xpath("//li[3]/div/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$ucFlexTables$gridFlexTables$ctl01']"), name);
        safeClick(driver,By.xpath("//div[3]/span"));
        //verify the admin name added or not
        Thread.sleep(1000);
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[1]");
        Thread.sleep(2000);
        safeClick(driver, By.xpath("//li/div[3]/span"));
        Thread.sleep(1000);
        findByList(driver,name,"//*[@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span[3]");
        boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
		if(isPresent==true){
			Thread.sleep(1000);
			String errortest=driver.findElement(By.xpath("//*[@id='accesscontrol-auth']/div[1]/div[2]")).getText(); 
			driver.findElement(By.xpath("//span[text()='Yes']")).click(); 
			System.out.println(errortest);
			
			}	
	}
	@Test(dependsOnMethods="deleteInactiveName")
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
	@Test(dataProvider="DataName",dependsOnMethods="NavigationtoLookupDatascreen")
	public void  downloadLookupTable(String name) throws Exception{
	
		gotoSubMenu(driver, "//a[text()='Library']", "//a[text()='Look Up Tables']","Look Up Settings");
		safeClick(driver, By.xpath("//a[text()='Look Up Data']"));
		findByList(driver,name,"//ul [@id='ctl08_ctl03_ucFlexTables_gridFlexTables_grid']","//div[3]/span");
		boolean ExportData=isElementPresent(driver,By.xpath("//a[text()='Export Data']"));
		boolean ExportBlankTemplate=isElementPresent(driver,By.xpath("//a[text()='Export Blank Template']"));
		Assert.assertTrue(ExportData);
		Assert.assertTrue(ExportBlankTemplate);
		DeleteFile(getFile("CustomizeTable_Blank_Table"));
		Downloader(driver, "ctl08_ctl03_lbnExportBlankTemplate");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Blank_Table"),0,4,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Data PK"));
		assertTrue(templatcontains.contains("StartDate"));
		assertTrue(templatcontains.contains("EndDate"));

}
	
	
	@Test (dependsOnMethods={"downloadLookupTable"})
	public  void importLookupTable_Blank_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_LookupTable_Blank_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Invalid End Date", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Invalid End Date is not displayed", false);
		}
	}
	

	@Test (dependsOnMethods={"importLookupTable_Blank_dateRegionalSetting"})
	public  void importLookupTable_BlankValid() throws Exception{
		importXL(driver,cachedProperties.value("LSUpload_LookupTable_Blank_Valid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}
	}
	
	
	@Test(dependsOnMethods="importLookupTable_BlankValid")
	public void  downloadLookupTableModify() throws Exception{
		DeleteFile(getFile("CustomizeTable_Modify_Uk-Suplier"));
		Downloader(driver, "ctl08_ctl03_lbnExportModifyData");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Modify_Uk-Suplier"),0,4,2);
		System.out.println(templatcontains);
		
}
	
	//Edit the LookUpData Table Template 
	
	@Test(dependsOnMethods="downloadLookupTableModify")
	public void  downloadLookupTableEdit() throws Exception{
		DeleteFile(getFile("CustomizeTable_Blank_Table"));
		Downloader(driver, "ctl08_ctl03_lbnExportBlankTemplate");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Blank_Table"),0,4,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Data PK"));
		assertTrue(templatcontains.contains("StartDate"));
		assertTrue(templatcontains.contains("EndDate"));
		//import
		importXL(driver,cachedProperties.value("LSUpload_LookupTable_Blank_Valid_Edit"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}			
	}	
	@Test(dependsOnMethods="downloadLookupTableEdit")
	public void  downloadLookupEditTableModify() throws Exception{
		DeleteFile(getFile("CustomizeTable_Modify_Uk-Suplier"));
		Downloader(driver, "ctl08_ctl03_lbnExportModifyData");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Modify_Uk-Suplier"),0,4,2);
		System.out.println(templatcontains);
	}
	//delete the LookUpData Table Template
	@Test(dependsOnMethods="downloadLookupEditTableModify")
	public void  downloadLookupTableDelete() throws Exception{
		DeleteFile(getFile("CustomizeTable_Blank_Table"));
		Downloader(driver, "ctl08_ctl03_lbnExportBlankTemplate");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Blank_Table"),0,4,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Data PK"));
		assertTrue(templatcontains.contains("StartDate"));
		assertTrue(templatcontains.contains("EndDate"));
		//import
		importXL(driver,cachedProperties.value("LSUpload_LookupTable_Blank_Valid_Delete"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported successfully not displayed", false);
		}
			
	}
	@Test(dependsOnMethods="downloadLookupTableDelete")
	public void  downloadLookupDeleteTableModify() throws Exception{
		DeleteFile(getFile("CustomizeTable_Modify_Uk-Suplier"));
		Downloader(driver, "ctl08_ctl03_lbnExportModifyData");
		String templatcontains=readDataFromXL(getFile("CustomizeTable_Modify_Uk-Suplier"),0,4,2);
		System.out.println(templatcontains);
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