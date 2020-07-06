package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver; // local driver

	// Now write a constructor
	public LoginPage(WebDriver rdriver) // this r is remote driver
	{
		// here we need to initiate the driver
		ldriver = rdriver;

		// specify the page factory class
		PageFactory.initElements(rdriver, this);
	}

	// Now identifying the elements present in the login page
	// import FindBy, cache look up, and WebElement
	// For these three elements we will write our action methods
	// So these below things are Elements
	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtUserName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//a[@href='Logout.php']")
	@CacheLookup
	WebElement lnkLogout;

	// And these below things are Action methods
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickSubmit() {
		btnLogin.click();
	}

	public void clickLogout() {
		lnkLogout.click();
	}

}
