package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {
	
	public BasePage(WebDriver driver)
	{
		super(driver);
	}

	@Override
	protected String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	protected String getPageHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Alert waitForAlertPresent(long timeout) {
		wait=new WebDriverWait(driver,timeout);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		return alert;
		}
	
	public void waitForElementPresent(WebElement ele,long timeout)
	{
		wait=new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	

}
