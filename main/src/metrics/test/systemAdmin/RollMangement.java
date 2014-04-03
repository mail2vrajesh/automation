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

public class RollMangement extends MetricsDomainWraper{
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
	
	//Role management screen
	//verify the roll management give the new role name 
	@DataProvider
	public Object [ ][ ] RoleNames() {
		return new Object [ ] [ ] { {"Metrics223"}};
		}
	//create the role name in role management
@Test(dataProvider="RoleNames")
	public void createTheRoleManageMentName(String roleName) throws Exception{
	gotoSubMenu(driver,"//a[text()='System Admin']","//a[text()='Access Control']","User Management");
		safeClick(driver,By.xpath("//a[text()='Role Management']"));
		//click the role + button
		Thread.sleep(1000);
		safeClick(driver,By.xpath("//*[@id='ctl08_ctl03_gridRoles_btnDisplayNew']/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridRoles$ctl01']"), roleName);
		safeClick(driver,By.xpath("//div[2]/span"));
		alertBox(driver);
		}
	//verify the role name is successfully added or not
@Test(dataProvider="RoleNames",dependsOnMethods={"createTheRoleManageMentName"})
	public void verifyTheuserNameinRoleManageMent(String roleName) throws Exception{
		safeClick(driver,By.xpath("//a[text()='System Admin']"));
		safeClick(driver,By.xpath("//a[text()='Role Management']"));
		findByList(driver, roleName,"//ul[@id='ctl08_ctl03_gridRoles_grid']","//div[2]/span[1]");
				}
	//create the user name in roles role management
	@DataProvider
	public Object [ ][ ] UserNameInRoles() {
		return new Object [ ] [ ] { {"Metrics1"}};
		}
@Test(dataProvider="UserNameInRoles",dependsOnMethods={"verifyTheuserNameinRoleManageMent"})
	public void CreateUsernameinRollmangement(String username) throws Exception{
		//create The one new username in roles
		safeClick(driver,By.xpath("//*[@id='ctl08_ctl03_gridUsers_btnDisplayNew']/span"));
		safeType(driver,By.xpath("//input[@name='ctl08$ctl03$gridUsers$ctl01']"), username);
	//	Thread.sleep(1000);
		safeClick(driver,By.xpath("//div[2]/span"));
		alertBox(driver);
		}
	
  //weShould give avilable rolename
  //Role key MangeMent
	@Test(dataProvider="RoleNames",dependsOnMethods={"CreateUsernameinRollmangement"})
	public void selectRoleKeyMangeMent(String rolekeyname) throws Exception{
		safeClick(driver,By.xpath("//a[text()='System Admin']"));
		safeClick(driver,By.xpath("//a[text()='Role Key Management']"));
		findByList(driver, rolekeyname, "//*[@id='ctl08_ctl03_keyRoleCtl_gridRoles_grid']","//div[2]/span");
		}
	//verify The validations of RoleKeyMangement
@Test(dependsOnMethods="selectRoleKeyMangeMent")
    public void verifyTheValidationsInRoleKeyManageMent(){
	boolean Role=isElementPresent(driver,By.xpath("//div[text()='Role']"));
	boolean System=isElementPresent(driver,By.xpath("//div[text()='System']"));
	boolean UserInfo=isElementPresent(driver,By.xpath("//div[text()='User Info']"));
	boolean UserRole=isElementPresent(driver,By.xpath("//div[text()='User Role']"));
	boolean Write=isElementPresent(driver,By.xpath("//div[text()='Write']"));
	boolean Read=isElementPresent(driver,By.xpath("//div[text()='Read']"));
	boolean Admin=isElementPresent(driver,By.xpath("//div[text()='Admin']"));
	Assert.assertTrue(Role);
	Assert.assertTrue(System);
	Assert.assertTrue(UserInfo);
	Assert.assertTrue(UserRole);
	Assert.assertTrue(Write);
	Assert.assertTrue(Read);
	Assert.assertTrue(Admin);
	
	
}
//verifyThe Permeations in RoleKeyManageMent
@Test(dependsOnMethods="verifyTheValidationsInRoleKeyManageMent")
public void verifyThePermeations() throws Exception{
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl01$ctl04']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl01$ctl01']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl01$ctl07']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl03$ctl05']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl04$ctl04']"));
	safeClick(driver,By.xpath("//input[@name='ctl08$ctl03$keyRoleCtl$rptKey$ctl03$ctl02']"));
	safeClick(driver,By.xpath("//a[text()='Save']"));
}


//

	
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

