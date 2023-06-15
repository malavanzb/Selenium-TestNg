package com.hm.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseExtentReport {
	
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/MyOwnReporter.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("OS", "Mac Sierra");
		extent.setSystemInfo("Host Name", "xxxx");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Madhuvan");
		

		sparkReporter.config().setDocumentTitle("Automation Test Report for xxx environment");
		sparkReporter.config().setReportName("Test Report for Sanity Environment");
		sparkReporter.config().setTheme(Theme.STANDARD);
	}
	
	@AfterMethod
	public void getResult(ITestResult result ) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Test case Failed due to below issues:", 
					ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Test Case Passed", 
					ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "Test Case SKIPPED",
					ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

}
