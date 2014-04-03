package com.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.base.Function;

public class FrameworkCommon extends FrameworkDeclaration {


	public static RemoteWebDriver driver = null;

	public RemoteWebDriver getDriver(String Browser) {
		if (driver == null) {

			if(Browser.contains("IE")){
				File file;if(getBit().contains("64")){file = new File("exe\\IEDriverServer64.exe");}else{file = new File("exe\\IEDriverServer32.exe");}
				DesiredCapabilities capability = DesiredCapabilities
						.internetExplorer();
				capability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver(capability);
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}else if (Browser.contains("firefox")){
				driver=new FirefoxDriver();
			}else{
				System.out.println("Chk config Browser");
			}


		}

		return driver;

	}

	public Boolean fluentWait(WebDriver driver, final By locator) {


		//WebDriverWait wd;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		Boolean foo = wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(locator).isDisplayed();
			}
		});

		return foo;
	};

	public void waitForPageLoaded(WebDriver driver) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			// TODO Problem with Remote Web Driver
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver, 1200);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			assertFalse("Timeout waiting for Page Load Request to complete.",true);
		}
	}

	public void safeClick(WebDriver driver, By by) throws Exception {
		boolean element = elementVisible(driver, by, 10);
		WebElement we = driver.findElement(by);
		if (element) {
			if(elementPresent(driver, by, 100))

				we.click();
		} else {
			Reporter.log("Element "+by+"is not displayed in "+driver.getCurrentUrl());
		}
	}

	public boolean elementVisible(WebDriver driver, By by, int Time)
			throws Exception, ErrorPageException {
		boolean element_withDelay = false;

		int second = 0;
		for (second = 0; second < Time; second++) {
			try {

				if (driver.findElement(by).isDisplayed() == true) {
					element_withDelay = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		return element_withDelay;
	}

	public boolean elementPresent(WebDriver driver, By by, int Time)
			throws InterruptedException, ErrorPageException {
		boolean element_withDelay = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {

				WebElement we = driver.findElement(by);
				if (we != null) {
					element_withDelay = true;


					return element_withDelay;
				}
			} catch (NoSuchElementException e) {
				if (driver.getTitle().trim().toLowerCase().contains("error")) {
					throw new ErrorPageException();
				}
				Thread.sleep(1000);
			}
		}

		return element_withDelay;
	}

	public void safeType(WebDriver driver, By by, String text)
			throws Exception {
		boolean element = elementVisible(driver, by, 50);
		if (element) {
			elementPresent(driver, by, 20);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(text);
		} else {
			Reporter.log("Not able to type text. Error occurred");
			assertTrue(false);
		}
	}


	public void safeSelect(WebDriver driver, By by, String value)
			throws NoSuchElementException, InterruptedException,
			ErrorPageException {
		if (elementPresent(driver, by, 50)) {
			Select select = new Select(driver.findElement(by));
			select.selectByValue(value);
		} else {
			Reporter.log("Unable to select expected option");
			assertTrue(false);
		}
	}

	public void safeSelectByText(WebDriver driver, By by, String value)
			throws Exception {
		if (elementVisible(driver, by, 50)) {
			Select select = new Select(driver.findElement(by));
			select.selectByVisibleText(value);
		} else {
			Reporter.log("Unable to select expected option");
			assertTrue(false);
		}
	}

	public boolean elementNotVisible(WebDriver driver, By by, int Time)
			throws Exception {
		boolean element_withDelay = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if (!driver.findElement(by).isDisplayed()) {
					element_withDelay = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		return element_withDelay;
	}



	public boolean textPresent(WebDriver driver, String text, int Time)
			throws InterruptedException {
		boolean textFlag = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if ((driver.getPageSource().contains(text))) {
					textFlag = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		return textFlag;
	}

	public boolean isEmptyField(WebDriver driver, By by) {
		return driver.findElement(by).getAttribute("value").isEmpty();
	}

	public String safeGetText(WebDriver driver, By by) throws Exception {
		boolean element = elementVisible(driver, by, 10);
		if (element) {
			elementPresent(driver, by, 5);
			return driver.findElement(by).getText().trim();
		}
		return null;
	}

	public void waitTitle(WebDriver driver, String title, int time)
			throws Exception {
		for (int second = 0; second < time; second++) {
			try {
				if (title.equalsIgnoreCase(driver.getTitle())) {
					break;
				}
			} catch (Exception e) {

			}
			Thread.sleep(1000);
		}
	}

	public void clickLink(WebDriver driver, String linkText) throws Exception {

		try {
			safeClick(driver, By.linkText(linkText));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void screenshot(ITestResult result, WebDriver driver)
			throws Exception {
		String filepath = "";
		String testName = result.getName();
		int Status = result.getStatus();
		if (Status == 2) {
			File file = new File(".");
			String filename = testName + putLogDate() + ".png";

			filepath =file.getCanonicalPath() + "\\ScreenShots\\" + filename;
			System.out.println("Test status of "+testName+" is " + Status);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filepath));
			Reporter.log("<a href'" + filepath + "'>screenshot</a>");
			Reporter.log("Screenshot Name :" + filename);
		}
	}

	public void getSnapshot(WebDriver driver) throws Exception {
		File file = new File(".");
		StackTraceElement e[] = Thread.currentThread().getStackTrace();
		String functionCallingName = e[1].getMethodName();
		String fileName = functionCallingName + putLogDate() + ".png";
		String filePath = file.getCanonicalPath() + "/ScreenShots/" + fileName;
		WebDriver augDriver = new Augmenter().augment(driver);
		File snapShot = ((TakesScreenshot) augDriver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(snapShot, new File(filePath));
		Reporter.log("<a href'" + filePath + "'>screenshot</a>");
		Reporter.log("Snapshot Name : " + fileName);
	}

	public String logURL(WebDriver driver) {
		String url = driver.getCurrentUrl();
		Reporter.log(url);
		return url;
	}

	public static String cancelPopupMessageBox(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}

		return message;
	}

	public boolean isElementPresent(WebDriver driver,By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void getApp(WebDriver driver,String url, String title) throws Exception {
		driver.get(url);
		driver.manage().window().maximize();
		waitTitle(driver, title, 60);

	}

	public void startInternetExplorer(WebDriver driver) {
		File file = new File("exe\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath() ); 
		//driver = new InternetExplorerDriver(capability);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}


	// col is for start from which col
	//row is for which row to read
	// eachrowcount is to get the count of col for given row
	public  String readDataFromXL(String filename,int col, int row, int eachcolcount)
			throws FileNotFoundException, IOException,  Exception {
		FileInputStream fs=new FileInputStream ("outputData\\"+filename);	
		Workbook wb = WorkbookFactory.create(fs);
		int rowCount=getExcelColulmnCount("Sheet1","outputData\\"+filename,eachcolcount);
		String[] XLdata =new String[rowCount];		
		//System.out.println(rowCount);
		for (int j=col;j<rowCount; j++){
			XLdata[j]=wb.getSheet("Sheet1").getRow(row).getCell(j).getStringCellValue();
			System.out.println(XLdata[j]);
		}

		StringBuilder builder = new StringBuilder();
		for(String s : XLdata) {
			builder.append(s);
		}
		return builder.toString();
	}


	public  String readDataFromXLLimitCol(String filename,int col, int row, int eachcolcount, int rowCount)
			throws FileNotFoundException, IOException, Exception {
		FileInputStream fs=new FileInputStream ("outputData\\"+filename);	
		Workbook wb = WorkbookFactory.create(fs);
		//int rowCount=getExcelColulmnCount("Sheet1","outputData\\"+filename,eachcolcount);
		String[] XLdata =new String[rowCount];		
		//System.out.println(rowCount);
		for (int j=col;j<rowCount; j++){
			XLdata[j]=wb.getSheet("Sheet1").getRow(row).getCell(j).getStringCellValue();
			System.out.println(XLdata[j]);
		}

		StringBuilder builder = new StringBuilder();
		for(String s : XLdata) {
			builder.append(s);
		}
		return builder.toString();
	}





	public String getFile(String Startwith) {

		File folder = new File("outputData\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				String file =listOfFiles[i].getName();
				if(file.startsWith(Startwith)){
					return file;
				}

			} 
		}
		return null;

	}

	public  void Downloader(RemoteWebDriver driver, String locator)

			throws InterruptedException, AWTException {
		driver.findElement(By.id(locator)).click();			
		Thread.sleep(2000);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	public  void eDownloader(WebDriver driver, String locator)
			throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(locator)).click();			
		Thread.sleep(3000);
		Robot robot=new Robot();
		if(cachedProperties.value("ethosbrowser").contains("firefox")){
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_ENTER);
		}else if (cachedProperties.value("ethosbrowser").contains("IE")){
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
		}else{
			System.out.println("browser not supported ");	
		}
	}

	public  void eDownloader(WebDriver driver, String locator, int delay)
			throws InterruptedException, AWTException {
		driver.findElement(By.cssSelector(locator)).click();			
		Thread.sleep(delay);
		Thread.sleep(delay);
		Robot robot=new Robot();			
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	public  void cancelDownloader(WebDriver driver, String locator)
			throws InterruptedException, AWTException {
		driver.findElement(By.id(locator)).click();			
		Thread.sleep(3000);
		Robot robot=new Robot();								
		robot.keyPress(KeyEvent.VK_ENTER);	
	}

	public  int getExcelColulmnCount(String SheetName, String filename, int row) {
		try{		
			FileInputStream fs = new FileInputStream (filename);
			Workbook wb = WorkbookFactory.create(fs);
			return wb.getSheet(SheetName).getRow(row).getLastCellNum();
		}
		catch(Exception e){
			return 0;
		}
	}


	public void DeleteFile(String FileName) {
		try {
			File file = new File("outputData\\"+FileName);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("file not found or kept opened.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void acceptAlert(WebDriver driver,String AlertMessage) throws InterruptedException {
		Thread.sleep(1000);
		for(int i=1;i<=10;i++){
			Alert alert= driver.switchTo().alert();
			String alertText=alert.getText();
			if(alertText.contains(AlertMessage)){
				System.out.println(alertText);
				alert.accept();
				break;
			}else{
				System.out.println("Alert not displayed");
			} 
		}
	}

	public void dismissAlert(WebDriver driver,String AlertMessage) throws InterruptedException {
		Thread.sleep(1000);
		for(int i=1;i<=10;i++){
			Alert alert= driver.switchTo().alert();
			String alertText=alert.getText();
			if(alertText.contains(AlertMessage)){
				System.out.println(alertText);
				alert.dismiss();
				break;
			}else{
				System.out.println("Alert not displayed");
			} 
		}
	}

	public static void importXL(WebDriver driver, String filePath) throws InterruptedException, IOException {
		File file = new File(".");
		String filepath =file.getCanonicalPath()+"\\inputData\\";
		System.out.println(filepath);
		driver.findElement(By.id("ctl08_ctl05_fileImport_fileImportNewFactor")).sendKeys(filepath+filePath);
		driver.findElement(By.id("ctl08_ctl05_fileImport_lbnImportNewFactors")).click();
	}



	public static void importXLValid(RemoteWebDriver driver, String filePath) throws InterruptedException, IOException {
		File file = new File(".");
		String filepath =file.getCanonicalPath()+"\\inputData\\";
		driver.findElement(By.id("ctl08_ctl03_fileImport_fileImportNewFactor")).sendKeys(filepath+filePath);
		driver.findElement(By.id("ctl08_ctl03_fileImport_lbnImportNewFactors")).click();

	}
	/*	public static void importXLLookup(RemoteWebDriver driver, String filePath) {
		driver.findElement(By.id("ctl08_ctl03_fileImport_fileImportNewFactor")).sendKeys(filePath);
		driver.findElement(By.id("ctl08_ctl03_fileImport_lbnImportNewFactors")).click();
	}*/

	public String safeGetSelectedValue(RemoteWebDriver driver, By by) throws Exception {
		boolean element = elementVisible(driver, by, 10);
		if (element) {
			Select combobox = new Select(driver.findElement(by));
			String getSelectedValue = combobox.getFirstSelectedOption().getText();
			return getSelectedValue;
		}
		return null;
	}

	//Bharath
	public boolean waitForPagetoLoad_Element(WebDriver driver, int timeout, By by)
	{
		return screenshot;
		/*wd = new WebDriverWait(driver, timeout);		
			WebElement element = driver.findElement(by);		
			return true;*/
	}

	public void PopUpWindowHandle(WebDriver driver,By pop_value ) throws IOException
	{
		String basewindow = driver.getWindowHandle();
		try {
			safeClick((RemoteWebDriver) driver,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[4]/td[4]/a"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			waitTitle((RemoteWebDriver) driver, " ETHOS ", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String popup = null;			
		java.util.Set<String> windowIterator = driver.getWindowHandles();
		if (windowIterator.size() == 2)
		{
			for (String WindowHandle : windowIterator)
			{
				if (!WindowHandle.equals(basewindow))		
				{
					popup = WindowHandle;
					driver.switchTo().window(popup);
				} 
			}
		}

	}
	public boolean dismissAlert(RemoteWebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		for(int i=1;i<=10;i++){
			Alert alert= driver.switchTo().alert();
			String alertText=alert.getText();
			if(alertText.contains("Message")){
				System.out.println(alertText);
				alert.dismiss();
				break;
			}else{
				System.out.println("Alert not displayed");
			} 
		}
		return true;
	}



	public String getBit()
	{

		String architecture = "os.arch";
		String bit =System.getProperty(architecture);
		System.out.println(bit);
		return bit;
	}


	public void  killIEInstances() throws IOException
	{
		File file = new File(".");
		String filepath =file.getCanonicalPath()+"\\resources\\";
		String path="cmd /c start "+filepath+"killIE.bat";
		Runtime rn=Runtime.getRuntime();
		Process pr=rn.exec(path);
		System.out.println(pr);

	}

	public void confirmDeleteOperation(RemoteWebDriver driver) throws Exception {

		safeType(driver, By.id("ctl00_cphMainContent__confirmControl_txtConfirm"), "DELETE");
		safeClick(driver, By.id("ctl00_cphMainContent__confirmControl_btnConfirm"));
		waitForPageLoaded(driver);
		Thread.sleep(60000);
	}

	public void selectFirstValueFromDropdown(RemoteWebDriver driver, By by ) throws Exception {
		if (elementVisible(driver, by, 50)) {
			Select select = new Select(driver.findElement(by));
			select.selectByIndex(1);
		} else {
			Reporter.log("Unable to select expected option");
			assertTrue(false);
		}
	}


}
