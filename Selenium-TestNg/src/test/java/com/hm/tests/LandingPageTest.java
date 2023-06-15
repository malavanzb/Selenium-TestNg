package com.hm.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.hm.pages.Base;
import com.hm.pages.LandingPage;

public class LandingPageTest extends BaseTest{

	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browser) {
		browser="Chrome";
		System.out.println("Browser = "+browser);
		initDriver(browser);
		landingPage = new LandingPage();
	}
}
