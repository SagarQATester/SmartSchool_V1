/**
 * ForgotPasswordPage represents the page object model for the "Forgot Password" screen.
 * It encapsulates the elements and actions related to password recovery functionality
 * in the application.
 *
 * <p>Extends {@link com.utils.ReusableComponents} to use shared WebDriver utility methods.</p>
 * 
 * <p>This class uses Selenium's PageFactory to initialize web elements.</p>
 * 
 * @author  
 * @version 1.0
 */
package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.ReusableComponents;

public class ForgotPasswordPage extends ReusableComponents {

    WebDriver driver;

    /** Email input field for entering user email address */
    @FindBy(name = "email")
    private WebElement userEmail;

    /** Submit button to trigger password recovery */
    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    /**
     * Constructor to initialize the ForgotPasswordPage with WebDriver.
     * Uses PageFactory to locate elements.
     *
     * @param driver WebDriver instance
     */
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the user's email address in the email field.
     *
     * @param email The email address to be entered for password recovery
     */
    public void entersUserEmailAddress(String email) {
        sendKey_custom(userEmail, "Email", email);
    }

    /**
     * Clicks the submit button to initiate the password recovery process.
     */
    public void clickOnSubmitbutton() {
        click_custom(submitButton, "Submit button");
    }
}
