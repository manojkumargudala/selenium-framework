package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class RegisterationPageObject extends PageFooter {

	@FindBy(xpath = ".//*[@id='user_login']")
	public WebElement userLogin;
	@FindBy(xpath = ".//*[@id='user_email']")
	WebElement userEmail;
	@FindBy(xpath = ".//input[@id='wp-submit']")
	WebElement linkTest;
	@FindBy(partialLinkText = "Log")
	WebElement partialLinkTextRegister;

	public RegisterationPageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		Reporter.log("Navigated to registration page");
		wait.until(ExpectedConditions.visibilityOf(userLogin));
		wait.until(ExpectedConditions.visibilityOf(userEmail));
		wait.until(ExpectedConditions.visibilityOf(linkTest));
		wait.until(ExpectedConditions.visibilityOf(partialLinkTextRegister));
	}
}
