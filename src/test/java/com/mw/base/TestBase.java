package com.mw.base;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mw.utilities.ExcelReader;
import com.mw.utilities.ExtentManager;
import com.mw.utilities.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * 
 * will initialize
 * 
 * WebDriver
 * Properties
 * Logs - Log4j.jar, application.log, selenium.log, ,log4j.properties
 * ExtentReports
 * DB
 * Excel
 * Mail
 * ReportNG, ExtentReports
 * Jenkins
 * 
 */



public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis ;
	public static Logger log = Logger.getLogger(TestBase.class); //("onkie") ;  //
	public static ExcelReader excel = new ExcelReader("/Users/onkumar/eclipse-workspace/FirstKeywordDriven/src/test/resources/excel/Data.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test ;
	static String browser;
	
	
	@BeforeSuite
	public void setUp() throws IOException  {
		
		if(driver == null) {
			
			FileInputStream fis;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
				config.load(fis);
				log.debug("config file loaded");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
				OR.load(fis);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug("OR properties loaded ");
			}
			
			System.setProperty("org.uncommon.reportng.escape-output", "false");
			
			
			if(System.getenv("browser") !=null && !System.getenv("browser").isEmpty()) {
				
				config.setProperty("browser", System.getenv("browser"));
				browser = config.getProperty("browser");
				
			}else {
				browser = config.getProperty("browser");
			}

			if(browser.equals("chrome")){
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/executables/chromedriver");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			}else if(browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			}else if(browser.equals("safari")) {
				
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to"+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")));
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
		}
		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
		
	}

	public static boolean isElementPresent(By by) {
		
		//below try catch doesn't work
//		try {
//			driver.findElement(by);
//			
//		}catch(NoSuchElementException e){
//			log.debug("Element not found");
//			return false;
//		}
//		return true;
		return driver.findElements(by).size() >0 ;
	}
	
	public static void inputText(By by, String data) throws Exception {
		
		if( isElementPresent(by)) {
			driver.findElement(by).sendKeys(data);
		}
		else {
			
			Exception myException = new Exception("error while input!");  
			throw myException;
		}
		
	}
	
	
	public static void type(String locatorKey, String value) {
		//System.out.println(locatorKey+":"+value);
		
		if(locatorKey.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);
		}else if(locatorKey.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);
		}else if (locatorKey.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locatorKey))).sendKeys(value);
		}
		test.log(Status.INFO, "Typing "+value+" using locator "+locatorKey);

		log.info("Typing in an Element : " + locatorKey+" entered the value as : "+value);
	}
	
	public static void click(String locatorKey) {
		
		System.out.println(locatorKey);
		if(locatorKey.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();
		}else if(locatorKey.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
		}else if (locatorKey.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locatorKey))).click();

		}
		
		test.info("Clicking on locator: "+locatorKey);
	}
	
	public static void compareStrings(String exepected, String actual) throws IOException, InterruptedException {
		
		try {
			
			Assert.assertEquals(exepected, actual);
		}catch(Throwable t){
			String screenShotFile = TestUtils.captureScreenShot();
			Thread.sleep(2000);
			test.log(Status.FAIL, "Verication has failed with message "+t.getMessage());
			test.addScreenCaptureFromPath(screenShotFile);
			
		}
		
	}
	
static WebElement dropdown;

public static void select(String locatorKey, String value) {
		
		System.out.println(locatorKey);
		if(locatorKey.endsWith("_CSS")) {
			
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_XPATH")) {
			
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		}else if (locatorKey.endsWith("_ID")) {

			dropdown = driver.findElement(By.id(OR.getProperty(locatorKey)));

		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		
		test.info("Selecting from dropdown: "+locatorKey + " with value: "+ value);
	}
}
