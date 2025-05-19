package com.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	
	public static ThreadLocal<ExtentTest> extTest= new ThreadLocal<ExtentTest>();
	
	public static ExtentTest getExtentTest()
	{
		return extTest.get();
	}
	
	public static void  setExtentTest(ExtentTest test)
	{
		extTest.set(test);
	}
	
	
}
