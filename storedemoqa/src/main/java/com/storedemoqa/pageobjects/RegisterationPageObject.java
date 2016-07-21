package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterationPageObject {

	@FindBy(xpath = ".//*[@id='user_login']")
	public WebElement userLogin;
	@FindBy(xpath = ".//*[@id='user_email']")
	WebElement userEmail;
	@FindBy(linkText = "Register")
	WebElement linkTest;
	@FindBy(partialLinkText = "")
	WebElement partialLinkTextRegister;

	WebDriver driver;

	public RegisterationPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
