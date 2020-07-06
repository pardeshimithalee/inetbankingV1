package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	/*
	 * So Base class will have things which are common for all the test cases. So
	 * Here we define the basic things Like variable and all
	 */

	// define common variables

	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	/*
	 * The above variables values were removed because we made config.properties
	 * method. And to call the ReadConfig methods we have written we have to create
	 * objects. the varibales were replaced by calling them here.
	 */
	public static Logger logger;

	/*
	 * Please see this carefully For Chrome he tool the path to be written here from
	 * Driver--> Chrome Exe we have here--> see the properties of it--> we get the
	 * path Then, he removed the first part of it till drivers:
	 * C:\\Users\\u6032884\\OneDrive - Thomson Reuters
	 * Incorporated\\Desktop\\Practice Practice\\inetBankingV1 Then in that place he
	 * gave the path in such a way that the thing will look upto the project for
	 * path Channge the slashed to fo rward rather than backward what we have first
	 */
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		// Now we have to set up things for Log4j as well here
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");

		/*
		 * So here we do the set up for running things through xml for different
		 * browsers. So have @Parameter above @Before Class and define variable like
		 * "String br".
		 */
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

	}

	// Creating a emial id using random method. Becuase you cannot hard code that
	// value as it changes for every customer.
	public String randomstring() // so whenever we call this method this will generate a random string
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8); // this will take 8 characters and generate a
																		// string
		return generatedstring;
	}

	// if we want random number we can write random number method.
	// We can make this as Base class part

	public static String randomNum() {
		String generatestring2 = RandomStringUtils.randomNumeric(4); // numberic it will generate the number with this
																		// number
		return (generatestring2);

	}

}
