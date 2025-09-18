package com.mw.rough;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyWindowsTabTest {
	
	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;
 
	public static void main(String[] args) throws InterruptedException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if(browser.equals("ie")) {			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		} else if(browser.equals("edge")) {			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		
		} 
//		else if(browser.equals("opera")) {			
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			OperaOptions options = new OperaOptions();
//			options.setBinary("C:\\Users\\hello\\AppData\\Local\\Programs\\Opera\\64.0.3417.73\\opera.exe");
//			capabilities.setCapability(OperaOptions.CAPABILITY, options);
//			
//			WebDriverManager.operadriver().setup();
//			driver = new OperaDriver(options);
//		
//		}
		
		//Pre-conditions | Maximize the browser and apply implicit waits.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//Navigate to this site for links.
		driver.get("https://www.facebook.com");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));
		
		System.out.println("Total Links="+linkElements.size());
		
		for(WebElement e: linkElements) {
			System.out.println(e.getText());
			
		}
		
		WebElement footer = driver.findElement(By.cssSelector("#pageFooterChildren > ul"));
		
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		
		//Limit the list to size 5
		footerLinks  = footerLinks.stream().limit(5).collect(Collectors.toList());
		
		
		Iterator<WebElement> itr = footerLinks.iterator();
		
		System.out.println("Operating on footer links");
		String clickonlinkTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
		while(itr.hasNext()) {
			
			WebElement footerLink = itr.next();
			System.out.println(footerLink.getText());
			footerLink.sendKeys(clickonlinkTab);
			Thread.sleep(Duration.ofSeconds(1));
		}
		
		Iterator<String> witr = driver.getWindowHandles().iterator();
		
		System.out.println("Getting tabs title");
		String parentWindow = witr.next();
		String parentWindowTitle = driver.switchTo().window(parentWindow).getTitle();
		System.out.println("Parent window is: "+parentWindowTitle);
		
		//restrict link opening to only 5
		int count = 0;
		while(witr.hasNext()) {
			String wh = witr.next();
			if(wh.equals(parentWindow)) {
				continue;
			}
			driver.switchTo().window(wh);
			if(driver.getTitle().isEmpty()) {
				System.out.println("==>Blank Title: "+driver.getCurrentUrl());
			}else {
				System.out.println("ChildWIndow is "+driver.getTitle());
			}
			if(count>=5) {
				break;
			}
			count++;
			
		}
		
		driver.close();
	}

}
