package com.reports;

public class ExtentLogger {

	public static void pass(String message)
	{
		ExtentManager.getExtentTest().pass(message);
	}
	
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}
	
	public static void info(String message)
	{
		ExtentManager.getExtentTest().info(message);
	}
}
