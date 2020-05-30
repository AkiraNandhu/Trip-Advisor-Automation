/** DO NOT CHANGE THIS CLASS. THIS CLASS IS FOR REFERENCE ONLY  **/

package com.cognizant.utilities;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties properties;
	static String baseUrl;
	
	@BeforeSuite
	public void driverSetup() throws IOException 
	{
		getDriver();

	} 
	
	@AfterSuite
	public void closeDriverSetup()
	{
		closeDriver();
	}
	@BeforeClass
	public void startApplication()
	{
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
	}
	public static Properties loadPropertyFile() throws IOException
	{
		FileInputStream fileInputStream=new FileInputStream("config.properties");
		
		properties=new Properties();
		properties.load(fileInputStream);
		
		return properties;
		
	}
    
	public static void getDriver() throws IOException 
	{
		
		loadPropertyFile();
		String browser=properties.getProperty("browser");
		baseUrl=properties.getProperty("url");
		String chromePath=properties.getProperty("chromepath");
		String firefoxPath=properties.getProperty("firefoxpath");


		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", firefoxPath);
	        FirefoxOptions firefoxOptions = new FirefoxOptions();
	        driver = new FirefoxDriver(firefoxOptions);
			
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver=new ChromeDriver();

		}	
			
	}
	
	public static void closeDriver()
	{
		driver.quit();
	}
}

