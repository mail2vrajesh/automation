package metrics.test;

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
}


@DataProvider
public Object [ ][ ] users() {
return new Object [ ] [ ] {{ "Metrics", "Metrics"} };
}
@Test(dataProvider="users")
public void metricSignin(String username, String password) throws Exception {
getApp(driver,cachedProperties.value("Metrics_url"),"Login");
metricsLogin(driver,username,password);	
if(username.equals("Metrics")){
elementPresent(driver, By.xpath("//div[@id='divList']/div/div/div[2]/div[4]/div"), 60); 
}else{
elementPresent(driver, By.cssSelector("div.msg.errorMsg"), 60); 
}
} 


@Test (dependsOnMethods={"metricSignin"})
public void verifyAccessControlOptions() throws Exception{
safeClick(driver, By.xpath("//a[text()='System Admin']"));
WebElement access=driver.findElement(By.xpath("//*[@id='nav-secondary']/li[1]"));
//get the sub Options in accessControl
List<WebElement> row=access.findElements(By.tagName("li"));
for(WebElement rows:row){
System.out.println(rows.getText());
}
}


//get the data in user table
@Test (dependsOnMethods={"verifyAccessControlOptions"})
public void verifyUserData() throws Exception{
String msg="Client Not Found";	
String expectedClient="SomeThing";
while (msg.equals("Client Not Found")){	
waitTitle(driver, msg,20);
WebElement table = driver.findElement(By.xpath("//ul [@class='table-grid']"));
			List<WebElement> rows = table.findElements(By.tagName("li"));
			for (WebElement row: rows){
			if (row.getText().equals(expectedClient)){
			msg="Client Found";
			row.click();
			break ;
			}
			}
			if (msg.equals("Client Not Found")){
			       waitTitle(driver, msg,20);
			driver.findElement(By.linkText("Next")).click();
			}

	      }
		}
	

@DataProvider
public Object [ ][ ] NewUserlist() {
return new Object [ ] [ ] { {"MetricsUser","MetricsUser1","MetricsUser1","MetricsUser1@gmail.com",
            	                  "MetricsFirstName","MetricsLasttName","123456789","080123456","EneryQuotes",
            	                  "Testing","perminent","Bangalore","560063","india","03061995","note-nothing","20042020"}
};
}
 @Test(dataProvider="NewUserlist",dependsOnMethods={"verifyUserData"})
 public void newUserCreation(String username,String password,String confirmpassword,String email,String firstname,
          	String Lastname,String telephone,String mobile,String company,String department,String jobtitle,String city,String address,
          	String postcode,String country,String dateofbirth,String note,String expiredate){
            	
driver.findElement(By.xpath("//a[text()='Create New User']")).click();
Select sel = new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']")));
                //verify the sub options in user dropdownList
List<WebElement> li  = sel.getOptions();
for(WebElement options:li){
System.out.println(options.getText());
}
         		   //Select the Web in Drop down list
new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"))).selectByVisibleText("Web");

driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Username']")).sendKeys(username);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Password']")).sendKeys(password);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_RepeatPassword']")).sendKeys(confirmpassword);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Email']")).sendKeys(email);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_FirstName']")).sendKeys(firstname);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_LastName']")).sendKeys(Lastname);

driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Tel']")).sendKeys(telephone); 
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Mobile']")).sendKeys(mobile);

driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Company']")).sendKeys(company);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Department']")).sendKeys(department);

driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_JobTitle']")).sendKeys(jobtitle); 
driver.findElement(By.xpath("//textarea[@name='ctl08$ctl03$txtUser_Address']")).sendKeys(address); 
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Postcode']")).sendKeys(postcode);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Country']")).sendKeys(country);
driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_DateOfBirth']")).sendKeys(dateofbirth);

driver.findElement(By.xpath("//textarea [@name='ctl08$ctl03$txtUser_Note']")).sendKeys(note); 

driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_ExpireDate']")).sendKeys(expiredate); 
driver.findElement(By.xpath("//a[text()='Save']")).click();

boolean isPresent=isElementPresent(driver,By.xpath("//span [text()='Error - Username']"));

if(isPresent==true){
	    String errortest2=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
		System.out.println(errortest2);
		driver.findElement(By.xpath("//span [text()='Ok']")).click(); 
		//user exiting
		 driver.findElement(By.xpath("//a[text()='System Admin']")).click();
		 Reporter.log("The Newuser is added exiting "+errortest2);
	}
else {
	//user new Successful
	Reporter.log("The Newuser is added Successful "+username);
	System.out.println("The Newuser is added Successful "+username);
}
}
 @DataProvider
 public Object [ ][ ] ExitingUserlist() {
	 return new Object [ ] [ ] { {"MetricsUser"}};
 }
 //we should give already exiting user 
 @Test(dataProvider="ExitingUserlist",dependsOnMethods={"newUserCreation"})
 public void duplicateUserValidation(String username){
	     driver.findElement(By.xpath("//a[text()='System Admin']")).click();
		 driver.findElement(By.xpath("//a[text()='Create New User']")).click();
		 new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"))).selectByVisibleText("Web");
			
		 driver.findElement(By.xpath("//input[@name='ctl08$ctl03$txtUser_Username']")).sendKeys(username);
		 driver.findElement(By.xpath("//a[text()='Save']")).click();
		 String errortest1=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
		 System.out.println(errortest1);
		 Reporter.log(errortest1);
		 driver.findElement(By.xpath("//span [text()='Ok']")).click(); 
			
}
//we should give already empty user 
 @Test(dependsOnMethods={"newUserCreation"})
	public void emptyUserValidation(){
      driver.findElement(By.xpath("//a[text()='System Admin']")).click();
  	   driver.findElement(By.xpath("//a[text()='Create New User']")).click();
  	    new Select(driver.findElement(By.xpath("//select [@name='ctl08$ctl03$ddlUser_Type']"))).selectByVisibleText("Web");
  		driver.findElement(By.xpath("//a[text()='Save']")).click();
  		String errortest=driver.findElement(By.xpath("//*[@id='dialog-message']/p")).getText(); 
  		System.out.println(errortest);
		 Reporter.log(errortest);
  		driver.findElement(By.xpath("//span [text()='Ok']")).click(); 
    
}
 
 //verify the new username in Win Active Dir
 @DataProvider
 public Object [ ][ ] UserlistWinActiveDir() {
	 return new Object [ ] [ ] { {"EthosDev"}};
 }
 //we should give already exiting user 
 @Test(dataProvider="UserlistWinActiveDir",dependsOnMethods={"emptyUserValidation"})
 public void winActiveDirUserName(){
	 
	 
 }

}
