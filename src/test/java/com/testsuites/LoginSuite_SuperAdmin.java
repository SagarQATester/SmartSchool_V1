package com.testsuites;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pageobjects.LoginPage_SuperAdmin;
import com.reports.ExtentReport;
import com.testcomponents.BaseClass;

@Listeners(com.reports.ExtentListener.class)

public class LoginSuite_SuperAdmin extends BaseClass {

	LoginPage_SuperAdmin loginPage;
	String featureName="Login Suite";
	String author="Sagar Kumbhar";
	@Test(description = "Verify that the user can successfully log in with valid credentials (correct username and password).")
	public void TC_SLF_001() throws InterruptedException
	{
		// Webhook added >>Now run the build by webhook
		String testcaseName="Verify that the user can successfully log in with valid credentials (correct username and password).  ";
		ExtentReport.createTest(testcaseName, featureName, author);
		loginPage=new LoginPage_SuperAdmin(driver);
		loginPage.LoginToApplicationBySuperAdmin("dyansagartuitionclasses@gmail.com", "sagar@201202");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}
}
