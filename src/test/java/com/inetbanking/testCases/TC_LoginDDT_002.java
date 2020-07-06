package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	/*
	 * So now here everthing will be called from the Base class but for testing we
	 * will require two mehtods. one is test method and one is data provider method.
	 * So here you see, we have test case and the other is Data Provider method
	 */
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username is provided");
		lp.setPassword(pwd);
		logger.info("password is provided");
		lp.clickSubmit();

		Thread.sleep(3000);

		// Now we have to call the method of ALert we have written in our test case
		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // This will close the alert
			driver.switchTo().defaultContent(); // again go back to login paage
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout(); // click logout
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // close logout alert
			driver.switchTo().defaultContent(); // switch back to main page required

		}

	}

	// verify the alerts are present or not, for this we will create a user defined
	// method.
	/*
	 * So here if there is a Alert then the statment will be true. But if the
	 * statement is false then we have to see that exception is caught and hence we
	 * put that thing in try and catch block. Please rememeber the importance of a
	 * return statement here. this whole code written can be used for other things
	 * as well. We can use this in Base Class as well.
	 * 
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = "C:\\Users\\u6032884\\OneDrive - Thomson Reuters Incorporated\\Desktop\\Practice Practice\\inetBankingV1\\src\\test\\java\\com\\inetbanking\\testData\\LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1"); // we are calling this thing from XLUtils to get the row count

		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		// Create 2 dimensional to store the data in that 2 dimensional array

		String logindata[][] = new String[rownum][colcount];

// now write the for loop to get the data, here below we start with 1 as 0 is

// header part.
		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colcount; j++)

			{
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 1 0
			}
		}
		return logindata;

	}

}
