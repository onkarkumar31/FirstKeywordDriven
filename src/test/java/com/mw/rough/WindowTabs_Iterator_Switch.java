package com.mw.rough;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;
//import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class WindowTabs_Iterator_Switch {
	
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
		
		//Store in an integer variable the total number of links.
		int linkCounts = driver.findElements(By.tagName("a")).size();
		
		//Print in the console the total number of links.
		System.out.println("The total number of links in this website is: " + linkCounts);
		
		//Store in a "footer" variable the footer section of the page.
		WebElement footer = driver.findElement(By.cssSelector(".uiList.pageFooterLinkList._509-._4ki._703._6-i"));
		
		//Print in the console the number of links in the footer section
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		System.out.println("The number of links in the footer section is: " + footerLinks.size());
		System.out.println("------Print All Links in the Footer Section Only------");
		
		//Print all the links within the footer section only.
		for(WebElement footerBlock : footerLinks) {
			System.out.println(footerBlock.getText() + " | " + footerBlock.getAttribute("href"));
		}
		
		//Click the links one by one and open them in a new tab.
		for(int i = 0; i < footerLinks.size(); i++) {
			String clickonlinkTab = Keys.chord(Keys.COMMAND,Keys.ENTER);
			footerLinks.get(i).sendKeys(clickonlinkTab);
			Thread.sleep(1000);
		}
		
		//The title of each window including the parent window gets printed.
		java.util.Iterator<String> iter = driver.getWindowHandles().iterator();	
		while(iter.hasNext()) {
			driver.switchTo().window(iter.next());
			System.out.println(driver.getTitle());
		}
		
		//Close all the child windows except the parent window
	    String parentWindow = driver.getWindowHandle();
	    //Condition to handle the switching and closing of opened tabs
	    for(String openTabs : driver.getWindowHandles()) {
	        if (!openTabs.equals(parentWindow)) {
	            driver.switchTo().window(openTabs);
	            driver.close();
	        }
	    }
	    driver.switchTo().window(parentWindow);
		
		//Navigate to this site wherein if you click the child window, it will automatically open in a new tab.
		driver.get("https://www.hdfc.com/");
		WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"HomepageModalVideo\"]/div/div/div[1]/button"));
		closeButton.click();
		WebElement linkBlogs = driver.findElement(By.xpath("(//a[@href='https://www.hdfc.com/blog'])[2]"));
		linkBlogs.click();
		
		//The console first prints the title of child window and then the title of the parent window.
		java.util.Iterator<String> iterate = driver.getWindowHandles().iterator();
		String parentID = iterate.next();			//first iterate.next() points to current parent window
		String childID = iterate.next();			//second iterate.next() points to current child window
		
		driver.switchTo().window(childID);			//switches to child window
		System.out.println(driver.getTitle());		//prints title of child window
		
		driver.switchTo().window(parentID);			//switches back to parent window
		System.out.println(driver.getTitle());		//prints title of parent window
		
		driver.switchTo().window(childID);			//switches to child window
		driver.close();								//close the child window
		driver.switchTo().window(parentID);			//switches back to parent window
		
		//Navigate to this site wherein if you click the child window, you need to press CTRL + ENTER
		driver.get("https://www.cheqsystems.com/");
		WebElement careersLink = driver.findElement(By.xpath("(//a[@href='https://www.cheqsystems.com/careers/'])[1]"));
		String ctrlEnter = Keys.chord(Keys.COMMAND,Keys.ENTER);
		careersLink.sendKeys(ctrlEnter);
		
		//The console first prints the title of child window and then the title of the parent window.
		java.util.Iterator<String> iterate1 = driver.getWindowHandles().iterator();
		String parentID1 = iterate1.next();			//first iterate.next() points to current parent window
		String childID1 = iterate1.next();			//second iterate.next() points to current child window
		
		driver.switchTo().window(childID1);			//switches to child window
		System.out.println(driver.getTitle());		//prints title of child window
		
		driver.switchTo().window(parentID1);		//switches back to parent window
		System.out.println(driver.getTitle());		//prints title of parent window
		
		driver.quit();								//End the WebDriver session
	}
 
}
