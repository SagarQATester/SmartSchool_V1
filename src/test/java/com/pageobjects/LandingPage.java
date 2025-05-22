package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.ReusableComponents;

public class LandingPage extends ReusableComponents {

     WebDriver driver;
	
	@FindBy(xpath="//span[normalize-space()='Front Office']")
	WebElement frontOffice;

	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitUntilTheFrontOfficeModuleAppeares(WebDriver driver) {
		try {
			waitUntilElementIsVisible(driver, frontOffice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
