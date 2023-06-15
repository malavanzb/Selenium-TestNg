package com.hm.tests;

import static com.hm.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.hm.pages.LandingPage;
import com.hm.utils.logs.Log;

public class DLandingPageTest extends DBaseTest {
	
	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) {
		initDriver(browser);
		landingPage = new LandingPage(driver);
	}
	
	@Test(enabled=true)
	public void verifyLandingPageTitle(Method method) {
        //ExtentReports Description
		startTest(method.getName(), "Verify Landing Page Title");
		
		String title = landingPage.getLandingPageTitle();
		Assert.assertEquals(title, landingPage.expLandingPageTitle);
	}
	
	@Test(enabled=true)
	public void getShoppingLinksList(Method method) {
        //ExtentReports Description
		startTest(method.getName(), "Verify Shoppling Links List");
		
		SoftAssert softAssert = new SoftAssert();
		
		landingPage.refreshPage();
		
		List<WebElement> shopplingLinksList = landingPage.getShoppingLinksList();
		
		softAssert.assertEquals(shopplingLinksList.size(), landingPage.expectedShopplingListLabels.length);
		
		int i=0;
		for(WebElement we : shopplingLinksList) {
			String listLabel = we.getText();
			System.out.println(listLabel);
			softAssert.assertEquals(listLabel, landingPage.expectedShopplingListLabels[i++]);
		}
		softAssert.assertAll();
		
	}
	
	@Test(enabled=false)
	public void validateGridIcon() {
		boolean flag = landingPage.validateGridIcon();
		Assert.assertTrue(flag);
		
		if(flag) {
			landingPage.clickValidateGridIcon();
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		Log.debug("verifyLandingPageTitle....Ended");
	}
}
