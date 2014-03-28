package com.domain;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.common.ErrorPageException;
import com.common.FrameworkCommon;
public class ETHOSDomainWraper extends FrameworkCommon {
	 public HashMap < String, String > loc = Metrics_readValuesFromCSV();

	public void ethosLogin(RemoteWebDriver driver,String username, String Password)
			throws InterruptedException, ErrorPageException, Exception {
				elementPresent(driver, By.id("ctl00_imgJHA"), 10);
				safeType(driver, By.id("ctl00_cphMainContent_ctrlLogin_UserName"), username);
				safeType(driver, By.id("ctl00_cphMainContent_ctrlLogin_Password"), Password);
				safeClick(driver, By.id("ctl00_cphMainContent_ctrlLogin_LoginButton"));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver, String text)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
			safeClick(driver, By.linkText(text));
	}
	
	public void navigateToConversionFactors(RemoteWebDriver driver) 
			throws InterruptedException, ErrorPageException, Exception {
			
				navigateToProductionGroupDestination( driver);
				safeClick(driver, By.linkText("Conversion Factors"));
	}
	
	public void navigateToIntervalDataRules(RemoteWebDriver driver) 
			throws InterruptedException, ErrorPageException, Exception {
			
				navigateToProductionGroupDestination( driver);
				safeClick(driver, By.linkText("Interval Data Rules"));
	}
	
	public void navigateToScheduledTasks(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("Data Management"));
			safeClick(driver, By.linkText("Scheduled Tasks"));
	}
	
	public void navigateToCostToServe(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("Data Management"));
			safeClick(driver, By.linkText("Cost To Serve Rates"));
	}
	
	public void navigateToProductionGroupForGas(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[5]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
	}
	
	public void navigateToProductionGroupForGas(RemoteWebDriver driver, String text)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToProductionGroupForGas(driver);
			safeClick(driver, By.linkText(text));
	}

}
