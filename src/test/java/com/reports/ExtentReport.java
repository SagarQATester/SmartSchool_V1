package com.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReport {

	public static ExtentReports extents;
	private static String path;

	public static void initReports()
	{
		if(Objects.isNull(extents)){	
	
			String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String reportName="TestReport-"+timeStamp+".html";
			path=System.getProperty("user.dir")+"\\extent-test-output"+reportName;
			ExtentSparkReporter sparkReporter= new ExtentSparkReporter(path).viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY,ViewName.AUTHOR }).apply();
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("Smart School Test Report");
			sparkReporter.config().setReportName("Smart School Web Application Test Report");
			
			extents=new ExtentReports();
			extents.attachReporter(sparkReporter);
			
	
		}
	}
		
		public static void flushReports() {
	
			extents.flush();
			
		}
		
		public static void createTest(String testcaseName, String featureName, String author, String browserName)
		{
			ExtentManager.setExtentTest(extents.createTest(testcaseName).assignCategory(featureName).assignAuthor(author).assignDevice(browserName));
		}
	
		
	
}
	

