package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
@Test(groups= {"Sanity"})
public class TestClass2 extends TestBase {
	
	@Test(groups= {"Smoke"})
	public void check()
	{
		log.info("inside check method");
		System.out.println("check");
	}
	
	
	public void check1()
	{
		log.info("inside check method");
		System.out.println("check1");
	}
	@Test()
	public void check2()
	{
		log.info("inside check method");
		System.out.println("check2");
	}

}
