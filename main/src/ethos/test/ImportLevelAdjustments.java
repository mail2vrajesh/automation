package ethos.test;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
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

import bsh.ParseException;

import com.domain.ETHOSDomainWraper;

public class ImportLevelAdjustments extends ETHOSDomainWraper {

	public RemoteWebDriver driver = null;
	
	@BeforeClass
	public void startSelenium() throws Exception {	
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		driver= new FirefoxDriver();
		/*driver = new InternetExplorerDriver();*/
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@DataProvider
	public Object [ ][ ] users() {
		return new Object [ ] [ ] {{"madhva","madhva"} };
	}

	@Test(dataProvider="users")
	public void ethosSignin(String username, String password) throws Exception {
		getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
	    ethosLogin(driver,username,password);
	    
		if(username.equals("madhva") || username.equals("sachin")){
			waitTitle(driver, "ETHOS Main Page", 10);
			elementPresent(driver, By.id("ctl00_btnLogout"), 10); 
		}else{
			textPresent(driver, "Your login attempt was not successful. Invalid User Name", 10);
		}
	}
	
	@DataProvider
	public Object [ ][ ] EFA() {
		return new Object [ ] [ ] {{ "System"}};
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
	
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyProductGroupListInLevlesAdjustments(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
	    waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
	    assertTrue("Product list is not there",elementPresent(driver, By.id("ctl00_cphParentContent_ClientGroupProductGroupHeader1_ClientGroupHeader1_Repeater1_ctl00_Label1"), 10));
	    
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_ProductOptionsForProductGroup(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
	    waitTitle(driver, "ETHOS", 10);
	    safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
	    assertTrue("Product drop down is not selected as per product group options",new Select(driver.findElement(By.id("ctl00_cphMainContent_ddlProduct"))).getFirstSelectedOption().getText().contains("Gas Supply"));
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyListLevelAndAdjustments(String item) throws Exception  {
		
		navigateToProductGroup(driver, "ELE", "Import Level Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_txtDateFrom"));
		safeType(driver, By.id("ctl00_cphMainContent_txtDateFrom"), "text");
		waitTitle(driver, "ETHOS", 10);
	    assertTrue("Level/Adjustment Type: is missing for the product group", elementPresent(driver, By.id("ctl00_cphMainContent_ddlLevAdjType"), 10));   

	    
	}
	
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyUpdateLevelAsGroup(String item) throws Exception  {
		

		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Uplift Factor: Climate Change Levy");
		waitTitle(driver, "ETHOS", 10);
	    assertTrue("Level/Adjustment Type: is missing for the product group", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), 10));       
	}
	
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyUpdateLevelAsCompany(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Comapny radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_1']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_1']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Volume Adjustment Factor");
		waitTitle(driver, "ETHOS", 10);
	    assertTrue("Level/Adjustment Type: is missing for the product group", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), 10));     
	    
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyUpdateLevelAsSite(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("site radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_2']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_2']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
	    assertTrue("Level/Adjustment Type: is missing for the product group", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), 10));     
	    
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyUpdateLevelAsDeliveryPoint(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Delivery point radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
	    assertTrue("Level/Adjustment Type: is missing for the product group", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), 10));     
    
	}
	
	//
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyDefaultDateToCurrentDate(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));	
		// Other code here
		/*String toDate=safeGetText(driver, By.xpath("//*[@id='ctl00_cphMainContent_txtDateFrom']"));
	    System.out.println(toDate);*/
		String toDate="04/04/2014";
	    //toDate = "05/11/2010";

	    // Value assigned to toDate somewhere in here

	    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
	    Calendar currDtCal = Calendar.getInstance();

	    // Zero out the hour, minute, second, and millisecond
	    currDtCal.set(Calendar.HOUR_OF_DAY, 0);
	    currDtCal.set(Calendar.MINUTE, 0);
	    currDtCal.set(Calendar.SECOND, 0);
	    currDtCal.set(Calendar.MILLISECOND, 0);

	    Date currDt = currDtCal.getTime();

	    Date toDt;
	    toDt = df.parse(toDate);

	    if (currDt.equals(toDt)) {
	    	System.out.println("same");
	        // They're the same date
	    }
	    /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date();*/
	    /*System.out.println(dateFormat.format(date));*/
	   /* safeClick(driver,By.xpath("//*[@id='spanctl00_cphMainContent_txtDateFrom']/img"));
	    String dat1=safeGetText(driver, By.xpath("//*[@id='CalendarControl']/table/tbody/tr[3]/td[6]/a"));
	    System.out.println(dat1);
	    String ethosdate=safeGetText(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
	    System.out.println(ethosdate);*/
        
	}
	
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyDefaultDateToOpenSelected(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));	    
		assertTrue("Deafault date to Open selected is not there ", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_chkDateBox_txtDateTo']"), 10));     
        
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyDefaultDateToOpenDiselected(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));	
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_chkDateBox_txtDateTo']"));
		assertTrue("Inbox is not displayed", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_txtDateTo']"), 10));     
        
	}
	
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyResetButton(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Delivery point radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_btnResetFilter']"));
		waitForPagetoLoad_Element(driver, 10, By.xpath("//*[@id='ctl00_cphMainContent_btnRemove']"));
		assertTrue("Reset button didn't reset the values",new Select(driver.findElement(By.id("ctl00_cphMainContent_ddlProductGroup"))).getFirstSelectedOption().getText().contains("Select Product Group"));
		
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyImportFile(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Gas");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProduct']"), "Gas Supply");
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_0']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "CLIENT_DESC");
		waitTitle(driver, "ETHOS", 10);
		String value="\\\\192.168.185.43\\Automation_import\\017_CLIENT_GRP.xls";
		safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
		safeClick(driver,By.id("ctl00_cphMainContent_btnImport"));
		waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnImport"));
		assertTrue("Import Levels/Adjustments - Review",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Import Levels/Adjustments - Review"));
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyCommit(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Gas");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProduct']"), "Gas Supply");
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_0']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "CLIENT_DESC");
		waitTitle(driver, "ETHOS", 10);
		String value="\\\\192.168.185.43\\Automation_import\\017_CLIENT_GRP.xls";
		safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
		safeClick(driver,By.id("ctl00_cphMainContent_btnImport"));
		waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnImport"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnCommit"));
		assertTrue("The data was not imported successfully.",driver.findElement(By.xpath("//*[@id='ctl00_cphMainContent_pnlSuccess']/span")).getText().contains("The data was imported successfully."));
		safeClick(driver, By.id("ctl00_cphMainContent_btnCommit"));
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyDataOverwrite(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Gas");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProduct']"), "Gas Supply");
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_0']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "CLIENT_DESC");
		waitTitle(driver, "ETHOS", 10);
		String value="\\\\192.168.185.43\\Automation_import\\017_CLIENT_GRP.xls";
		safeType(driver,By.id("ctl00_cphMainContent_txtFile"),value);
		safeClick(driver,By.id("ctl00_cphMainContent_btnImport"));
		waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnImport"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnCommit"));
		assertTrue("Overwrite messgage is not there..",driver.findElement(By.id("ctl00_cphMainContent_pnlNoErrors")).getText().contains("The spreadsheet does not contain any errors. Click commit to save the data."));
		safeClick(driver, By.id("ctl00_cphMainContent_btnCommit"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnFinish"));
		safeClick(driver, By.id("ctl00_cphMainContent_btnFinish"));
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyImportFileErrorMessage(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Delivery point radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
		safeType(driver, By.xpath("//*[@id='ctl00_cphMainContent_txtFile']"), "file");
		safeClick(driver,By.id("ctl00_cphMainContent_btnImport"));
		waitTitle(driver, "ETHOS", 10);
		assertTrue("Failure message is not there",elementPresent(driver, By.id("ctl00_cphMainContent_lblStatus"), 10));
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyExportLevelsadjustmentsTemplate(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		assertTrue("Delivery point radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
		cancelDownloader(driver, "ctl00_cphMainContent_btnExportTemplate");
		
	}
	//passed
	@Test(dataProvider = "EFA", dependsOnMethods = {"ethosSignin"})
	public void clientMaintenance_VerifyRemoveButton(String item) throws Exception  {
		
		navigateToClientMaintenanceGroup(driver, "Import Levels & Adjustments");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProductGroup']"), "Electricity");
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlProduct']"), "Half Hourly Electricity");
		waitTitle(driver, "ETHOS", 10);
		assertTrue("Delivery point radio button is missing", elementPresent(driver, By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"), 10));     
		safeClick(driver,By.xpath("//*[@id='ctl00_cphMainContent_radioUpdateLevel_3']"));
		waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnResetFilter"));
		safeSelectByText(driver, By.xpath("//*[@id='ctl00_cphMainContent_ddlLevAdjType']"), "Bill Adjustment");
		waitTitle(driver, "ETHOS", 10);
		safeClick(driver, By.id("ctl00_cphMainContent_btnRemove"));
		dismissAlert(driver, "Are you sure you want to remove all levels/adjustments that apply within the specified date range?");
		
	}

	
}


