package com.hm.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.hm.pages.Base;
import com.hm.pages.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends Base {
    public WebDriver driver;
    public LandingPage landingPage;
    	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeSuite
    public void setupProxy() {

    }

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
