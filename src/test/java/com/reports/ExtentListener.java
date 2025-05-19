package com.reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testcomponents.BaseClass;

public class ExtentListener extends BaseClass implements ITestListener {

	public void saveScreenshot(String msg) {
		String byteImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		ExtentManager.getExtentTest().pass(msg,
				MediaEntityBuilder.createScreenCaptureFromBase64String(byteImage).build());
	}

	public void saveFailureScreenshot(String msg) {
		String byteImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		ExtentManager.getExtentTest().fail(msg,
				MediaEntityBuilder.createScreenCaptureFromBase64String(byteImage).build());

	}

	public void onTestStart(ITestResult result) {

		//ExtentReport.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentManager.getExtentTest().log(Status.PASS,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + ": Testcase Passed", ExtentColor.GREEN));
		saveScreenshot("Testcase Passed !!!");

	}

	public void onTestFailure(ITestResult result) {
		ExtentManager.getExtentTest().log(Status.FAIL,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + ": Testcase failed ", ExtentColor.RED));
		saveFailureScreenshot("Testcase Failed !!!");
	}

	public void onTestSkipped(ITestResult result) {

		ExtentManager.getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName()+"Testcase Skipped", ExtentColor.ORANGE));
		
		
	}

	public void onStart(ITestContext context) {

		ExtentReport.initReports();
	}

	public void onFinish(ITestContext context) {

		ExtentReport.flushReports();
	}

}
