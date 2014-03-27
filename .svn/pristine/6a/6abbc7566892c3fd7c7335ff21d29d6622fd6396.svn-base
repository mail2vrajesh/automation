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
	 
	
	
	

}
