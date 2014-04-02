package com.domain;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
	
	//During the code refactoring all the navigations would be deleted and only the following method will be used.
	public void navigateToProductGroup(RemoteWebDriver driver, String productId, String productGroup)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			
			int noOfRows;
			WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvProductGroup>tbody"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			noOfRows = rows.size(); 
		    
			String actualProdId;
			String cssId;
			int iterator = 2;
		    for(iterator=2; iterator<noOfRows; iterator++) {
		    	cssId = "#ctl00_cphMainContent_gvProductGroup>tbody>tr:nth-child("+iterator+")>td:nth-child(2)";
		    	actualProdId = safeGetText(driver, By.cssSelector(cssId));
		    	if (actualProdId.equals(productId))
		    		break;
		    }
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr["+iterator+"]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
			safeClick(driver, By.linkText(productGroup));
	}
	
	//During the code refactoring all the navigations would be deleted and only the following method will be used.
	public void navigateToScreen(RemoteWebDriver driver, String screen, String subScreen)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText(screen));
			safeClick(driver, By.linkText(subScreen));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
	}
	
	public void navigateToProductionGroup(RemoteWebDriver driver, String text)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
						
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
			safeClick(driver, By.linkText(text));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver, String text)
			throws InterruptedException, ErrorPageException, Exception {
			int noOfRows;
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
	
	public int returnRowNumber(RemoteWebDriver driver, String id, String pattern, int column)
			throws InterruptedException, ErrorPageException, Exception {
			int iterator = 2;
			int noOfRows;
			
			String actualPattern = "";
			String cssId = "";
			
			WebElement table = driver.findElement(By.cssSelector(id));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			noOfRows = rows.size(); 
		    System.out.println("Rowcount "+noOfRows);
		    for (iterator = 2; iterator < (noOfRows-2); iterator++ )
		    {
		    	cssId = id+">tr:nth-child("+iterator+")>td:nth-child("+column+")";
		    	actualPattern = safeGetText(driver, By.cssSelector(cssId));
		    	if (pattern.contains(actualPattern))
		    		break;
		    }
		    
		    if (iterator == (noOfRows-2))
		    	iterator = 2;
		    
		    return iterator;
	}
	
	
}
