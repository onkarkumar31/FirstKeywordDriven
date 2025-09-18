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

public class AddCustomer extends TestBase{
	
	//@Test(dataProvider="custData")
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
	public void addCustomerTest(String firstName, String lastName, String pinCode, String runMode) throws InterruptedException, AWTException {
		if(!runMode.equals("Y")) {
			throw new SkipException("Test skipped since runMode is no for data:"+firstName+","+lastName+","+pinCode);
		}
		
		System.out.println(firstName+":"+lastName+":"+pinCode);
		log.debug(firstName+":"+lastName+":"+pinCode);
		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.contains("manager"))
		driver.findElement(By.cssSelector(OR.getProperty("BankManagerLogin_CSS"))).click();
		
		driver.findElement(By.cssSelector(OR.getProperty("addCust_CSS"))).click();
		
		type("firstName_XPATH", firstName);
		type("lastName_XPATH", lastName);
		type("pinCode_XPATH", pinCode);
		click("addBtn_XPATH");
		Thread.sleep(Duration.ofSeconds(1));
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id"), "Customer addition failed.");
		alert.accept();
		
		// Additional message form reportNG
		Reporter.log("Customer Added successfully");
		
		click("showCust_XPATH");
		driver.findElement(By.cssSelector(".marTop.ng-scope")).click();
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//js.executeScript("window.scrollBy(0,500)"); //Scrolls down by 500 pixels
		//js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		//js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		//Thread.sleep(2000);
		
		
		Actions action = new Actions(driver);
		String scrollSeq = Keys.chord(Keys.COMMAND, Keys.ARROW_DOWN);
		action.sendKeys(scrollSeq).build().perform();
		action.sendKeys(scrollSeq).build().perform();
		//action.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		
		//Robot robot = new Robot();
		//robot.mouseWheel(500);
		
		Thread.sleep(1000);
		
		
		//inputText(By.cssSelector(OR.getProperty(pinCode)), );
		
	}

	@DataProvider(name="custData")
	public Object[][] getCustData(){
		String sheetName = "addCustomer";
		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);
		
		Object[][] data=  new Object[rowNum-1][colNum];
		
		for(int i =2;i<=rowNum;i++) {
			
			for(int j=0;j<colNum;j++) {
				data[i-2][j] = excel.getCellData(sheetName, j, i);
			}
		}
		
		return data;
		
	}
}
