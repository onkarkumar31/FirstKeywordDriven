package com.mw.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Rough1 {
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		Actions action = new Actions(driver);
		
		WebElement sourceElement = driver.findElement(By.xpath(""));
		WebElement targetElement = driver.findElement(By.xpath(""));
		
		action.dragAndDrop(sourceElement, targetElement).perform();
		
	}

}
