package com.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class PageClass1 extends BasePage {
	
	//elements
	@FindBy(linkText="Basic Auth")
	private WebElement BasicAuthElement;
	
	@FindBy(xpath="//*[contains(text(),'Congratulations')]")
	private WebElement loginSuccessMsg;
	
	@FindBy(id="dropdown")
	private WebElement dropDown;
	
	@FindBy(linkText="Dropdown")
	private WebElement dropDownLink;
	 
	public PageClass1(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public String login(String username,String password)
	{
		String text=null;
		BasicAuthElement.click();
		try
		{
		//waitForAlertPresent(40);
		Alert alert=driver.switchTo().alert();
		alert.sendKeys(username + Keys.TAB + password);
		alert.accept();
		waitForElementPresent(loginSuccessMsg,10);
		text=loginSuccessMsg.getText();
		}
		catch(TimeoutException e)
		{
			
		}
		return text;
	}
	
	public void selectOption(String value)
	{
		dropDownLink.click();
		waitForElementPresent(dropDown,10);
		Select select=new Select(dropDown);
		select.selectByVisibleText(value);
		
		driver.navigate().back();
		try {
			Thread.sleep(2000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
