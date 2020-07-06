package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		driver.get(baseURL);

		/*
		 * For all this logger info's you can add ay info you want, or nay warning you
		 * want.
		 */
		logger.info("Url is opened");

		// Now here we have to call methods from the LoginPage Object class
		// For this we will create the object

		LoginPage lp = new LoginPage(driver); // This new loginpage(driver) was showing error asking us to make
												// loginpage constructor as public and we did that
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickSubmit();

		// Go to Homepage and verify the Title of the page
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}

}
