package com.common;

import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ErrorCheckThread extends FrameworkCommon implements Runnable{
	
	private RemoteWebDriver driver;
	AtomicBoolean jobDone = new AtomicBoolean(false);
	
	public ErrorCheckThread(RemoteWebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void run() {
		String partialTitle = "";
		try {
			while(!(jobDone.get())) {
				//check if search sector appears, i.e SRP loaded again due to some error
				if(elementPresent(driver, By.xpath("//*[@id='GlobalNav']/div/div[2]/div/strong"), 3)) {
					partialTitle = safeGetText(driver, By.xpath("//*[@id='GlobalNav']/div/div[2]/div/strong"));
					if(partialTitle!=null && !partialTitle.isEmpty() && !(jobDone.get())) {
						jobDone.compareAndSet(false, true);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void gotoSubMenu(RemoteWebDriver driver, String menu, String subMenu,
			String title) throws Exception {
				Actions actions = new Actions(driver);
					WebElement mainmenu = driver.findElement(By.xpath(menu));
					WebElement submenu = driver.findElement(By.xpath(subMenu));
			       	actions.moveToElement(mainmenu).moveToElement(submenu).click().build().perform();;	
			       	Thread.sleep(3000);
			    	String actualTitle =driver.getTitle();
					//String expectedTitle="Library Settings";
					Assert.assertEquals(actualTitle,title);
			}

}
