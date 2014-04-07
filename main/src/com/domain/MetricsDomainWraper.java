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
		/*while(textPresent(driver, "access permission", 3)){
			 safeClick(driver, By.xpath("//div[@id='divList']/div/div/div[2]/div[4]/div"));
				metricsLogin(driver, cachedProperties.value("Metrics_username"), cachedProperties.value("Metrics_password"));
		 }*/
		Thread.sleep(1000);
		//driver.navigate().refresh();
				    Actions actions = new Actions(driver);
					WebElement mainmenu = driver.findElement(By.xpath(menu));
					WebElement submenu = driver.findElement(By.xpath(subMenu));
			       	actions.moveToElement(mainmenu).moveToElement(submenu).click().build().perform();	
			       waitTitle(driver, title,20);
			   	Thread.sleep(1000);
			   	
			   
			   	
			}

	public void simpleFilter(RemoteWebDriver driver,String filterName, String byText) throws Exception {
	
		if(elementPresent(driver, By.xpath("//div[contains(@class,'grouptype-box limitWidth')]"), 3)){
			Actions actions = new Actions(driver);
			WebElement mainmenu = driver.findElement(By.xpath("//div[contains(@class,'grouptype-box limitWidth')]"));
			
	       	actions.moveToElement(mainmenu).build().perform();
	      //div[@id='ctl08_ctl02_groupfilter']/div/div/div/div[5]/span
	       /*	WebElement sub = driver.findElement(By.xpath("//div[@id='ctl08_ctl02_groupfilter']/div/div/div/div[5]/span"));
	       	actions.moveToElement(sub).click();*/
	       	safeClick(driver, By.xpath("//div[@id='ctl08_ctl02_groupfilter']/div/div/div/div[5]/span"));
	       	
	     
	      	Thread.sleep(5000);	
		}else{
		System.out.println("no filter");
		}
	
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-plusthick"));
		safeClick(driver, By.xpath("//a[contains(text(),'"+filterName+"')]"));
		safeType(driver, By.xpath("(//input[@type='text'])[4]"), byText);
		safeClick(driver, By.xpath("//a[contains(text(),'"+byText+"')]"));

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
	
	
	public void dateFilter(RemoteWebDriver driver, String fDate, String TDate) throws Exception {
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-triangle-1-s"));
safeType(driver, By.xpath("//div[2]/div[1]/input"), fDate);
safeType(driver, By.xpath("//div[2]/input"), TDate);
safeClick(driver, By.xpath("//a[@id='ctl08_ctl04_lbnSave']/span"));

	}
	
	public void getData(RemoteWebDriver driver,String tablepath,String rowpath,String values) {
		WebElement table=driver.findElement(By.xpath(tablepath));
		List<WebElement> rows1=table.findElements(By.tagName(rowpath));
		for(WebElement row:rows1){
			 values=row.getText();
		}
	}

}
