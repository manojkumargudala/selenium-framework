package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NestedFramePageObject extends PageFooter {
	@FindBy(xpath = "//frameset[@name='frameset-middle']")
	public WebElement nestedFrames;
	@FindBy(xpath = "//frame[@name='frame-left']")
	public WebElement leftFrame;
	@FindBy(xpath = "//frame[@name='frame-middle']")
	public WebElement middleFrame;
	@FindBy(xpath = "//frame[@name='frame-right']")
	public WebElement rightFrame;
	@FindBy(xpath = "//frame[@name='frame-bottom']")
	public WebElement bottonFrame;

	public void verifyPageLoaded() {
		// wait.until(ExpectedConditions.visibilityOf(productCatergoy));
		wait.until(ExpectedConditions.visibilityOf(nestedFrames));
		// wait.until(ExpectedConditions.visibilityOf(leftFrame));

	}
}
