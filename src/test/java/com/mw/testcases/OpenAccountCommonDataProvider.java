package com.mw.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.SkipException;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mw.base.TestBase;
import com.mw.utilities.TestUtils;

public class OpenAccountCommonDataProvider extends TestBase{
	
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
	public void openAccountTest(String customer, String currency) throws InterruptedException, AWTException {
		
		if(!TestUtils.isTestRunnable("openAccountTest")) {
			
			throw new SkipException("Skipping the test openAccountTest since runMode is No ("+customer+","+currency+")");
		}
		click("openAcctBtn_CSS");
		select("customer_CSS", customer);
		select("currency_CSS",currency);
		click("process_CSS");
		
	}
}
