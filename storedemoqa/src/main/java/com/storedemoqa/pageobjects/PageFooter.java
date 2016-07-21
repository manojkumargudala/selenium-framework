package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class PageFooter {
	WebDriver driver;
	Wait<WebDriver> wait;
	@FindBy(xpath = ".//a[text()='SP Home']")
	WebElement sPHome;
	@FindBy(xpath = ".//a[text()='Sample Page']")
	WebElement samplePage;
	@FindBy(xpath = ".//a[text()='Your Account']")
	WebElement yourAccount;

	public PageFooter(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
}