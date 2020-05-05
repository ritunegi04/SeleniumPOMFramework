package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.pages.Page;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.pages.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	static protected WebDriver driver;
	static protected Properties prop;
	ExtentHtmlReporter htmlreporter;
	ExtentReports report;
	ExtentTest extentTest;
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
	
	@BeforeSuite
	public void beforeSuite()
	{
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/report.html");
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("UI Testing Report");
		report=new ExtentReports();
		report.attachReporter(htmlreporter);
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
	
	@BeforeMethod
	public void beforeMethod(Method m)
	{
		extentTest=report.createTest(m.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		String methodName=result.getMethod().getMethodName();
		if(result.getStatus()==ITestResult.SUCCESS) 
		{
			extentTest.log(Status.PASS,methodName + " Passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE) 
		{
			extentTest.log(Status.FAIL,methodName + " Failed");
			String exception=Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.log(Status.FAIL, "Exception : "+exception);
			try {
				
				extentTest.fail("Attaching screenshot", 
						MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"/Screenshots/"+methodName+".png").build());
			}
	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(result.getStatus()==ITestResult.SKIP) 
		{
			extentTest.log(Status.SKIP,methodName + " Skipped");
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	@AfterSuite
	public void afterSuite()
	{
		report.flush();
	}
}
