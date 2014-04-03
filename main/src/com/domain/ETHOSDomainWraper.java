package com.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

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
	
	//During the code refactoring all the navigations would be deleted and only the following method will be used.
	public void navigateToProductGroup(RemoteWebDriver driver, String productId, String productGroup)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			safeClick(driver, By.linkText("Product Groups"));
			
			int noOfRows;
			WebElement table = driver.findElement(By.cssSelector("#ctl00_cphMainContent_gvProductGroup>tbody"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			noOfRows = rows.size(); 
		    
			String actualProdId;
			String cssId;
			int iterator = 2;
		    for(iterator=2; iterator<noOfRows; iterator++) {
		    	cssId = "#ctl00_cphMainContent_gvProductGroup>tbody>tr:nth-child("+iterator+")>td:nth-child(2)";
		    	actualProdId = safeGetText(driver, By.cssSelector(cssId));
		    	if (actualProdId.equals(productId))
		    		break;
		    }
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr["+iterator+"]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
			safeClick(driver, By.linkText(productGroup));
	}
	
	//During the code refactoring all the navigations would be deleted and only the following method will be used.
	public void navigateToScreen(RemoteWebDriver driver, String screen, String subScreen)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText(screen));
			safeClick(driver, By.linkText(subScreen));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			
			navigateToScreen(driver, "System","Product Groups");
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
	}
	
	public void navigateToProductionGroupDestination(RemoteWebDriver driver, String text)
			throws InterruptedException, ErrorPageException, Exception {
			int noOfRows;
			navigateToScreen(driver, "System","Product Groups");
			
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//img[@id='ctl00_cphParentContent_ProductGroupHeader1_btnOptions']"));
			safeClick(driver, By.linkText(text));
	}
	
	public int returnRowNumber(RemoteWebDriver driver, String id, String pattern, int column)
			throws InterruptedException, ErrorPageException, Exception {
			int iterator = 2;
			int noOfRows;
			
			String actualPattern = "";
			String cssId = "";
			
			WebElement table = driver.findElement(By.cssSelector(id));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			noOfRows = rows.size(); 
		    System.out.println("Rowcount "+noOfRows);
		    for (iterator = 2; iterator < (noOfRows-2); iterator++ )
		    {
		    	cssId = id+">tr:nth-child("+iterator+")>td:nth-child("+column+")";
		    	actualPattern = safeGetText(driver, By.cssSelector(cssId));
		    	if (pattern.contains(actualPattern))
		    		break;
		    }
		    
		    if (iterator == (noOfRows-2))
		    	iterator = 2;
		    
		    return iterator;
	}
	
	public void navigateToClientMaintenance(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
		
			safeClick(driver, By.linkText("Client"));
			safeClick(driver, By.cssSelector("#maincontent>ul>li:nth-child(2)>div>b>a"));
			safeClick(driver, By.cssSelector("#maincontent>ul>li:nth-child(1)>div>b>a"));
			
	}
	
	public void navigateToClientMaintenanceGroup(RemoteWebDriver driver, String groupType)
			throws InterruptedException, ErrorPageException, Exception {
		
			navigateToClientMaintenance(driver);
			safeClick(driver, By.id("ctl00_cphMainContent_btnResetFilter"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvClientGroup>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphParentContent_ClientGroupHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver,By.linkText(groupType));
	}
	
	/*bharath
	 * 
	 */
	public void navigateToSecurityRoles(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
	
			safeClick(driver, By.linkText("System"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("Security"));
			safeClick(driver, By.linkText("Security"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("Roles"));
			safeClick(driver, By.linkText("Roles"));
			
	}
	
	public void navigateToProductGroupPage(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("Product Groups"));
			safeClick(driver, By.linkText("Product Groups"));
			waitForPagetoLoad_Element(driver, 10,By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			safeClick(driver, By.xpath("//div[@id='productgrouplist']//tr[4]//a"));
			
	}
	
	public void navigateToNHHConfiguration(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("NHH Configuration"));
			safeClick(driver, By.linkText("NHH Configuration"));
			
	}
	
	public void navigateToNHHConfigurationBrands(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToNHHConfiguration(driver);
			waitForPagetoLoad_Element(driver, 10,By.id("ctl00_cphMainContent_btnAddNew"));
			safeClick(driver, By.linkText("Select"));
			waitForPagetoLoad_Element(driver, 10,By.id("ctl00_lblTitle"));
			safeClick(driver, By.id("ctl00_cphParentContent_DPConfigHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.id("ctl00_cphParentContent_DPConfigHeader1_Repeater1_ctl00_lnkBands"));			
	}
	public void navigateToCurrencies(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("System"));
			waitForPagetoLoad_Element(driver, 10,By.linkText("Currencies"));
			safeClick(driver, By.linkText("Currencies"));
			
	}
	
	public void navigateToSuppliers(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToSuppliersMarketshare(driver,linktext);
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnApply"));
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			waitForPagetoLoad_Element(driver, 10, By.linkText("Select"));
			safeClick(driver, By.linkText("Select"));
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphParentContent_SupplierHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.id("ctl00_cphParentContent_SupplierHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[4]/div/a[2]"));
			
	}
	
	public void navigateToSuppliersProperdata(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToSuppliersMarketshare(driver,linktext);
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnApply"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));
			waitForPagetoLoad_Element(driver, 10, By.linkText("Select"));
			safeClick(driver, By.linkText("Select"));
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphParentContent_SupplierHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.id("ctl00_cphParentContent_SupplierHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[4]/div/a[2]"));
			
	}
	
	public void navigateToSuppliersClickView(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToSuppliers(driver,linktext);
			waitForPagetoLoad_Element(driver, 10, By.id("ctl00_cphMainContent_btnApply"));
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
			safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));		
	}
	
	public void navigateToSuppliersClickSelect(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			navigateToSuppliersClickView(driver,linktext);
			waitForPagetoLoad_Element(driver, 10, By.linkText("Select"));
			safeClick(driver, By.linkText("Select"));		
	}
	
	public void navigateToSuppliersMarketshare(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("Supplier"));
			waitForPagetoLoad_Element(driver, 10,By.linkText(linktext));
			safeClick(driver, By.linkText(linktext));
	}
	
	public void navigateToRateKeys(RemoteWebDriver driver, String productId) 
			throws InterruptedException, ErrorPageException, Exception {
		
			navigateToProductGroup(driver, productId, "Rate Types");
			
			safeClick(driver, By.cssSelector("#ctl00_cphMainContent_gvRateType>tbody>tr:nth-child(2)>td:nth-child(1)>a"));
			safeClick(driver, By.id("ctl00_cphParentContent_RateTypeHeader1_Repeater1_ctl00_btnOptions"));
			safeClick(driver, By.linkText("Rate Keys"));
	}

	public void navigateToImportSiteData(RemoteWebDriver driver)
			throws InterruptedException, ErrorPageException, Exception {
			
		 	safeClick(driver, By.linkText("Client"));
		    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[3]/div[4]/div[2]/ul/li[2]/div/b/a")); 
		    safeClick(driver, By.xpath("//div[3]/div[4]/div[2]/ul/li[2]/div/b/a"));
		    waitForPagetoLoad_Element(driver, 10,By.linkText("Clients"));		    
		    safeClick(driver, By.linkText("Clients"));
		    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[3]/td/input[1]"));		   
		    safeClick(driver, By.id("ctl00_cphMainContent_btnApply"));//view
		    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));	
		    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td/a"));
		    waitForPagetoLoad_Element(driver, 20,By.xpath("//div[2]/div[3]/div/div/span/div/img"));		    
		    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div/img"));
		    safeClick(driver, By.xpath("//div[2]/div[3]/div/div/span/div[5]/div/a[5]"));//product group
		    waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));
		    safeClick(driver, By.xpath("//div[2]/div[4]/div[2]/div/table/tbody/tr[2]/td[1]/a"));//select button of electricity		    
			waitForPagetoLoad_Element(driver, 10,By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));
			safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div/img"));//down arrow-
		    safeClick(driver, By.xpath("//div[2]/div[3]/div/div[2]/span/div[4]/div/a[7]"));//import site data-		
	}
	
	public void navigateToDataManagement(RemoteWebDriver driver,String linktext)
			throws InterruptedException, ErrorPageException, Exception {
			
			safeClick(driver, By.linkText("Data Management"));
			waitForPagetoLoad_Element(driver, 10,By.linkText(linktext));
			safeClick(driver, By.linkText(linktext));
			
	}
	
	public void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public ArrayList<String> getOptionsDropdown(By by)
	{
		//new Select(driver.findElement(By.xpath("//select[contains(@id,'ddlProductGroup')]"))).getOptions().get(1).getText()
		List<WebElement> l= new Select(driver.findElement(by)).getOptions();
		ArrayList<String> ar=new ArrayList<String>();
		for(WebElement e: l)
			if(e.getText().length()>0 && !e.getText().toUpperCase().contains("SELECT"))
				ar.add(e.getText());
		return ar;
	}

	public void login(String Username, String Password) throws Exception {
		safeType(driver, By.cssSelector("input[type=\"text\"]"), Username);
		safeType(driver, By.cssSelector("input[type=\"password\"]"), Password);
		safeClick(driver, By.xpath("//input[contains(@name,'LoginButton')]"));

	}

	/*public void goToVolumeDataPage() throws Exception {
		
		safeClick(driver, By.linkText("Client"));
		safeClick(driver, By.linkText("Volume"));
		safeClick(driver, By.linkText("Volume Data Totals"));
	}
	
	public void mouseMoveTo(By locator)
	{
		 Actions builder = new Actions(driver);
		 Action move=builder.moveToElement(driver.findElement(locator)).build();
		 move.perform();
	}
	
	public void goToSummaryData() throws Exception
	{
		mouseMoveTo(By.linkText("Client"));
		mouseMoveTo(By.linkText("Volume"));
		mouseMoveTo(By.linkText("Volume"));
		mouseMoveTo(By.linkText("Volume Data Totals"));
		mouseMoveTo(By.linkText("Volume Summary Data"));
		mouseMoveTo(By.linkText("Add"));
		safeClick(driver, By.linkText("Add"));
		
	}*/
	
	public void selectDropDown(By locator,String visibleText)
	{
		new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
	}

	public void jsClick(By locator)
	{
		
	}
	
	public void fillDropDownsVolumeSummaryData()
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "BTMonthlyRawData");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
	public void fillDropDownsForGas()
	{
		selectDropDown(By.xpath("//select[contains(@id,'ddlProductGroup')]"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "Actual");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
	
public void goToVolumeDataPage() throws Exception {
		
		safeClick(driver, By.linkText("Client"));
		safeClick(driver, By.linkText("Volume"));
		safeClick(driver, By.linkText("Volume Data Totals"));
	}
	
	public void mouseMoveTo(By locator)
	{
		 Actions builder = new Actions(driver);
		 Action move=builder.moveToElement(driver.findElement(locator)).build();
		 move.perform();
	}

	public void goToPage(String[] links) throws Exception
	{
		for(String link:links)
			mouseMoveTo(By.linkText(link));
		safeClick(driver, By.linkText(links[links.length-1]));
	}
	public void goToSummaryData() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Volume Summary Data","Add"});
			
	}
	public void goToCountriesPage() throws Exception
	{
		goToPage(new String[]{"System","Country","Countries"});
	}
	public void goToCurrenciesPage() throws Exception
	{
		goToPage(new String[]{"System","Currencies"});
	}
	public void goToExchangeRateTypes() throws Exception
	{
		goToPage(new String[]{"System","Exchange Rate Types"});
	}
	public void goToSystemUsersPage() throws Exception
	{
		goToPage(new String[]{"System","Security","Users"});
		
	}
	public void goToSystemRolesPage() throws Exception
	{
		goToPage(new String[]{"System","Security","Roles"});
		
	}
	public void goToCountryZonesPage() throws Exception
	{
		goToPage(new String[]{"System","Country","Countries","Zone","Zones"});
	}
	public void goToNHHConfiguration() throws Exception
	{
		goToPage(new String[]{"System","NHH Configuration"});

	}
	
	public void goToVolumeDataShapeOut() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Shape Output"});
	}
	
	public void goToVolumeDataIntervalCheck() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Check"});
	}
	public void goToVolumeDataIntervalSummarise() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Summarise"});
	}
	public void goToVolumeDataRemoval() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Remove"});
	}
	public void goToVolumeDataIntervalExport() throws Exception
	{
		goToPage(new String[]{"Client","Volume","Volume","Volume Data Totals","Interval Data","Load","Export"});
	}
	
	/*public void selectDropDown(By locator,String visibleText)
	{
		new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
	}
*/

	
	public void selectElectricityProducts()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
		waitForPageLoaded(driver);
	}
	/*public void fillDropDownsVolumeSummaryData()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Half Hourly Electricity");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "BTMonthlyRawData");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}
	public void fillDropDownsForGas()
	{
		selectDropDown(By.id("ctl00_cphMainContent_ddlProductGroup"), "Gas");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlProduct"), "Gas Supply");
		waitForPageLoaded(driver);
		selectDropDown(By.xpath("//select[contains(@id,'ddlDataType') or contains(@id,'DdlVolumeType')]"), "Actual");
		waitForPageLoaded(driver);
		selectDropDown(By.id("ctl00_cphMainContent_ddlUnitBasis"), "kWh");
		waitForPageLoaded(driver);
	}*/
	public void goToVolumeDataTotalsPage() throws Exception {
		goToPage(new String[]{"Client","Volume","Volume Data Totals"});
	}
	
	public void safeCheck(By locator)
	{
		if(driver.findElement(locator).getAttribute("checked")==null)
			driver.findElement(locator).click();
	
	}
	public void findDelPoint(String dp) throws Exception
	{
		safeType(driver, By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP"), dp);
		safeClick(driver, By.id("ctl00_cphMainContent_DPTreeControl1_btnFindDP"));
		waitForPageLoaded(driver);
		driver.findElement(By.id("ctl00_cphMainContent_DPTreeControl1_txtFindDP")).clear();
	}
	
	public void safeUnCheck(By locator)
	{
		if(driver.findElement(locator).getAttribute("checked")!=null)
			driver.findElement(locator).click();
	
	}
	
	public void goToGenericLookupItems() throws Exception
	{
		goToPage(new String[]{"System","Generic Look Up Items"});
	}
}
