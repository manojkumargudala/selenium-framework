package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ImacsCaterogyPageObject {
	WebDriver driver;
	Wait<WebDriver> wait;
	PageFooter pageFooter;

	public ImacsCaterogyPageObject(WebDriver driver, Wait<WebDriver> wait, PageFooter pageFooter) {
		this.driver = driver;
		this.wait = wait;
		this.pageFooter = pageFooter;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[text()='Magic Mouse']")
	WebElement magicMouse;
	@FindBy(xpath = ".//input[@value='Add To Cart'][@type='submit']")
	WebElement addToCard;
	@FindBy(xpath = ".//a[text()='Go to Checkout']")
	WebElement goToCheckoutCart;

	@FindBy(xpath = ".//h1[text()='iMacs']")
	WebElement iMacs;

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(iMacs));
		wait.until(ExpectedConditions.visibilityOf(addToCard));

	}

	public CheckOutPageObject goToCheckOutPage() {
		CheckOutPageObject checkoutPageObject = new CheckOutPageObject(driver, wait);
		addToCard.click();
		wait.until(ExpectedConditions.visibilityOf(goToCheckoutCart));
		goToCheckoutCart.click();
		return checkoutPageObject;
	}

	public void clickSpHomeLink() {
		pageFooter.sPHome.click();
	}

	public SamplePageObject clickSamplePageLink() {
		SamplePageObject samplePageOject = new SamplePageObject(driver, wait, pageFooter);
		pageFooter.samplePage.click();
		return samplePageOject;
	}

	public void clickYourAccountLink() {
		pageFooter.yourAccount.click();
	}
}
