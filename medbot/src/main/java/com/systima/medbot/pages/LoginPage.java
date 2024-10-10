package com.systima.medbot.pages;

import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.LogStatus;
import com.systima.medbot.base.TestBase;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginPage extends TestBase{
	
	//Page Factory
	
	@FindBy(css=".email-id")
	WebElement username;
	
	@FindBy(id="password1")
	WebElement password;
	
	@FindBy(css=".btn-sm")
	WebElement loginBtn;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	// extent report
    public void setUpExtentReports() {
        setExtent();
    }
	
	//Actions:

    // data passed via config.properties file		
//		public LoginPage login(String un, String pwd) throws InterruptedException{
//
//			username.sendKeys(un);
//			password.sendKeys(pwd);
//			
//			    	JavascriptExecutor js = (JavascriptExecutor)driver;
//			    	js.executeScript("arguments[0].click();", loginBtn);
//			    	
//			return new LoginPage();
//		}
    
 
    // Helper Methods (Excel Data Reading)
 	private String[][] getExcelData() throws BiffException, IOException {
 		try (InputStream excelStream = getClass().getClassLoader().getResourceAsStream("medbot.xls")) {
 			Workbook workbook = Workbook.getWorkbook(excelStream);
 			Sheet sheet = workbook.getSheet(0); // sheet name

 			int rowCount = sheet.getRows();
 			int columnCount = sheet.getColumns();
 			String[][] testData;

 			if (rowCount == 1) {
 				testData = new String[1][columnCount];
 				for (int j = 0; j < columnCount; j++) {
 					testData[0][j] = sheet.getCell(j, 0).getContents();
 				}
 			} else {
 				testData = new String[rowCount - 1][columnCount];
 				for (int i = 1; i < rowCount; i++) {
 					for (int j = 0; j < columnCount; j++) {
 						testData[i - 1][j] = sheet.getCell(j, i).getContents();
 					}
 				}
 			}
 			return testData;
 		}
 	}

 	// Data Provider
 	@DataProvider(name = "loginData")
 	public Object[][] loginDataProvider() throws BiffException, IOException {
 		return getExcelData();
 	}

    
    
		
		// Optionally, provide login method for DataProvider
	    public LoginPage loginDataProvider(String un, String pwd) {
	    	extentTest = extent.startTest("Enter the Medbot Credentials");
	    	extentTest.log(LogStatus.INFO,"Enter the Email ID");
	        username.sendKeys(un);
	        extentTest.log(LogStatus.INFO,"Enter the Password");
	        password.sendKeys(pwd);
	        
	        extentTest.log(LogStatus.INFO,"Click the Submit button");
	        // Using JavaScriptExecutor to click the login button
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", loginBtn);
	        
	        return this; // return the current instance
	    }
	
	
	
}
