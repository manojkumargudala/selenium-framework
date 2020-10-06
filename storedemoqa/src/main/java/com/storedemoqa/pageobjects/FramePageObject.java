package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramePageObject extends PageFooter {
	@FindBy(xpath = ".//a[text()='Nested Frames']")
	public WebElement nestedFrames;

	@FindBy(xpath = ".//a[text()='iFrame']")
	public WebElement iFrame;

	public FramePageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		// wait.until(ExpectedConditions.visibilityOf(productCatergoy));
		wait.until(ExpectedConditions.visibilityOf(nestedFrames));
		wait.until(ExpectedConditions.visibilityOf(iFrame));

	}

	public NestedFramePageObject clickNestedFrame() {
		wait.until(ExpectedConditions.visibilityOf(nestedFrames));
		nestedFrames.click();
		return new NestedFramePageObject();
	}
}
