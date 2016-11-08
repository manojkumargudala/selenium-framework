package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ImacsCaterogyPageObject extends PageFooter {

  public ImacsCaterogyPageObject(final WebDriver driver, final Wait<WebDriver> wait) {
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

}
