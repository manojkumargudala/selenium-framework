package com.jarvis.testcases;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoqa.selenium.BaseDriverInitilization;
import com.jarvis.pageobjects.SignUpPageObject;

public class SignUpTestCase2 extends BaseDriverInitilization {
	SignUpPageObject signUpPageObject;

	@BeforeMethod
	public void SetUp() {
		signUpPageObject = new SignUpPageObject();
	}

	@Test
	public void signUpForJarvis() {
		Reporter.log("started Load Url Test Case");
		loadBaseUrl();
		Reporter.log("Filling  the SignupForm");
		signUpPageObject.fillSignUpForm();
	}

	@Test
	public void signUpForJarvisParellel() {
		Reporter.log("started Load Url Test Case");
		loadBaseUrl();
		Reporter.log("Filling  the SignupForm");
		signUpPageObject.fillSignUpForm();
	}
}
