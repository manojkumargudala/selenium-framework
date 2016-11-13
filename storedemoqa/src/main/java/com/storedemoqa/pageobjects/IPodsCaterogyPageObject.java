package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IPodsCaterogyPageObject extends PageFooter {

	public IPodsCaterogyPageObject() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[text()='Magic Mouse']")
	WebElement magicMouse;

	@FindBy(xpath = ".//input[@value='Add To Cart'][@type='submit']")
	WebElement addToCard;

	@FindBy(xpath = ".//h1[text()='iPods']")
	WebElement iPods;

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(iPods));
		wait.until(ExpectedConditions.visibilityOf(addToCard));
	}

}
