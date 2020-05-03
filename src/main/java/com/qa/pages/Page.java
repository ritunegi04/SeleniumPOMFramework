package com.qa.pages;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	protected WebDriver driver;
	protected WebDriverWait wait;
	public Page(WebDriver driver)
	{
		this.driver=driver;
	}
	
	protected abstract String getPageTitle();
	protected abstract String getPageHeader();
	
	
	  public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) throws Exception {
	   return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver); }
	  
	  
	  
	  
	  
	  
	
	
	
}
