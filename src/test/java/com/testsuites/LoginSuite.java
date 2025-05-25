/**
 * LoginSuite is a TestNG-based test class that verifies various login functionalities
 * of the application, including valid login, invalid login attempts, form validation, 
 * and password recovery processes.
 *
 * <p>Each test case is logged using the {@link com.reports.ExtentReport} for detailed reporting.</p>
 * <p>The class uses TestNG annotations to manage the execution order and grouping of test cases.</p>
 * 
 * <p>Extends {@link com.testcomponents.BaseClass} for common setup, teardown, and utility support.</p>
 * 
 * <p>Author: Sagar Kumbhar</p>
 * <p>Browser: Chrome</p>
 * 
 * @version 1.0
 */
package com.testsuites;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pageobjects.ForgotPasswordPage;
import com.pageobjects.LoginPage;
import com.reports.ExtentReport;
import com.testcomponents.BaseClass;

@Listeners(com.reports.ExtentListener.class)
public class LoginSuite extends BaseClass {

    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    String featureName = "Login Suite";
    String author = "Sagar Kumbhar";
    String browserName = "Chrome";

    /**
     * Test Case ID: TC_SLF_001
     * <p>Verifies that a user can successfully log in with valid credentials.</p>
     *
     * @throws InterruptedException if the thread is interrupted during wait
     */
    @Test(priority = 1, enabled = true,
          description = "Verify that the user can successfully log in with valid credentials",
          groups = { "Regression", "Smoke" })
    public void TC_SLF_001() throws InterruptedException {
        String testcaseName = "Verify that the user can successfully log in with valid credentials (correct username and password).";
        ExtentReport.createTest(testcaseName, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.LoginToApplicationBySuperAdmin("dyansagartuitionclasses@gmail.com", "sagar@201202");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    /**
     * Test Case ID: TC_SLF_002
     * <p>Verifies that the application shows a proper error message when invalid login credentials are used.</p>
     */
    @Test(priority = 2, enabled = true,
          description = "Verify Super Admin login with invalid credentials shows proper error message",
          groups = { "Regression", "Smoke" })
    public void TC_SLF_002() {
        String testcaseTitle = "To verify whether the Super Admin can log in to the application with the invalid credentials, proper error message should be displayed";
        ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.loginToTheApplicationWithInvalidCredentials("sagar", "sagar@201");
        Assert.assertTrue(loginPage.verifyProperErrorMessageDisplayedOnScreen("Invalid Username Or Password"));
    }

    /**
     * Test Case ID: TC_SLF_003
     * <p>Verifies that leaving both username and password fields empty shows both error messages.</p>
     */
    @Test(priority = 3, enabled = true,
          description = "Leave the username and password fields empty and submit the form, ensuring that both error messages are shown.",
          groups = { "Regression" })
    public void TC_SLF_003() {
        String testcaseTitle = "Leave the username and password fields empty and submit the form, ensuring that both error messages are shown.";
        ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.clickOnSignIn();
        Assert.assertTrue(loginPage.verifyEmptyErrorMessage("The Username field is required.", "The Password field is required."));
    }

    /**
     * Test Case ID: TC_SLF_004
     * <p>Verifies that leaving only the password field empty shows the appropriate error message.</p>
     */
    @Test(priority = 4, enabled = true,
          description = "Enter a valid username but an empty password and submit the form to check for the required password error message.",
          groups = { "Regression" })
    public void TC_SLF_004() {
        String testcaseTitle = "Enter a valid username but an empty password and submit the form to check for the required password error message.";
        ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.enterUserName("dyansagartuitionclasses@gmail.com");
        loginPage.clickOnSignIn();
        Assert.assertTrue(loginPage.verifyEmptyErrorMessage("", "The Password field is required."));
    }

    /**
     * Test Case ID: TC_SLF_005
     * <p>Verifies that leaving only the username field empty shows the appropriate error message.</p>
     */
    @Test(priority = 5, enabled = true,
          description = "Enter a valid password but an empty username and submit the form to check for the required username error message.",
          groups = { "Regression" })
    public void TC_SLF_005() {
        String testcaseTitle = "Enter a valid password but an empty username and submit the form to check for the required username error message.";
        ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.enterPassword("sagar@123");
        loginPage.clickOnSignIn();
        Assert.assertTrue(loginPage.verifyEmptyErrorMessage("The Username field is required.", ""));
    }

    /**
     * Test Case ID: TC_SLF_006
     * <p>Verifies that the application is able to send a password recovery email when the user clicks "Forgot Password".</p>
     */
    @Test(priority = 6, enabled = true,
          description = "Validate whether the application is able to send password recovery mail to the user when user forgot the password",
          groups = { "Regression" })
    public void TC_SLF_006() {
        String testcaseTitle = "Validate whether the application able to sent password recovery mail to the user when user forgot the password";
        ExtentReport.createTest(testcaseTitle, featureName, author, browserName);
        loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordLink();
        forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.entersUserEmailAddress("dyansagartuitionclasses@gmail.com");
        forgotPasswordPage.clickOnSubmitbutton();
        boolean status = loginPage.verifyApplicationAbleToSentPasswordRecoveryMail("Please Check Your Email To Recover Your Password");
        Assert.assertTrue(status);
    }
}
