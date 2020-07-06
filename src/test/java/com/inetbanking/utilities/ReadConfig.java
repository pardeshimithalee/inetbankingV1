package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	// Create object for Propertoes class import from util.properties
	Properties pro; // this is an object of the properties file

	// Then in Object class we created a constructor here also we will do the same
	// thing.
	public ReadConfig() {
		File src = new File(
				"C:\\Users\\u6032884\\OneDrive - Thomson Reuters Incorporated\\Desktop\\Practice Practice\\inetBankingV1\\Configuration\\config.properties"); // path
																																								// of
																																								// the
																																								// config.propeties
																																								// file

		try {
			FileInputStream fis = new FileInputStream(src); // We are reading the data from config.properties and hence
															// we import the fileinput stream.To read the data we have
															// to read the data in the file mode.
			pro = new Properties();
			pro.load(fis); // this will load the complete file, And he wrote the try catch block to catch
							// the exception if the config file is not loaded
		} catch (IOException e) {
			System.out.println("Exception is " + e.getMessage());
		}
		/*
		 * So, we write this constructor the test case will come for the data and take
		 * it from here. once I start this class the constructor will be ignored and
		 * once the property file is loaded then we need read the values by adding the
		 * methods The below things I ahve written are very case sensitive write them as
		 * they are in the config files.
		 */
	}

	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getUsername() {
		String username = pro.getProperty("username");
		return username;
	}

	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}

	public String getChromePath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}

	public String getIEPath() {
		String iepath = pro.getProperty("iepath");
		return iepath;
	}

	public String getFirefoxPath() {
		String firefoxpath = pro.getProperty("firefoxpath");
		return firefoxpath;
	}

}
