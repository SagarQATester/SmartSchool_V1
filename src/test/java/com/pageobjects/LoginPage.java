package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.reports.ExtentLogger;
import com.utils.ReusableComponents;

public class LoginPage extends ReusableComponents {

	WebDriver driver;
	LandingPage landingPage = new LandingPage(driver);

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(css = "[type='submit']")
	WebElement signIn;

	@FindBy(xpath = "//span[normalize-space()='Front Office']")
	WebElement frontOffice;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement invalidLogin;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterUserName(String uname) {

		sendKey_custom(userName, "Username", uname);
	}

	public void enterPassword(String userPassword) {
		sendKey_custom(password, "Password", userPassword);
	}

	public void clickOnSignIn() {
		click_custom(signIn, "Sign In");
	}

	public void LoginToApplicationBySuperAdmin(String username, String password) {
		try {
			enterUserName(username);
			enterPassword(password);
			clickOnSignIn();
			waitUntilElementIsVisible(driver, frontOffice);
			ExtentLogger.pass("The Super Admin Successfully login into the application");

		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("Super Admin user not able to login  into the application");
		}

	}

	public void loginToTheApplicationWithInvalidCredentials(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnSignIn();
	}

	public boolean verifyProperErrorMessageDisplayedOnScreen(String expectedError) {
		boolean errorStatus = false;
		if (getErrorMessgae(invalidLogin).equalsIgnoreCase(expectedError)) {
			ExtentLogger.pass("Proper Error message displayed on the screen");
			ExtentLogger.pass("Error Message :" + getErrorMessgae(invalidLogin));
			return errorStatus = true;

		} else {
			ExtentLogger.fail("Invalid error message displayed on screen");
			return errorStatus = false;
		}
	}

}
