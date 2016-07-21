package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class SamplePageObject {
	@FindBy(xpath = ".//h1[text()='Sample Page']")
	WebElement samplePageHeader;
	WebDriver driver;
	Wait<WebDriver> wait;
	PageFooter pageFooter;

	public SamplePageObject(WebDriver driver, Wait<WebDriver> wait, PageFooter pageFooter) {
		this.driver = driver;
		this.wait = wait;
		this.pageFooter = pageFooter;
		PageFactory.initElements(driver, this);

	}

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(samplePageHeader));

	}

}
