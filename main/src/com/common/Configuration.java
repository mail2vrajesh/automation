// #########-----------------------------------------------------------------------##########
// ~ Selenium Automation Framework
// ~ Purpose:
// ~ Usage:
// ~ Create By: Faisal and Kiran
// ~ Email:
//#########-----------------------------------------------------------------------##########
package com.common;

import java.io.File;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;


public class Configuration extends FrameworkDeclaration {
RemoteWebDriver driver;
	
	@BeforeSuite
	public void setup() {
		
		File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
		DesiredCapabilities capability = DesiredCapabilities
				.internetExplorer();
		capability
		.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver(capability);
		// TODO Auto-generated method stub

	}
    

}
