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

	@Test(enabled = true, description = "To verify whether the Super Admin can log in to the application with the invalid credentials, proper error message should be displayed ", groups = {
			"Regression", "Smoke" })
	public void TC_SLF_002() {

		String testcaseTitle = "To verify whether the Super Admin can log in to the application with the invalid credentials, proper error message should be displayed ";
		ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
		loginPage = new LoginPage(driver);
		loginPage.loginToTheApplicationWithInvalidCredentials("sagar", "sagar@201");
		Assert.assertTrue(loginPage.verifyProperErrorMessageDisplayedOnScreen("Invalid Username Or Password"));
	}

	@Test(enabled = true, description = "Leave the username and password fields empty and submit the form, ensuring that both error messages are shown.", groups = {
			"Regression" })
	public void TC_SLF_003() {

		String testcaseTitle = "Leave the username and password fields empty and submit the form, ensuring that both error messages are shown.";
		ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignIn();
		Assert.assertTrue(loginPage.verifyEmptyErrorMessage("The Username field is required.",
				"The Password field is required."));

	}

	@Test(enabled = true, description = "Enter a valid username but an empty password and submit the form to check for the required password error message.", groups = {
			"Regression" })
	public void TC_SLF_004() {

		String testcaseTitle = "Enter a valid username but an empty password and submit the form to check for the required password error message.";
		ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
		loginPage = new LoginPage(driver);
		loginPage.enterUserName("dyansagartuitionclasses@gmail.com");
		loginPage.clickOnSignIn();
		Assert.assertTrue(loginPage.verifyEmptyErrorMessage("",
				"The Password field is required."));

	}

	@Test(enabled = true, description = "Enter a valid password but an empty username and submit the form to check for the required username error message.", groups = {
			"Regression" })
	public void TC_SLF_005() {

		String testcaseTitle = "Enter a valid password but an empty username and submit the form to check for the required username error message.";
		ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
		loginPage = new LoginPage(driver);
		loginPage.enterPassword("sagar@123");;
		loginPage.clickOnSignIn();
		Assert.assertTrue(loginPage.verifyEmptyErrorMessage("The Username field is required.",
				""));

	}

}
