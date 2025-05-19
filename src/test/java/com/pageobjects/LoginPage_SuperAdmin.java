package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utils.ReusableComponents;

public class LoginPage_SuperAdmin extends ReusableComponents {

	WebDriver driver;
	
	@FindBy (name="username")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(css="[type='submit']")
	WebElement signIn;
	
	
	
	
	public LoginPage_SuperAdmin(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void enterUserName(String uname) {
		
		sendKey_custom(userName, "Username",uname);
	}
	
	public void enterPassword(String userPassword)
	{
		sendKey_custom(password, "Password", userPassword);
	}

	public void clickOnSignIn()
	{
		click_custom(signIn, "Sign In");
	}
	
	public void LoginToApplicationBySuperAdmin(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnSignIn();
	}
}
