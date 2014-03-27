package ethos.volumedata;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.FrameworkCommon;

public class S11 extends FrameworkCommon{

	public RemoteWebDriver driver = null;
	public CommonUtils utils=new CommonUtils();

	@BeforeClass
	public void startSelenium() throws Exception {	
		driver=(RemoteWebDriver) utils.getDriver();
		utils.openUrl(cachedProperties.value("Ethos_url"));
		utils.login( "madhva", "madhva");
		utils.goToVolumeDataPage();
	}
	
	
	@Test
	public void test1() throws Exception
	{
		String[] productGrps={"Electricity","Gas","Oil"};
		ArrayList<String> actProducts=utils.getOptionsDropdown(By.xpath("//select[contains(@id,'ddlProductGroup')]"));
		for(String product:productGrps)
			Assert.assertTrue(actProducts.contains(product), product +"doesn't contain in the drop down");
		
		Assert.assertTrue(actProducts.size()==productGrps.length, "Size of Expected & actual products don't match");
			
	}
	
	@Test
	public void test2() throws Exception
	{
		String[] products={"Half Hourly Electricity","Non Half Hourly Electricity","Gas Supply","Diesel","Gas Oil","Heavy Fuel Oil","Kerosene","Medium Fuel Oil","Unleaded"};
		ArrayList<String> actProducts=utils.getOptionsDropdown(By.xpath("//select[contains(@id,'ddlProduct')]"));
		for(String product:products)
			Assert.assertTrue(actProducts.contains(product), product +"doesn't contain in the drop down");
		
		Assert.assertTrue(actProducts.size()==products.length, "Size of Expected & actual products don't match");
			
	}
	
	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
}