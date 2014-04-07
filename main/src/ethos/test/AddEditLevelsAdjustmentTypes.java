package ethos.test;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.ErrorPageException;
import com.domain.ETHOSDomainWraper;

public class AddEditLevelsAdjustmentTypes extends ETHOSDomainWraper {
	
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) getDriver(driver,cachedProperties.value("ethosbrowser"));
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
	public Object [ ][ ] AddEditLevelsAdjustmentTypesList() {
		return new Object [ ] [ ] {{ "System"}};
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsClientGroup(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"CLIENT_GRP");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"CLIENT_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsCompany(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"COMPANY");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"COMPANY_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsSite(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"SITE");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"SITE_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsDeliveryPoint(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"DELIVERY_PT");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"DELIVERY_PT_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsDeliveryPointLevel(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"DP_LEVEL");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"DP_LEVEL_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsVolume(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"VOLUME");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"VOLUME_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsMonetary(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"MONETARY");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"MONETARY_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_0"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_1"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void addLevelAdjustmentAsMinorUnit(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			safeType(driver, By.id("ctl00_cphMainContent_txtDataType"),"MINOR_UNIT");
			safeType(driver, By.id("ctl00_cphMainContent_txtDesc"),"MINOR_UNIT_DESC");
			safeSelectByText(driver, By.id("ctl00_cphMainContent_ddlUnitBasis"),"MWh");
			safeType(driver, By.id("ctl00_cphMainContent_txtDecimalPlaces"),"2");
			
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtClientGroup_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtCompany_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtSite_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioUsedAtDeliveryPoint_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioAllowDP_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsVolume_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioIsMonetary_1"));
			safeClick(driver, By.id("ctl00_cphMainContent_radioDefaultUseMinorCurrencyUnit_0"));
			
			safeClick(driver, By.id("ctl00_cphMainContent__btnSave"));
			
			if(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent__duplicateValidator"),1))
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnCancel"));
			else
				safeClick(driver, By.cssSelector("#ctl00_cphMainContent__btnToList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void deleteLevelAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			
			safeSelectByText(driver, By.cssSelector(".grid-pager>select"),"30");
			int row = returnRowNumber(driver, "#ctl00_cphMainContent_gvLevelAndAdjustmentType>tbody", "SITE", 2);
			
			String cssId = "#ctl00_cphMainContent_gvLevelAndAdjustmentType>tbody>tr:nth-child("+row+")>td:nth-child(1)>a";
			safeClick(driver, By.cssSelector(cssId));
			safeClick(driver, By.id("ctl00_cphMainContent__btnDelete"));
			
			confirmDeleteOperation(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void defaultUnitBasisLevelAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			safeClick(driver, By.id("ctl00_cphMainContent_btnAddNew"));
			
			assertEquals(safeGetSelectedValue(driver, By.id("ctl00_cphMainContent_ddlUnitBasis")),"(Select Unit Basis)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void verifyExportLevelsAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			eDownloader(driver, ".grid-pager>a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void verifyDeleteLinkLevelAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			
			safeSelectByText(driver, By.cssSelector(".grid-pager>select"),"20");
			int row = returnRowNumber(driver, "#ctl00_cphMainContent_gvLevelAndAdjustmentType>tbody", "SITE", 2);
			
			String cssId = "#ctl00_cphMainContent_gvLevelAndAdjustmentType>tbody>tr:nth-child("+row+")>td:nth-child(1)>a";
			safeClick(driver, By.cssSelector(cssId));
			
			assertTrue(elementPresent(driver, By.id("ctl00_cphMainContent__btnDelete"),1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void verifyExportLinkLevelAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			assertTrue(elementPresent(driver, By.cssSelector(".grid-pager>a"),1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "AddEditLevelsAdjustmentTypesList", dependsOnMethods = {"ethosSignin"})
	public void verifyAddButtonLevelAdjustment(String item) throws InterruptedException, ErrorPageException {
		
		try {
			navigateToProductGroup(driver, "GAS", "Level & Adjustment Types");
			assertTrue(elementPresent(driver, By.cssSelector("#ctl00_cphMainContent_btnAddNew"),1));
			
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
