package com.hm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.hm.pages.DBase;
import com.hm.pages.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DBaseTest extends DBase {
    public WebDriver driver;
    public LandingPage landingPage;
    
	private static String currentBrowser;
    
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeSuite
    public void setupProxy() {

    }
	
	public void initDriver(String browser) {

		// String browser = prop.getProperty("browser");
		currentBrowser = browser;

//      handle using owner logic		
//		String browser = mec.browser();

		if (currentBrowser.equals("Chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// 				"C:\\Users\\madha\\Downloads\\chromedriver_win32\\chromedriver.exe");
			
			ChromeOptions option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");
            
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
		} else if (currentBrowser.equals("Firefox")) {
			// System.setProperty("webdriver.gecho.driver",
			// 				"C:\\Users\\madha\\Downloads\\geckodriver-v0.33.0-win32\\gechodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		String env = prop.getProperty("environment");
		String url = null;
		if (env.equalsIgnoreCase("dev"))
			url = prop.getProperty("dev.url");
		else if (env.equalsIgnoreCase("uat"))
			url = prop.getProperty("uat.url");
		else if (env.equalsIgnoreCase("stg"))
			url = prop.getProperty("stg.url");
		else if (env.equalsIgnoreCase("prod"))
			url = prop.getProperty("prod.url");

		driver.get(url);

		driver.manage().window().maximize();

//      handle using owner logic
//		driver.get(mec.url());

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
