package com.testcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

	public static WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp()
	{
		driver= new ChromeDriver();
		driver.get("http://localhost/gyansagarclasses/site/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
}
