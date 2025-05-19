package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.reports.ExtentLogger;

public class ReusableComponents {

	WebDriver driver;

	public ReusableComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static void sendKey_custom(WebElement ele, String fieldName, String valueToBeSent) {
		try {

			ele.sendKeys(valueToBeSent);
			System.out.println(valueToBeSent+" : value successfully entered in the "+fieldName+" field");
			ExtentLogger.pass(valueToBeSent+" : value successfully entered in the "+fieldName+" field");
		} catch (Exception e) {
				e.printStackTrace();
				ExtentLogger.fail("Not able to enter the value ["+valueToBeSent+"] in the "+fieldName+" field");
				Assert.fail("Not able to enter the value ["+valueToBeSent+"] in the "+fieldName+" field");
				
		}

	}

	public static void click_custom(WebElement ele, String fieldName) {
		try {

			ele.click();
			System.out.println("Clicked on "+fieldName);
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Not able to click on"+ fieldName);
				
		}

	}


}
