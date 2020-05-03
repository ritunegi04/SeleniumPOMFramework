package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.PageClass1;

public class PageClass1Test extends TestBase {
	
	/*
	 * @Test public void basicAuthTest() throws Exception { String
	 * successMsg=page.getInstance(PageClass1.class).
	 * login(prop.getProperty("username"), prop.getProperty("password"));
	 * Assert.assertEquals(successMsg,
	 * "Congratulations! You must have the proper credentials."); }
	 */
	@Test
	public void selectDropDown() throws Exception
	{
		page.getInstance(PageClass1.class).selectOption("Option 1");
	}

}
