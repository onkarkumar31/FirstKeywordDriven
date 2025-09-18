package com.mw.testcases;

import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.mw.base.TestBase;

public class Rough extends TestBase{
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		
		
		
	}
	
	@Test
	public void f() throws AWTException, InterruptedException {
//		Robot robot = new Robot();
//		robot.mouseWheel(500);
//		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//js.executeScript("window.scrollBy(0,500)"); //Scrolls down by 500 pixels
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
		Thread.sleep(5000);
	}

}
