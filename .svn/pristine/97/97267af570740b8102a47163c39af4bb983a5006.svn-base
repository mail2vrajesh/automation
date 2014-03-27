package metrics.test.systemAdmin;

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

public class BureauRolePermission extends MetricsDomainWraper{
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
	public void verifyTheValidationsInRoleKeyManageMent() throws Exception{
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
		safeClick(driver,By.xpath("//a[text()='Save']"));
		
	}
	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
		}
	
	}

