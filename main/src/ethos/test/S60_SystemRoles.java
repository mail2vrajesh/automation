package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.domain.ETHOSDomainWraper;

public class S60_SystemRoles extends ETHOSDomainWraper{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToSystemRolesPage();
	}

	
	@Test
	public void S60_1_verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Roles"));
	}

	@Test
	public void S60_2_verifyTablePresent() throws Exception
	{
		assertTrue("User Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvRole"),3));
	}
	
	@Test
	public void S60_3_verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvRole']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		safeClick(driver, By.linkText("Select"));
		elementVisible(driver, By.id("__lblctl00_cphMainContent_txtID"), 5);
		assertTrue("Page not moved to Users page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View Role"));
	}
	
	@Test
	public void S60_4_verifyExportLink() throws Exception
	{
		elementVisible(driver, By.linkText("Export"), 5);
		safeClick(driver,By.linkText("Export"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void S60_5_verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Add Role"));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnSave"), 3));
		assertTrue(elementVisible(driver, By.id("ctl00_cphMainContent__btnCancel"), 3));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	
	}


}
