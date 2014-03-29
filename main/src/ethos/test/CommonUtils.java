package ethos.test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.common.FrameworkCommon;

public class CommonUtils extends FrameworkCommon {
	public static RemoteWebDriver driver = null;

	@BeforeClass
	public void initClass()
	{
		getDriver();
	}
	@AfterClass
	public void destroyClass()
	{
		if(driver!=null)
		{
			driver.close();
			driver.quit();
		}
	}
	@BeforeMethod
	public void testSetup(Method method) throws Exception {
		System.out.println("**************** Starting test: " + method.getName()
				+ " ****************");
	}
	public WebDriver getDriver() {
		if (driver == null || driver.getSessionId()==null) {
			/*File file = new File("exe\\IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities
					.internetExplorer();
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());*/
			/*driver = new InternetExplorerDriver(capability);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			driver=new FirefoxDriver();
		}

		return driver;

	}

	public void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public ArrayList<String> getOptionsDropdown(By by)
	{
		List<WebElement> l= new Select(driver.findElement(by)).getOptions();
		ArrayList<String> ar=new ArrayList<String>();
		for(WebElement e: l)
			if(e.getText().length()>0 && !e.getText().toUpperCase().contains("SELECT"))
				ar.add(e.getText());
		return ar;
	}

	public void login(String Username, String Password) throws Exception {
		safeType(driver, By.cssSelector("input[type=\"text\"]"), Username);
		safeType(driver, By.cssSelector("input[type=\"password\"]"), Password);
		safeClick(driver, By.xpath("//input[contains(@name,'LoginButton')]"));

	}

	public void goToVolumeDataPage() throws Exception {
		
		safeClick(driver, By.linkText("Client"));
		safeClick(driver, By.linkText("Volume"));
		safeClick(driver, By.linkText("Volume Data Totals"));
	}
	
	public void mouseMoveTo(By locator)
	{
		 Actions builder = new Actions(driver);
		 Action move=builder.moveToElement(driver.findElement(locator)).build();
		 move.perform();
	}

	public void goToPage(String[] links) throws Exception
	{
		for(String link:links)
			mouseMoveTo(By.linkText(link));
		safeClick(driver, By.linkText(links[links.length-1]));
	}
	public void goToSummaryData() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Volume Summary Data","Add"});
			
	}
	public void goToCountriesPage() throws Exception
	{
		goToPage(new String[]{"System","Country","Countries"});
	}
	public void goToSystemUsersPage() throws Exception
	{
		goToPage(new String[]{"System","Security","Users"});
		
	}
	
	public void goToCountryZonesPage() throws Exception
	{
		goToPage(new String[]{"System","Country","Countries","Zone","Zones"});

	}
	
	public void goToVolumeDataShapeOut() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Shape Output"});
	}
	
	public void goToVolumeDataIntervalCheck() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Check"});
	}
	public void goToVolumeDataRemoval() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Remove"});
	}
	
	
	public void selectDropDown(By locator,String visibleText)
	{
		new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
	}

	public void jsClick(By locator)
	{
		
	}
	
	public void selectElectricityProducts()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
		waitForPageLoaded(driver);
	}
	public void fillDropDownsVolumeSummaryData()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "BTMonthlyRawData");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
	public void fillDropDownsForGas()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "Actual");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
	
	
	public void safeCheck(By locator)
	{
		if(driver.findElement(locator).getAttribute("checked")==null)
			driver.findElement(locator).click();
	
	}
}
