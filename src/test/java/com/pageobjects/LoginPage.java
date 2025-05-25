/**
 * LoginPage represents the page object model for the login screen of the application.
 * It encapsulates the web elements and user actions that can be performed on the login page.
 *
 * <p>This class provides methods to perform login operations, handle errors,
 * and verify successful or unsuccessful login attempts using Selenium WebDriver.</p>
 *
 * <p>Extends {@link com.utils.ReusableComponents} for common reusable actions.</p>
 * 
 * @author  
 * @version 1.0
 */
package com.pageobjects;

import java.util.List;

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

    @FindBy(xpath = "//span[@class='text-danger']/p")
    List<WebElement> errorMessages;

    @FindBy(xpath="(//span[@class='text-danger']/p)[1]")
    WebElement usernameEmptyError;	

    @FindBy(xpath="(//span[@class='text-danger']/p)[2]")
    WebElement passwordEmptyError;

    @FindBy(linkText = "Forgot Password?")
    WebElement forgotPassword;

    @FindBy(css=".alert-success")
    private WebElement successMessage;

    /**
     * Constructor to initialize the LoginPage with WebDriver.
     *
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Enters the username in the username field.
     *
     * @param uname Username string
     */
    public void enterUserName(String uname) {
        sendKey_custom(userName, "Username", uname);
    }

    /**
     * Enters the password in the password field.
     *
     * @param userPassword Password string
     */
    public void enterPassword(String userPassword) {
        sendKey_custom(password, "Password", userPassword);
    }

    /**
     * Clicks on the Sign In button.
     */
    public void clickOnSignIn() {
        click_custom(signIn, "Sign In");
    }

    /**
     * Logs in to the application using Super Admin credentials.
     *
     * @param username Username of the Super Admin
     * @param password Password of the Super Admin
     */
    public void LoginToApplicationBySuperAdmin(String username, String password) {
        try {
            enterUserName(username);
            enterPassword(password);
            clickOnSignIn();
            waitUntilElementIsVisible(driver, frontOffice);
            ExtentLogger.pass("The Super Admin Successfully login into the application");
        } catch (Exception e) {
            ExtentLogger.fail("Super Admin user not able to login into the application");
        }
    }

    /**
     * Attempts to login with invalid credentials.
     *
     * @param username Invalid username
     * @param password Invalid password
     */
    public void loginToTheApplicationWithInvalidCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickOnSignIn();
    }

    /**
     * Verifies whether the proper error message is displayed on screen.
     *
     * @param expectedError Expected error message
     * @return true if expected error matches actual, false otherwise
     */
    public boolean verifyProperErrorMessageDisplayedOnScreen(String expectedError) {
        boolean errorStatus = false;
        if (getErrorMessgae(invalidLogin).equalsIgnoreCase(expectedError)) {
            ExtentLogger.pass("Proper Error message displayed on the screen");
            ExtentLogger.pass("Error Message :" + getErrorMessgae(invalidLogin));
            errorStatus = true;
        } else {
            ExtentLogger.fail("Invalid error message displayed on screen");
        }
        return errorStatus;
    }

    /**
     * Verifies whether appropriate empty field error messages are displayed.
     *
     * @param userNameEmpty Expected username empty error
     * @param passwordEmpty Expected password empty error
     * @return true if correct messages are displayed, false otherwise
     */
    public boolean verifyEmptyErrorMessage(String userNameEmpty, String passwordEmpty) {
        boolean errorStatus = false;
        if (errorMessages != null) {
            for (WebElement errorMessage : errorMessages) {
                if (errorMessage.getText().equalsIgnoreCase(userNameEmpty)
                        || errorMessage.getText().equalsIgnoreCase(passwordEmpty)) {
                    ExtentLogger.pass("Proper Error Message Displayed On Screen ");
                    errorStatus = true;
                } else {
                    ExtentLogger.fail("Wrong Error Message Displayed on the screen");
                    errorStatus = false;
                }
            }
        } else {
            ExtentLogger.fail("Error Message not displayed on the screen ");
        }
        return errorStatus;
    }

    /**
     * Verifies the username empty error message. (Currently incomplete implementation)
     *
     * @param usernameEmptyErrorMsg Expected error message for empty username
     */
    public void verifyUsernameEmptyErrorMessage(String usernameEmptyErrorMsg) {
        boolean errorStatus = false;
        if (usernameEmptyError.getText().equalsIgnoreCase(usernameEmptyErrorMsg)) {
            ExtentLogger.pass("Correct username empty error displayed.");
        } else {
            ExtentLogger.fail("Incorrect or no username empty error displayed.");
        }
    }

    /**
     * Clicks on the 'Forgot Password?' link.
     */
    public void clickOnForgotPasswordLink() {
        click_custom(forgotPassword, "Forgot Password Link");
    }

    /**
     * Verifies that the application is able to send a password recovery mail.
     *
     * @param expectedMsg Expected success message
     * @return true if the message matches, false otherwise
     */
    public boolean verifyApplicationAbleToSentPasswordRecoveryMail(String expectedMsg) {
        boolean mailStatus = false;
        waitUntilElementIsVisible(driver, successMessage);
        if (successMessage.getText().equalsIgnoreCase(expectedMsg)) {
            ExtentLogger.pass("Successfully Sent Password Recovery Mail :" + successMessage.getText());
            mailStatus = true;
        } else {
            ExtentLogger.fail("Application unable to send Password Recovery Mail");
        }
        return mailStatus;
    }
}
