package ethos.volumedata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.common.FrameworkCommon;

public class CommonUtils extends FrameworkCommon {
	public static RemoteWebDriver driver = null;

	public WebDriver getDriver() {
		if (driver == null) {
			File file = new File("exe\\IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities
					.internetExplorer();
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver(capability);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//	driver=new FirefoxDriver();
		}

		return driver;

	}

	public void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public ArrayList<String> getOptionsDropdown(By by)
	{
		//new Select(driver.findElement(By.xpath("//select[contains(@id,'ddlProductGroup')]"))).getOptions().get(1).getText()
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
	
	public void goToSummaryData() throws Exception
	{
		mouseMoveTo(By.linkText("Client"));
		mouseMoveTo(By.linkText("Volume"));
		mouseMoveTo(By.linkText("Volume"));
		mouseMoveTo(By.linkText("Volume Data Totals"));
		mouseMoveTo(By.linkText("Volume Summary Data"));
		mouseMoveTo(By.linkText("Add"));
		safeClick(driver, By.linkText("Add"));
		
	}
	
	public void selectDropDown(By locator,String visibleText)
	{
		new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
	}

	public void jsClick(By locator)
	{
		
	}
	
	public void fillDropDownsVolumeSummaryData()
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Electricity");
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
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "Actual");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
}
