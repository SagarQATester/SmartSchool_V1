package com.testsuites;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pageobjects.LoginPage;
import com.reports.ExtentReport;
import com.testcomponents.BaseClass;

@Listeners(com.reports.ExtentListener.class)

public class LoginSuite extends BaseClass {

	LoginPage loginPage;
	String featureName = "Login Suite";
	String author = "Sagar Kumbhar";
	String browserName = "Chrome";

	@Test(description = "Verify that the user can successfully log in with valid credentials", groups = { "Reggression",
			"Smoke" })
	public void TC_SLF_001() throws InterruptedException {
		// Webhook added >>Now run the build by webhook
		String testcaseName = "Verify that the user can successfully log in with valid credentials (correct username and password).  ";
		ExtentReport.createTest(testcaseName, featureName, author, browserName);
		loginPage = new LoginPage(driver);
		loginPage.LoginToApplicationBySuperAdmin("dyansagartuitionclasses@gmail.com", "sagar@201202");
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}

	@Test(enabled=true,description = "To verify whether the Super Admin can log in to the application with the invalid credentials, proper error message should be displayed ", groups = {
			"Regression", "Smoke" })
	public void TC_SLF_002() {

		String testcaseTitle="To verify whether the Super Admin can log in to the application with the invalid credentials, proper error message should be displayed ";
		ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
		loginPage=new LoginPage(driver);
		loginPage.loginToTheApplicationWithInvalidCredentials("sagar", "sagar@201");
		Assert.assertTrue(loginPage.verifyProperErrorMessageDisplayedOnScreen("Invalid Username Or Password"));
	}
}
