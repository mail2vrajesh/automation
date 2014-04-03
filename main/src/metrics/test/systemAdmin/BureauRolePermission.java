package metrics.test.systemAdmin;

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

public class BureauRolePermission extends MetricsDomainWraper{
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
    //select The Select Bureau Role Permission/ DW Role Permission option from System Admin Tab
	@DataProvider
	public Object [ ][ ] SelectRole() {
	return new Object [ ] [ ] {{ "MetricsAdmin"} };
	}
	@Test(dataProvider="SelectRole")
	public void selectBureauRolePermission(String role) throws Exception{
	gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Bureau Role Permission']","Bureau Role Permission");
	//take any of one of role
	findByList(driver, role,"//ul[@id='ctl08_ctl05_keyRoleCtl_gridRoles_grid']", "div[2]/span[1]");
		}
	//verify the all validations in BureauRolePermission
	@Test(dependsOnMethods="selectBureauRolePermission")
	public void verifyTheValidations_BureauRolePermission() throws Exception{
		boolean ReportParameters=isElementPresent(driver,By.xpath("//div[text()='Report Parameters']"));
		boolean TimebandProfiles=isElementPresent(driver,By.xpath("//div[text()='Timeband Profiles']"));
		boolean AttachGroupTemplates=isElementPresent(driver,By.xpath("//div[text()='Attach Group Templates']"));
		boolean Report=isElementPresent(driver,By.xpath("//div[text()='Report']"));
		boolean TariffTemplates=isElementPresent(driver,By.xpath("//div[text()='Tariff Templates']"));
		boolean Holiday=isElementPresent(driver,By.xpath("//div[text()='Holiday']"));
		boolean BureauAppAdmin=isElementPresent(driver,By.xpath("//div[text()='Bureau App Admin']"));
		boolean TimebandTemplates=isElementPresent(driver,By.xpath("//div[text()='Timeband Templates']"));
		boolean Write=isElementPresent(driver,By.xpath("//div[text()='Write']"));
		boolean Access=isElementPresent(driver,By.xpath("//div[text()='Access']"));
		boolean Unlock=isElementPresent(driver,By.xpath("//div[text()='Unlock']"));
		Assert.assertTrue(ReportParameters);
		Assert.assertTrue(TimebandProfiles);
		Assert.assertTrue(AttachGroupTemplates);
		Assert.assertTrue(Report);
		Assert.assertTrue(Holiday);
		Assert.assertTrue(TariffTemplates);
		Assert.assertTrue(TimebandTemplates);
		Assert.assertTrue(BureauAppAdmin);
		Assert.assertTrue(Write);
		Assert.assertTrue(Access);
		Assert.assertTrue(Unlock);
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl04$ctl05']"));
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl05$ctl01']"));
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl06$ctl04']"));
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl08$ctl10']"));
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl04$ctl10']"));
		safeClick(driver,By.xpath("//input[@name='ctl08$ctl05$keyRoleCtl$rptKey$ctl02$ctl07']"));
		Thread.sleep(1000);
		safeClick(driver,By.xpath("//a[text()='Save']"));
		
	}
	
	//DW Role Permission
	@DataProvider
	public Object [ ][ ] DWSelectRole() {
	return new Object [ ] [ ] {{ "MetricsAdmin"} };
	}
	@Test(dataProvider="DWSelectRole",dependsOnMethods="verifyTheValidations_BureauRolePermission")
	public void verifyTheValidations_DWRolePermission(String role) throws Exception{
    gotoSubMenu(driver, "//a[text()='System Admin']","//a[text()='DW Role Permission']","DW Role Permission");
	findByList(driver, role,"//*[@id='ctl08_ctl03_keyRoleCtl_gridRoles_grid']", "//li/div[2]/span");
	boolean SiteInformation=isElementPresent(driver,By.xpath("//div[text()='Site Information']"));
	boolean SiteTechnicalParameters=isElementPresent(driver,By.xpath("//div[text()='Site Technical Parameters']"));
	boolean Consumption=isElementPresent(driver,By.xpath("//div[text()='Consumption']"));
	boolean BillingItems=isElementPresent(driver,By.xpath("//div[text()='Billing Items']"));
	boolean Invoice=isElementPresent(driver,By.xpath("//div[text()='Invoice']"));
	boolean Groups=isElementPresent(driver,By.xpath("//div[text()='Groups']"));
	boolean TechnicalParameterDefinition=isElementPresent(driver,By.xpath("//div[text()='Technical Parameter Definition']"));
	boolean TariffCategories=isElementPresent(driver,By.xpath("//div[text()='Tariff Categories']"));
	boolean ClientInformation=isElementPresent(driver,By.xpath("//div[text()='Client Information']"));
	boolean TariffLines=isElementPresent(driver,By.xpath("//div[text()='Tariff Lines']"));
	boolean LookUpTableData=isElementPresent(driver,By.xpath("//div[text()='Look Up Table Data']"));
	Assert.assertTrue(SiteInformation);
	Assert.assertTrue(SiteTechnicalParameters);
	Assert.assertTrue(Consumption);
	Assert.assertTrue(BillingItems);
	Assert.assertTrue(Invoice);
	Assert.assertTrue(Groups);
	Assert.assertTrue(TechnicalParameterDefinition);
	Assert.assertTrue(TariffCategories);
	Assert.assertTrue(ClientInformation);
	Assert.assertTrue(TariffLines);
	Assert.assertTrue(LookUpTableData);
	
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl09$ctl01']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl10$ctl07']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl08$ctl05']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl07$ctl10']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl18$ctl10']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl15$ctl08']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl09$ctl02']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl12$ctl08']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl13$ctl02']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl17$ctl04']"));
	Thread.sleep(1000);
	safeClick(driver,By.xpath("//a[text()='Save']"));
		
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

