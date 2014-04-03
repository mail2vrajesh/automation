package com.domain;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.common.ErrorPageException;
import com.common.FrameworkCommon;
public class MetricsDomainWraper extends FrameworkCommon {
	 public HashMap < String, String > loc = Metrics_readValuesFromCSV();
	 
	public void metricsLogin(RemoteWebDriver driver, String Username, String Password)
			throws Exception, InterruptedException, ErrorPageException {
	     safeType(driver, By.cssSelector("input[type=\"text\"]"), Username);
		 safeType(driver, By.cssSelector("input[type=\"password\"]"), Password);
		 safeClick(driver, By.xpath("//span[text()='Sign in']"));
				
			}

	public void gotoSubMenu(RemoteWebDriver driver, String menu, String subMenu,
			String title) throws Exception {
		Thread.sleep(1000);
		//driver.navigate().refresh();
				    Actions actions = new Actions(driver);
					WebElement mainmenu = driver.findElement(By.xpath(menu));
					WebElement submenu = driver.findElement(By.xpath(subMenu));
			       	actions.moveToElement(mainmenu).moveToElement(submenu).click().build().perform();	
			       waitTitle(driver, title,20);
			   	Thread.sleep(1000);
			   	
			   
			   	
			}

	public void simpleFilter(RemoteWebDriver driver,String filterName, String byText) {
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-plusthick")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'"+filterName+"')]")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(byText);
	    driver.findElement(By.xpath("//a[contains(text(),'"+byText+"')]")).click();
	}
	
	public void alertBox(RemoteWebDriver driver) throws InterruptedException {
		boolean isPresent=isElementPresent(driver,By.xpath("//div [@tabindex='-1']"));
		if(isPresent==true){
			Thread.sleep(1000);
			System.out.println("trace143");
			String errortest=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
			driver.findElement(By.xpath("//span [text()='Ok']")).click(); 
			System.out.println(errortest);
			}
		else {
			Thread.sleep(1000);
					}
	}

	public void findByList(RemoteWebDriver driver,String name, String tablename, String Edit)
			throws InterruptedException {
				String msg="ElementNOtPresented";	
				while (msg.equals("ElementNOtPresented")){	
					WebElement table = driver.findElement(By.xpath(tablename));
					List<WebElement> rows = table.findElements(By.tagName("li"));
					for (WebElement row: rows){
						System.out.println(row.getText()+"==="+name); 
						if (row.getText().contains(name)){
							//if name is successfully added go and click edit button
							msg="EleementPresent";
							row.click();
							Thread.sleep(1000);
							row.findElement(By.xpath(Edit)).click();
								break ;
							}
						}
					if (msg.equals("ElementNOtPresented")){
						driver.findElement(By.linkText("Next")).click();
					Thread.sleep(1000);	
						}
					}
			}


}
