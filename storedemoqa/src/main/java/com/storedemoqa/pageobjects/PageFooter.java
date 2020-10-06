package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageFooter extends PageHeader {
	@FindBy(xpath = ".//a[text()='Elemental Selenium']")
	WebElement elementSelenium;

	public void clickSpHomeLink() {
		wait.until(ExpectedConditions.visibilityOf(elementSelenium));
		elementSelenium.click();
	}
}
