package Automationacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Automationacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	
	ExtentReports extent =  ExtentReporterNG.getReportObject();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		String filepath = "null";
		
	try {
		 filepath = 	getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
		//Screenshot, Attach to report
	}
	
	public void onTestSkipped(ITestResult result) {
		
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	public void onStart(ITestContext context) {
		
		
	}
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}
	
}
