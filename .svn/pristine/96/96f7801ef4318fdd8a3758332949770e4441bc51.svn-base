package metrics.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class down_Up_XL extends MetricsDomainWraper  {
	public RemoteWebDriver driver = null;


	@BeforeClass
	public void startSelenium() throws Exception {	
		File file = new File("exe\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		//driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}



	@Test 
	public  void downloadUpload() throws Exception{
		getApp(driver,cachedProperties.value("Metrics_url"),"Login");
		metricsLogin(driver, "Metrics", "Metrics");
		gotoSubMenu(driver, "//a[text()='Contracts']", "//a[text()='Site Setup']", "Site Setup");
		/*System.out.println("1");
			Actions actions =new Actions(driver);	
			Thread.sleep(3000);
				WebElement mainmenu = driver.findElement(By.xpath("//a[text()='Contracts']"));
				WebElement submenu = driver.findElement(By.xpath("//a[text()='Site Setup']"));
				actions.moveToElement(mainmenu).moveToElement(submenu).click().build().perform();


				Thread.sleep(3000);*/
		//getApp(driver,"http://192.168.185.43/Metrics/?MP=3.1","Site Setup");

		//driver.findElement(By.xpath("//a[text()='Download Template']")).click();
		System.out.println("2");
		DeleteFile("Upload_Client_Blank.xlsx");
		Downloader(driver, "//a[text()='Download Template']");
//
		//readDataFromXL("Upload_Client_Blank.xlsx");


		//importXL(driver,"ctl08_ctl05_fileImport_fileImportNewFactor","ctl08_ctl05_fileImport_lbnImportNewFactors","C:\\Users\\Balaraman\\Desktop\\Metrics\\excels\\Upload_Client_Blank.xlsx");


	}}	

