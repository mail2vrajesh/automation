package metrics.test.systemAdmin;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.domain.MetricsDomainWraper;

public class SystemAdmin extends MetricsDomainWraper {
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
getApp(driver,cachedProperties.value("Metrics_url"),"Login");
metricsLogin(driver,"Metrics","Metrics");
}

//To verify the Access Control Sub Options

@Test ()
public void verifyAccessControlOptions() throws Exception{
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
WebElement access=driver.findElement(By.xpath("//*[@id='nav-secondary']/li[1]"));
//get the sub Options in accessControl
Thread.sleep(1000);
List<WebElement> row=access.findElements(By.tagName("li"));
for(WebElement rows:row){
System.out.println(rows.getText());
}
}


//To create the New user with valid data
@DataProvider
public Object [ ][ ] NewUserlist() throws Exception {
return new Object [ ] [ ] { {"Metrics"+putuser(),"MetricsUser1","MetricsUser1","MetricsUser1@gmail.com",
            	             "MetricsFirstName","MetricsLasttName","123456789","080123456","EneryQuotes",
            	             "Testing","perminent","Bangalore","560063","india","03061995","note-nothing","20042020"}
};}
@Test(dataProvider="NewUserlist",dependsOnMethods={"verifyAccessControlOptions"})
public void newUserCreation(String username,String password,String confirmpassword,String email,String firstname,
String Lastname,String telephone,String mobile,String company,String department,String jobtitle,String address,
String postcode,String country,String dateofbirth,String note,String expiredate) throws Exception{
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
driver.findElement(By.xpath("//a[text()='Create New User']")).click();
Select sel = new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']")));
//verify the sub options in user dropdownList 
Thread.sleep(1000);
List<WebElement> li  = sel.getOptions();
for(WebElement options:li){
System.out.println(options.getText());
}
//Select the Web in Drop down list
new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"))).selectByVisibleText("Web");
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Username']"),username);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Password']"),password);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_RepeatPassword']"),confirmpassword);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Email']"),email);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_FirstName']"),firstname);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_LastName']"),Lastname);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Tel']"),telephone); 
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Mobile']"),mobile);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Company']"),company);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Department']"),department);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_JobTitle']"),jobtitle); 
safeType(driver,By.xpath("//textarea[@name='ctl08$ctl03$txtUser_Address']"),address); 
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Postcode']"),postcode);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_Country']"),country);
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_DateOfBirth']"),dateofbirth);
safeType(driver,By.xpath("//textarea [@name='ctl08$ctl03$txtUser_Note']"),note); 
safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_ExpireDate']"),expiredate); 
//click the save button
safeClick(driver,By.xpath("//a[text()='Save']"));
//This condition is if your run the script with out changing the data it will catch the message
alertBox(driver);
}
   
// verify the exiting user
//we should give already exiting user 
 @DataProvider
 public Object [ ][ ] ExitingUserlist() {
	 return new Object [ ] [ ] { {"MetricsUser"}};
 }

@Test(dataProvider="ExitingUserlist",dependsOnMethods={"newUserCreation"})
 public void duplicateUserValidation(String username) throws Exception{
	System.out.println("trace3");
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
	     safeClick(driver,By.xpath("//a[text()='Create New User']"));
	     new Select(driver.findElement(By.id("ctl08_ctl03_ddlUser_Type"))).selectByVisibleText("Web");
	    // safeSelect(driver,By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"),"Web");
		 driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Username']")).sendKeys(username);
		 driver.findElement(By.xpath("//a[text()='Save']")).click();
		alertBox(driver);
		 
}
//verify the empty user
//we should give already exiting user 
@Test(dependsOnMethods={"duplicateUserValidation"})
	public void emptyUserValidation() throws Exception{
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
  	  driver.findElement(By.xpath("//a[text()='Create New User']")).click();
  	  new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"))).selectByVisibleText("Web");
      driver.findElement(By.xpath("//a[text()='Save']")).click();
  	  alertBox(driver);
}
 //Verify whether IntelliSense functionality is working in User Name filed or not.
	
 @DataProvider
 public Object [ ][ ] IntelliSensefunctionality() {
	 return new Object [ ] [ ] { {"EthosDev"}};
 }
 @Test(dataProvider="IntelliSensefunctionality",dependsOnMethods={"emptyUserValidation"})
 public void verifywhetherIntelliSensefunctionality(String username) throws Exception{
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
     safeClick(driver,By.xpath("//a[text()='Create New User']"));
	 safeSelect(driver,By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"),"Win Active Dir.");
	 safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtADUser_Username']"), username); 
	 boolean nomatchfound =isElementPresent(driver,By.xpath("//div [text()='No match found']"));
	 boolean hasAlreadyBeenEnabled  =isElementPresent(driver,By.xpath("//div [text()='User account has already been enabled.']"));
	 boolean validatingNewUsername  =isElementPresent(driver,By.xpath("//div [text()='Validating new username...']"));
	 if(nomatchfound==true){
			 System.out.println("No match found");
	  }
	  else if(hasAlreadyBeenEnabled==true){
		 System.out.println("User account has already been enabled.");
	  }
	  
	  else if(validatingNewUsername==true){
		System.out.println("Validating new username...");

 }
}
 //verify the new username in select WinActiveDir from drop down list
// you should give valid userName
 @DataProvider
 public Object [ ][ ] UserlistWinActiveDir() {
	 return new Object [ ] [ ] { {"EthosDev","20092020"}};
 }
 //we should give new username
 @Test(dataProvider="UserlistWinActiveDir",dependsOnMethods={"verifywhetherIntelliSensefunctionality"})
 public void winActiveDirUserName(String username,String expiredate) throws Exception{
	 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
     safeClick(driver,By.xpath("//a[text()='Create New User']"));
	 safeSelect(driver,By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"),"Win Active Dir.");
	 safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtADUser_Username']"), username);
	 safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtUser_ExpireDate']"),expiredate);
	 safeClick(driver,By.xpath("//a[text()='Save']"));
	alertBox(driver);
 }
 
//verify the exiting username in select WinActiveDir from drop down list
//you should give exiting userName
 @DataProvider
 public Object [ ][ ] DuplicateUserlistWinActiveDir() {
	 return new Object [ ] [ ] { {"EthosDev"}};
 }          
 
	 @Test(dataProvider="DuplicateUserlistWinActiveDir",dependsOnMethods={"winActiveDirUserName"})
	 public void winActiveDirDuplicateUserName(String username) throws Exception{
		 gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Access Control']","User Management");
	     safeClick(driver,By.xpath("//a[text()='Create New User']"));
		 safeSelect(driver,By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"),"Win Active Dir.");
		 safeType(driver,By.xpath("//input[@name='ctl08$ctl03$txtADUser_Username']"), username);
		 driver.findElement(By.xpath("//a[text()='Save']")).click();
		 alertBox(driver);
}
		
/////LogMessages//////
// we sholud give valid log generic number
	@Test(dependsOnMethods={"winActiveDirDuplicateUserName"})
	 public void verifyTheLogMessageScreen() throws Exception{
		 String name="6450042";
	gotoSubMenu(driver, "//a[text()='System Admin']", "//a[text()='Log Messages']","Log Messages");

			driver.findElement(By.xpath("//input [@name='ctl08$ctl03$txtSearch']")).sendKeys(name);
			driver.findElement(By.xpath("//a [text()='Search']")).click();
			
			Boolean isPresent = driver.findElements(By.xpath("//div [@tabindex='-1']")).size() > 0;
			if(isPresent==true){
				String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText();
				System.out.println(errortest2);
				driver.findElement(By.xpath("//span [text()='Ok']")).click();
				
				}
			
		if(isPresent==false){
		findByList(driver, name, "//*[@id='ctl08_ctl03_grdLogMessage_grid']", "//div[4]/span");	
		String  description=driver.findElement(By.xpath("//div[@id='ctl08_ctl03_divDescription']")).getText(); 
		System.out.println(description);
	}
			
			}
				
			
			
			
			
			
			
		
				
				
		

	 
	 @AfterClass
		public void closeSelenium() throws Exception {
			driver.close();
			driver.quit();
			}
	 
}