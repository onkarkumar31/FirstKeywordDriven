package com.mw.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.mw.base.TestBase;

public class TestUtils extends TestBase{
	
	public static String captureScreenShot(String scrName) throws IOException {
		
		String ScreenShotPath =System.getProperty("user.dir")+"/target/surefire-reports/html";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotName = "";
		
		try {
			screenShotName = ScreenShotPath+"/"+scrName+".jpg";
			FileUtils.copyFile(scrFile, new File(screenShotName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotName;
		
	}
	
	public static String captureScreenShot() throws IOException {
		
		Date d = new Date();
		String scrName = d.toString().replaceAll(":", "_").replaceAll(" ", "_");
		String ScreenShotPath =System.getProperty("user.dir")+"/target/surefire-reports/html";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotName = "";
		
		try {
			screenShotName = ScreenShotPath+"/"+scrName+".jpg";
			FileUtils.copyFile(scrFile, new File(screenShotName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotName;
		
	}
	
	@DataProvider(name="dp")
	public Object[][] getCustData(Method m){
		String sheetName = m.getName();
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
	
	public static boolean isTestRunnable(String testName) {
		String sheetName = "RunModes";
		int rowNum = excel.getRowCount(sheetName);
		
		for(int i=2;i<=rowNum;i++) {
			
			String runMode = excel.getCellData(sheetName, testName, i);
			
			if(runMode.equalsIgnoreCase("Y")) {
				return true;
			}else {
				return false;
			}
		}
		return false;
		
	}

}
