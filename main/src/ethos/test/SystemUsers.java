package ethos.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SystemUsers extends CommonUtils{

	
	@BeforeClass
	public void startBrowser() throws Exception {	
		openUrl(cachedProperties.value("Ethos_url"));
		login( "madhva", "madhva");
		
	}
	
	@BeforeMethod()
	public void init() throws Exception
	{
		goToSystemUsersPage();
	}

	
	@Test
	public void verifyHeading() throws Exception
	{
		assertTrue(driver.findElement(By.id("ctl00_lblTitle")).getText().contains("Users"));
	}

	@Test
	public void verifyRoleDropDown() throws Exception
	{
		assertTrue("Role Drop down doesn't exist",elementVisible(driver,By.id("ctl00_cphMainContent_ddlRole"),3));
	}
	
	@Test
	public void verifyUserIdSearch() throws Exception
	{
		assertTrue("UserId search Not available",elementVisible(driver,By.id("ctl00_cphMainContent_txtUserID"),3));
	}
	
	@Test
	public void verifyResetBtn() throws Exception
	{
		assertTrue("Reset Button Not available",elementVisible(driver,By.id("ctl00_cphMainContent_btnResetFilter"),3));
	}
	
	@Test
	public void verifyTablePresent() throws Exception
	{
		assertTrue("User Table Not available",elementVisible(driver,By.id("ctl00_cphMainContent_gvUser"),3));
	}
	
	@Test
	public void verifySelectLink() throws Exception
	{
		//"table#ctl00_cphMainContent_gvCountry tr:nth-of-type("+(i+1)+") td:nth-of-type("+i+") a"
		for(int i=1;i<=10;i++)
			assertTrue("Select not available at "+ i,elementVisible(driver, By.xpath("//table[@id='ctl00_cphMainContent_gvUser']//tr["+(i+1)+"]//td[1]/a[text()='Select']"), 1));
		safeClick(driver, By.linkText("Select"));
		elementVisible(driver, By.id("__lblctl00_cphMainContent_txtID"), 5);
		assertTrue("Page not moved to Users page",driver.findElement(By.id("ctl00_lblTitle")).getText().contains("View User"));
	}
	
	@Test
	public void verifyExportLink() throws Exception
	{
		elementVisible(driver, By.linkText("Export"), 5);
		safeClick(driver,By.linkText("Export"));
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void verifyAddNewBtn() throws Exception
	{
		safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
		assertTrue("Popup didn't appear after clicking Add new",elementVisible(driver, By.id("ctl00_pnlMain"), 5));
		safeClick(driver, By.id("ctl00_cphMainContent__btnCancel"));
	}


}
