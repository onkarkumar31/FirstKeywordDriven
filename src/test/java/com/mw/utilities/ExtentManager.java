package com.mw.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent == null) {
			
			extent = new ExtentReports();
			
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/surefire-reports/html/extent.html");
			extent.attachReporter(spark);
		}
		
		return extent;
		
	}

}
