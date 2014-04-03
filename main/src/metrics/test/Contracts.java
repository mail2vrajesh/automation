package metrics.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class Contracts extends MetricsDomainWraper {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		//driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
	}



	@Test 
	public  void download_Upload_Client() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Client_Blank.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnBlankClientTemplate");
		String templatcontains=readDataFromXL("Upload_Client_Blank.xlsx",0,1,1);
		
		assertTrue(templatcontains.contains("ClientPK"));
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Country"));
		assertTrue(templatcontains.contains("Address line 1"));
		assertTrue(templatcontains.contains("Address line 2"));
		assertTrue(templatcontains.contains("Address line 3"));
		assertTrue(templatcontains.contains("Address line 4"));
		assertTrue(templatcontains.contains("Post code"));
		assertTrue(templatcontains.contains("Note"));
	}
	
	@Test (dependsOnMethods={"download_Upload_Client"})
	public  void importUpload_Client_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankValid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));

		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported Unsuccessfully", false);
		}
		simpleFilter(driver,"Client",cachedProperties.value("ClientName"));
		Thread.sleep(2000);
		String ClientDisplayed=driver.findElement(By.xpath("//ul[@id='multiSelection-grid']/li")).getText();
		//assertEquals(ClientDisplayed, cachedProperties.value("ClientName"));

	}
	
	@Test (dependsOnMethods={"importUpload_Client_Valid"})
	public  void importUpload_Client_CountryBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankCountryBlank"));
		Boolean success =textPresent(driver, "Country cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}

	

	@Test (dependsOnMethods={"importUpload_Client_CountryBlank"})
	public  void importUpload_Client_Duplicate() throws Exception{
		importXL(driver,cachedProperties.value("SSUploadClientBlankValid"));
		Boolean success =textPresent(driver, "duplicated", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}

	
	
	@Test//(dependsOnMethods={"importUpload_Client_Duplicate"})
	public  void download_Site_Blank() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Site_Blank.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnBlankSiteTemplate");
		String templatcontains=readDataFromXL("Upload_Site_Blank.xlsx",0,1,1);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("SitePK"));
		assertTrue(templatcontains.contains("Site Name"));
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Country"));
		assertTrue(templatcontains.contains("Address line 1"));
		assertTrue(templatcontains.contains("Address line 2"));
		assertTrue(templatcontains.contains("Address line 3"));
		assertTrue(templatcontains.contains("Address line 4"));
		assertTrue(templatcontains.contains("Post code"));
		assertTrue(templatcontains.contains("Note"));
		assertTrue(templatcontains.contains("Gas"));
		assertTrue(templatcontains.contains("Power"));
		assertTrue(templatcontains.contains("Water"));
		assertTrue(templatcontains.contains("Carbon"));
		assertTrue(templatcontains.contains("Oil"));
		assertTrue(templatcontains.contains("Coal"));
	}
				
			
	@Test (dependsOnMethods={"download_Site_Blank"})
	public  void importUpload_Site_CountryBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_CountryBlank"));
		Boolean success =textPresent(driver, "Country cannot be blank", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Site_CountryBlank"})
	public  void importUpload_Site_CommodityBlank() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_CommodityBlank"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Site_CommodityBlank"})
	public  void importUpload_Site_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Valid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Site_Valid"})
	public  void importUpload_Site_Duplicate() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Valid"));
		Boolean success =textPresent(driver, "duplicated", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("not a duplicated", false);
		}
	}
	
	
	@Test//(dependsOnMethods={"importUpload_Client_Duplicate"})
	public  void download_Site_Modify() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_Site.xlsx");
		Downloader(driver, "ctl08_ctl05_lbnSiteData");
		String templatcontains=readDataFromXL("Upload_Site.xlsx",1,2,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Sevam_Site"));
		assertTrue(templatcontains.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains.contains("ALL Countries"));
		
	}
	 
	@Test (dependsOnMethods={"download_Site_Modify"})
	public  void importUpload_Site_Modify_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Modify"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported Unsuccessfully", false);
		}
	}
	
	@Test
	public  void download_Account_References() throws Exception{
		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		safeClick(driver, By.id("ctl08_ctl05_selectClients_multiSelection_rptSelection_ctl00_liItem"));
		DeleteFile("Upload_AccountRefTemplate.xls");
		Downloader(driver, "ctl08_ctl05_lbnAccountRefData");
		String templatcontains=readDataFromXL("Upload_AccountRefTemplate.xls",0,1,2);
		System.out.println(templatcontains);
		assertTrue(templatcontains.contains("Client Name"));
		assertTrue(templatcontains.contains("Site Name"));
		assertTrue(templatcontains.contains("Meter"));
		assertTrue(templatcontains.contains("Start Date"));
		assertTrue(templatcontains.contains("End Date"));
		assertTrue(templatcontains.contains("Supplier Account Ref"));
		assertTrue(templatcontains.contains("Supplier Name"));
		String templatcontains1=readDataFromXL("Upload_AccountRefTemplate.xls",0,2,2);
		System.out.println(templatcontains1);
		assertTrue(templatcontains1.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains1.contains("Sevam_Site1"));
		assertTrue(templatcontains1.contains("A12"));
		String templatcontains2=readDataFromXL("Upload_AccountRefTemplate.xls",0,3,2);
		System.out.println(templatcontains2);
		assertTrue(templatcontains2.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains2.contains("Sevam_Site1"));
		assertTrue(templatcontains2.contains("A13"));
		String templatcontains3=readDataFromXL("Upload_AccountRefTemplate.xls",0,4,2);
		System.out.println(templatcontains3);
		assertTrue(templatcontains3.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains3.contains("Sevam_Site2"));
		assertTrue(templatcontains3.contains("A14"));
		String templatcontains4=readDataFromXL("Upload_AccountRefTemplate.xls",0,5,2);
		System.out.println(templatcontains4);
		assertTrue(templatcontains4.contains(cachedProperties.value("ClientName")));
		assertTrue(templatcontains4.contains("Sevam_Site2"));
		assertTrue(templatcontains4.contains("A15"));
		
	}
	
	@Test (dependsOnMethods={"download_Account_References"})
	public  void importUpload_Account_Ref_invalidDate() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_InvalidDate"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date. ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date is lesser than the end date. ", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_invalidDate"})
	public  void importUpload_Account_Ref_invalidDateFormat() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_InvalidDateFormat"));
		Boolean success =textPresent(driver, "Can't capture invalid date format", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Can't capture invalid date format", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_invalidDateFormat"})
	public  void importUpload_Account_Ref_EmptyRefNo() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_EmptyRefNo"));
		Boolean success =textPresent(driver, "Account Reference Number missing for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Account Reference Number missing for meter not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_EmptyRefNo"})
	public  void importUpload_Account_Ref_EmptyAccountName() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_EmptyAccountName"));
		Boolean success =textPresent(driver, "Supplier name (Issuer) not selected for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Supplier name (Issuer) not selected for meter not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_EmptyAccountName"})
	public  void importUpload_Account_Ref_Empty() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_AccountDuplicate"));
		Boolean success =textPresent(driver, "Account Reference duplicated for meter ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Account Reference duplicated for meter not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Account_Ref_Empty"})
	public  void importUpload_Account_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_Valid"));
		Boolean success =textPresent(driver, "File imported successfully", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("File imported Unsuccessfully", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importUpload_Account_Valid"})
	public  void importUpload_DateOverlap() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_DateOverlap"));
		Boolean success =textPresent(driver, "Dates overlap for meter", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Dates overlap for meter is not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importUpload_DateOverlap"})
	public  void importUpload_Account_Delete() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_AccountRef_Delete"));
		Boolean success =textPresent(driver, "The record(s) will be permanently deleted and cannot be recovered", 10);
		if(success){
			safeClick(driver, By.id("ctl08_ctl05_fileImport_lbnDelAccRef"));
			Boolean success2 =textPresent(driver, "Records were successfully deleted", 10);
			if(success2){
				safeClick(driver, By.xpath("//span[text()='Ok']"));
			}else{
				String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
				System.out.println(errortest2);
				safeClick(driver, By.xpath("//span[text()='Ok']"));
				assertTrue("The record(s) will be permanently deleted and cannot be recovered not dsiplayed", false);
			}
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("The record(s) will be permanently deleted and cannot be recovered not dsiplayed", false);
		}
	}
	
	
	
	@Test 
	public  void download_Client_Group() throws Exception{

		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		DeleteFile("Upload_ClientGroup.xls");
		Downloader(driver, "ctl08_ctl05_lbnClientGroupData");
		String templatcontains=readDataFromXL("Upload_ClientGroup.xls",0,0,1);
		System.out.println(templatcontains);
		String templatcontains1=readDataFromXL("Upload_ClientGroup.xls",0,2,1);
		System.out.println(templatcontains1);
		assertTrue(templatcontains.contains("EQ Excel Template"));
		assertTrue(templatcontains.contains("1.0.0.0"));
		assertTrue(templatcontains.contains("DataWareHouse"));
		assertTrue(templatcontains.contains("Template Name"));
		assertTrue(templatcontains.contains("Client groups modify and upload"));
		assertTrue(templatcontains1.contains("Client Name"));
		assertTrue(templatcontains1.contains("Start Date"));
		assertTrue(templatcontains1.contains("End Date"));
	}
	
	
	@Test (dependsOnMethods={"download_Client_Group"})
	public  void importClient_Group_InvalidClientName() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_GroupInvalidClientName"));
		Boolean success =textPresent(driver, "Client Sevam not found", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Client Sevam not found not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importClient_Group_InvalidClientName"})
	public  void importClient_GroupStartDateLess() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_StartDateLess"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date must be lesser than the end date not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importClient_GroupStartDateLess"})
	public  void importClient_Group_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Incorrect date format. Please follow the datetime format as defined in the regional settings.", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect date format. Please follow the datetime format as defined in the regional settings. not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importClient_Group_dateRegionalSetting"})
	public  void importClient_Group_GroupReq() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_NameReq"));
		Boolean success =textPresent(driver, "Group name not found. ", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Group name not found not displayed", false);
		}
	}
	
	
	//Veeresh
	
	@Test (dependsOnMethods={"importClient_Group_GroupReq"})
	public  void importClient_Group_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Client_Group_Valid"));
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
	
	
	
	@Test (dependsOnMethods={"importClient_Group_Valid"})
	public  void importSite_GroupStartDateLess() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_StartDateLess"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date must be lesser than the end date not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importSite_GroupStartDateLess"})
	public  void importSite_Group_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Incorrect date format. Please follow the datetime format as defined in the regional settings", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect date format. Please follow the datetime format as defined in the regional settings not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importSite_GroupStartDateLess"})
	public  void importSite_Group_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Site_Group_Valid"));
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
	
	
	
	@Test (dependsOnMethods={"importSite_Group_Valid"})
	public  void importMeter_GroupStartDateLess() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Meter_Group_StartDateLess"));
		Boolean success =textPresent(driver, "Start date must be lesser than the end date", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Start date must be lesser than the end date not displayed", false);
		}
	}
	
	
	@Test (dependsOnMethods={"importMeter_GroupStartDateLess"})
	public  void importMeter_Group_dateRegionalSetting() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Meter_Group_dateRegionalSetting"));
		Boolean success =textPresent(driver, "Incorrect date format. Please follow the datetime format as defined in the regional settings.", 10);
		if(success){
			safeClick(driver, By.xpath("//span[text()='Ok']"));	
		}else{
			String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			System.out.println(errortest2);
			safeClick(driver, By.xpath("//span[text()='Ok']"));
			assertTrue("Incorrect date format. Please follow the datetime format as defined in the regional settings not displayed", false);
		}
	}
	
	@Test (dependsOnMethods={"importMeter_Group_dateRegionalSetting"})
	public  void importMeter_Group_Valid() throws Exception{
		importXL(driver,cachedProperties.value("SSUpload_Meter_Group_Valid"));
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
		screenshot(_result, driver);
	}
	
}
