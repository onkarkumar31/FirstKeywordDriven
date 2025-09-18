package com.mw.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.mw.base.TestBase;
import com.mw.utilities.TestUtils;

public class CustomListeners extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		test = rep.createTest(result.getName());
		//rep.flush();
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, result.getName().toUpperCase()+" has passed");
		rep.flush();
		log.debug(result.getName().toUpperCase()+" has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String screenShotFile = "";
		try {
			screenShotFile = TestUtils.captureScreenShot(result.getName().toUpperCase());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target=\"_blank\" href=\""+screenShotFile+"\"><img src=\""+screenShotFile+"\" height=100 width=100></img></a>");
		test.addScreenCaptureFromPath(screenShotFile);
		test.log(Status.FAIL, result.getName().toUpperCase()+" has failed");
		rep.flush();
		log.debug("loginTest has failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
		
		test.log(Status.SKIP, "Test "+result.getName()+" got skipped with message: "+result.getThrowable());
		rep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
	
}
