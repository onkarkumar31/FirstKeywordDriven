package com.mw.testcases;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;

import com.mw.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	

  @Test
  public void loginTest() throws InterruptedException, IOException {
	  
	  
	  compareStrings("abc", "xyz");
	  log.debug("Inside loginTest");
	  driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
	  log.debug("Operation click has completed");
	  //Thread.sleep(Duration.ofSeconds(2));
	  log.debug(isElementPresent(By.cssSelector(OR.getProperty("addCust_CSS"))));
	  Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCust_CSS"))), "Add customer button is not present");
	  Assert.fail();
	  Reporter.log("Bank manager login success");

	  
  }
}
