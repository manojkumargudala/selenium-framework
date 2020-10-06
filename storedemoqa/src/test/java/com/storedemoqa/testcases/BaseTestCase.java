package com.storedemoqa.testcases;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoqa.selenium.BaseDriverInitilization;
import com.storedemoqa.pageobjects.FramePageObject;
import com.storedemoqa.pageobjects.HomePageObject;
import com.storedemoqa.pageobjects.NestedFramePageObject;

public class BaseTestCase extends BaseDriverInitilization {
	HomePageObject homePageObject;

	@BeforeMethod
	public void SetUp() {
		homePageObject = new HomePageObject();
	}
	// @Test(invocationCount = 10)

	@Test
	public void loadUrl() {
		Reporter.log("started Load Url Test Case");
		loadBaseUrl();

	}

	@Test
	public void loadUrlAnother() {
		Reporter.log("started Load Url Test Case");
		loadBaseUrl();
		FramePageObject framePageObject = homePageObject.clickFrameAccountPage();
		framePageObject.verifyPageLoaded();
		NestedFramePageObject nestedFramePageObject = framePageObject.clickNestedFrame();
		nestedFramePageObject.verifyPageLoaded();
	}

}
