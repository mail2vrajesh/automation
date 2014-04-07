	
package ethos.test;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class RFQDocumentText extends ETHOSDomainWraper {
	
		public RemoteWebDriver driver = null;
	
		@BeforeClass
		public void startSelenium() throws Exception {	
			driver=(RemoteWebDriver) getDriver(driver, cachedProperties.value("ethosbrowser"));
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		}
		
		@DataProvider
		public Object [ ][ ] users() {
			return new Object [ ] [ ] {{"madhva","madhva"} };
		}
	
		@Test(dataProvider="users")
		public void ethosSignin(String username, String password) throws Exception {
			getApp(driver,cachedProperties.value("Ethos_url"),"ETHOS Login");
		    ethosLogin(driver,username,password);
		    
			if(username.equals("madhva") || username.equals("sachin")){
				waitTitle(driver, "ETHOS Main Page", 10);
				elementPresent(driver, By.id("ctl00_btnLogout"), 10); 
			}else{
				textPresent(driver, "Your login attempt was not successful. Invalid User Name", 10);
			}
		}
		
		@DataProvider
		public Object [ ][ ] RFQDocumentList() {
			return new Object [ ] [ ] {{ "System"}};
		}
		
		@Test(dataProvider = "RFQDocumentList", dependsOnMethods = {"ethosSignin"})
		public void verifyAddNewRFQDocument(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "RFQ Document Text");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocument>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size();
				
				String descriptionCssID = "ctl00_cphMainContent_gvRFQDocument_ctl"+noOfRows+"_txtDescriptionAdd";
				String textStyleCssID = "ctl00_cphMainContent_gvRFQDocument_ctl"+noOfRows+"_ddlSectionStylesAdd";
				String addButtonCssID = "ctl00_cphMainContent_gvRFQDocument_ctl"+noOfRows+"_btnAdd";
				
				safeType(driver,By.id(descriptionCssID),"Automation Entry "+noOfRows);
				safeSelectByText(driver,By.id(textStyleCssID),"Heading 1");
				safeClick(driver, By.id(addButtonCssID));
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				
				if(isElementPresent(driver, By.id("ctl00_cphMainContent_CustVal_Validate")))
					safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));
				
				safeClick(driver, By.id("ctl00_cphMainContent_lnkBack"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RFQDocumentList", dependsOnMethods = {"ethosSignin"})
		public void verifyEditRFQDocument(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "RFQ Document Text");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocument>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size()-1;
				String descriptionCssID;
				System.out.println("Rows is for Edit "+noOfRows);
				
				if (noOfRows < 10)
					descriptionCssID = "ctl00_cphMainContent_gvRFQDocument_ctl0"+noOfRows+"_txtDescription";
				else
					descriptionCssID = "ctl00_cphMainContent_gvRFQDocument_ctl"+noOfRows+"_txtDescription";
				
				System.out.println("Edit Id is "+descriptionCssID);
				safeType(driver,By.id(descriptionCssID),"Modified Automation Entry "+noOfRows);
				
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				Thread.sleep(2000);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RFQDocumentList", dependsOnMethods = {"ethosSignin"})
		public void verifyInsertReferenceRFQDocument(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "RFQ Document Text");
				
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocument>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size();
				System.out.println("Rows is for Insert "+noOfRows);
				
				String selectCSSId = "#ctl00_cphMainContent_gvRFQDocument>tbody>tr:nth-child("+noOfRows+")>td:nth-child(1)>a";
				
				System.out.println("Edit Id is "+selectCSSId);
				safeClick(driver, By.cssSelector(selectCSSId));
				Thread.sleep(3000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				WebElement table2 = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocumentEdit>tbody"));
				List<WebElement> rows2 = table2.findElements(By.tagName("tr"));
				noOfRows = rows2.size();
				System.out.println("Rows is for Insert "+noOfRows);
				
				
				safeClick(driver, By.id("ctl00_cphMainContent_btnReferenceText"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client Acceptance");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client ID");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client Name");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_gvRFQDocumentEdit_ctl0"+noOfRows+"_ddlSectionStylesAdd"),"Normal");
				safeClick(driver, By.id("ctl00_cphMainContent_gvRFQDocumentEdit_ctl0"+noOfRows+"_btnAdd"));
				
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				
				if(isElementPresent(driver, By.id("ctl00_cphMainContent_CustVal_Validate")))
						safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));
				
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_lnkBack"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(dataProvider = "RFQDocumentList", dependsOnMethods = {"ethosSignin"})
		public void verifyInsertReferenceRFQDocumentCancel(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "RFQ Document Text");
				
				
				int noOfRows;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocument>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size();
				System.out.println("Rows is for Insert "+noOfRows);
				
				String selectCSSId = "#ctl00_cphMainContent_gvRFQDocument>tbody>tr:nth-child("+noOfRows+")>td:nth-child(1)>a";
				
				System.out.println("Edit Id is "+selectCSSId);
				safeClick(driver, By.cssSelector(selectCSSId));
				Thread.sleep(3000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				WebElement table2 = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocumentEdit>tbody"));
				List<WebElement> rows2 = table2.findElements(By.tagName("tr"));
				noOfRows = rows2.size();
				System.out.println("Rows is for Insert "+noOfRows);
				
				
				safeClick(driver, By.id("ctl00_cphMainContent_btnReferenceText"));
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client Acceptance");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client ID");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlReferenceText"),"Client Name");
				safeSelectByText(driver, By.id("ctl00_cphMainContent_gvRFQDocumentEdit_ctl0"+noOfRows+"_ddlSectionStylesAdd"),"Normal");
				safeClick(driver, By.id("ctl00_cphMainContent_gvRFQDocumentEdit_ctl0"+noOfRows+"_btnAdd"));
				
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnCancel"));
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_lnkBack"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test(dataProvider = "RFQDocumentList", dependsOnMethods = {"ethosSignin"})
		public void verifyDeleteRFQDocument(String item) throws InterruptedException, ErrorPageException {
			
			try {
				navigateToProductGroup(driver, "ELE", "RFQ Document Text");
				safeClick(driver, By.id("ctl00_cphMainContent_btnEdit"));
				
				int noOfRows;
				String deleteButtonId;
				WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvRFQDocument>tbody"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				noOfRows = rows.size()-1;
				
				System.out.println("Rows is for Delete "+noOfRows);
				if (noOfRows < 10)
					deleteButtonId = "ctl00_cphMainContent_gvRFQDocument_ctl0"+noOfRows+"_btnDelete";
				else
					deleteButtonId = "ctl00_cphMainContent_gvRFQDocument_ctl"+noOfRows+"_btnDelete";
				
				System.out.println("Delete Id is "+deleteButtonId);
				safeClick(driver, By.id(deleteButtonId));
				Thread.sleep(2000);
				acceptAlert(driver, "Are you sure");
				Thread.sleep(2000);
				safeClick(driver, By.id("ctl00_cphMainContent_btnSave"));
				Thread.sleep(2000);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@AfterClass
		public void closeSelenium() throws Exception {
			driver.close();
			driver.quit();
		}
		@AfterMethod (alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception{
			if(screenshot){
				screenshot(_result, driver);
			}
			
		}
	
	
	
	}
		