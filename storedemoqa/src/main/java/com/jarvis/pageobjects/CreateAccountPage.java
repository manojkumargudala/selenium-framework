package com.jarvis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateAccountPage extends PageHeader {
	@FindBy(xpath = ".//label[text()='Create your account']")
	WebElement createAccountLabel;

	public CreateAccountPage() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(createAccountLabel));

	}

	public void fillSignUpForm() {
		wait.until(ExpectedConditions.visibilityOf(createAccountLabel));

	}
}
