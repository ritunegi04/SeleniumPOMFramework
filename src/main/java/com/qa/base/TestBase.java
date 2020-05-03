package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.qa.pages.Page;
import com.qa.pages.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	static protected WebDriver driver;
	static protected Properties prop;
	String browser;
	public Page page;
	
	public TestBase() 
	
		{
			prop=new Properties();
			try
			{
			FileInputStream fin=new FileInputStream(new File(System.getProperty("user.dir"))+ "/src/main/java/com/qa/resources/app.config");
			prop.load(fin);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

		}
	
	
	@BeforeClass
	public void setup()
	{
		browser=prop.getProperty("browser");
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		page=new BasePage(driver);
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
