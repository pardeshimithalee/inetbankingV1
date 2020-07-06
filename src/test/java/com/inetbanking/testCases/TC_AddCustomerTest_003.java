package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		// For entering the website
		lp.setUserName(username);
		logger.info("Username is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		/*WebElement element=driver.findElement(By.xpath("//div[@id='transparentInner']"));
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
*/

		Thread.sleep(3000);

		// Now Start adding the details of New customer

		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		logger.info("Providing the customer details.....");
		addcust.clickAddNewCustomer();
		addcust.custName("Mithalee");
		addcust.custgender("female");
		addcust.custdob("07", "15", "1991");
		Thread.sleep(3000); // Because it takes time for the date to get entered
		addcust.custaddress("India");
		addcust.custcity("Hyderabad");
		addcust.custstate("Telangana");
		addcust.custpinno("500036");
		addcust.custtelephoneno("9703978841");
		// every customer will have a unique email id
		// so we have to generate random email id. we cannot hard core the values.
		// we have to generate random data dynamically. we define user defined function
		// for this
		String email = randomstring() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();

		Thread.sleep(3000);
		// Validate
		logger.info("Validation is started....");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");

		if (res == true) {
			Assert.assertTrue(true);
			logger.info("testcase is passed");
		} else {
			logger.info("testcase is failed");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}

	}
	
	//This should be part of Base Class thats why commeting it and taking it to the base class
	/*public String randomstring() //so whenever we call this method this will generate a random string
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8); //this will take 8 characters and generate a string
		return generatedstring;
	}
	
	//if we want random number we can write random number method. 
	//We can make this as Base class part
	
	public static String randomNum()
	{
		String generatestring2=RandomStringUtils.randomNumeric(4); //numberic it will generate the number with this number
		return(generatestring2);
		
	}*/
	
	
	
	

}
