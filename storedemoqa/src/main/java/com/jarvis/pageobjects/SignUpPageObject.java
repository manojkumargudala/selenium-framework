package com.jarvis.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.demoqa.utils.DataUtils;

public class SignUpPageObject extends PageHeader {
	@FindBy(xpath = ".//input[@id='addressInput']")
	WebElement addressInput;
	@FindBy(xpath = ".//input[@value='NEXT']")
	WebElement nextButton;

	@FindBy(xpath = "//*[contains(text(),'Testa Street')]")
	WebElement addressLink;
	@FindBy(xpath = "//span[contains(text(),'Fortnightly')]")
	WebElement fortnightly;
	@FindBy(xpath = "//div[@class='timer-box']//div[@class='owl-next']")
	WebElement owlNext;
	@FindBy(xpath = "//div[@class='owl-item active']//span[text()='3:30']")
	WebElement threeAndHalfHours;
	@FindBy(xpath = "//div[@class='owl-item active']//span[text()='8:00']")
	WebElement eightOClock;
	@FindBy(xpath = "//div[@aria-label='day-23']")
	WebElement day23;
	@FindBy(xpath = "//textarea[@name='comment']")
	WebElement textarea;
	@FindBy(xpath = "//ul[@class='option-prefer-pets']/li")
	WebElement femaleGender;

	@FindBy(xpath = "// ul[@class='option-prefer-pets']//span[text()='Dog']")
	WebElement petsDog;
	@FindBy(xpath = ".//input[@name='coupon_code']")
	WebElement couponCode;

	public SignUpPageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(addressInput));
		wait.until(ExpectedConditions.visibilityOf(nextButton));

	}

	public CreateAccountPage fillSignUpForm() {
		wait.until(ExpectedConditions.visibilityOf(addressInput));
		addressInput.sendKeys("test");
		DataUtils.pause(3);
		addressInput.sendKeys(Keys.ARROW_DOWN);
		addressInput.sendKeys(Keys.ARROW_DOWN);
		addressInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(addressLink));
		// addressLink.click();
		fortnightly.click();
		owlNext.click();
		threeAndHalfHours.click();
		day23.click();
		eightOClock.click();
		textarea.sendKeys("This is the comment box");
		femaleGender.click();
		petsDog.click();
		couponCode.sendKeys("Test12345");
		nextButton.click();
		return new CreateAccountPage();

	}
}
