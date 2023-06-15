package com.hm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

//import config.MultipleEnvConfig;

public class Base {

    public WebDriver driver;
    public WebDriverWait wait;
    
	public static Properties prop;
//  handle using owner logic  - Not working
//	public static MultipleEnvConfig mec;

//	@BeforeSuite
//	public void setUp() {
//		System.out.println(System.getProperty("user.dir"));
//		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/MyOwnReporter.html");
//		extent = new ExtentReports();
//		extent.attachReporter(sparkReporter);
//		
//		extent.setSystemInfo("OS", "Mac Sierra");
//		extent.setSystemInfo("Host Name", "xxxx");
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("User Name", "Madhuvan");
//		
//
//		sparkReporter.config().setDocumentTitle("Automation Test Report for xxx environment");
//		sparkReporter.config().setReportName("Test Report for Sanity Environment");
//		sparkReporter.config().setTheme(Theme.STANDARD);
//	}

//	@AfterMethod
//	public void getResult(ITestResult result ) {
//		if(result.getStatus()==ITestResult.FAILURE) {
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Test case Failed due to below issues:", 
//					ExtentColor.RED));
//			test.fail(result.getThrowable());
//		} else if(result.getStatus()==ITestResult.SUCCESS) {
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Test Case Passed", 
//					ExtentColor.GREEN));
//		} else {
//			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "Test Case SKIPPED",
//					ExtentColor.ORANGE));
//			test.skip(result.getThrowable());
//		}
//	}

//	@AfterSuite
//	public void tearDown() {
//		extent.flush();
//	}

	public Base() {
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/hm/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// handle using owner logic - Not working
//		mec = ConfigFactory.create(MultipleEnvConfig.class);
	}
	
	public void initDriver(String browser) {		
//      handle using owner logic		
//		String browser = mec.browser();
		
		if(browser.equals("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\madha\\Downloads\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");
            
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
		} else if(browser.equals("Firefox")) {
			//System.setProperty("webdriver.gecho.driver", "C:\\Users\\madha\\Downloads\\geckodriver-v0.33.0-win32\\gechodriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}

		String env = prop.getProperty("environment");
		String url = null;
		if(env.equalsIgnoreCase("dev"))
			url = prop.getProperty("dev.url");
		else if(env.equalsIgnoreCase("uat"))
			url = prop.getProperty("uat.url");
		else if(env.equalsIgnoreCase("stg"))
			url = prop.getProperty("stg.url");
		else if(env.equalsIgnoreCase("prod"))
			url = prop.getProperty("prod.url");

		driver.get(url);	

//      handle using owner logic

//		driver.get(mec.url());
		
	}

	
    //Click Method
    public void click(By by) {
        waitVisibility(by).click();
    }

    //Write Text
    public void writeText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }

    //Read Text
    public String readText(By by) {
        return waitVisibility(by).getText();
    }

    //Wait
    public WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }	



}
