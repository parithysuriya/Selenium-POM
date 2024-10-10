package com.systima.medbot.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.systima.medbot.base.TestBase;
import com.systima.medbot.pages.LoginPage;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;

    // Constructor
    public LoginPageTest() {
        super();
    }

    // Set up the ExtentReports before running tests
    @BeforeTest
    public void setUpExtentReports() {
        setExtent();
    }

    // Initialize WebDriver and page object before each test method
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }

    // Test case for login functionality via CONFIG.PROPERTIES FILE
//    @Test
//    public void loginTest() throws InterruptedException {
//        extentTest = extent.startTest("loginTest");
//        loginPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
//        extentTest.log(LogStatus.PASS, "Login test passed");
//    }
    
    // Data passed via xls
    
    @Test(dataProvider = "loginData", dataProviderClass = LoginPage.class)
    public void medbotlogin(String un, String pwd) {

    	loginPage = loginPage.loginDataProvider(un,pwd);
    }
    

    // After each test, log the result and close the browser
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        Down(result);  // Pass the ITestResult object to the Down() method for reporting
        driver.quit();  // Close the browser after test
    }

    // Close the ExtentReports after all tests are executed
    @AfterTest
    public void setEndReport() {
        endReport();
    }
    
    @AfterSuite
    public void sendEmail () {
    	sendEmailReport();
    }
    
    
}
