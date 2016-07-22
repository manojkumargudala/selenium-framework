package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class IPadsCaterogyPageObject extends PageFooter {
  WebDriver driver;
  Wait<WebDriver> wait;

  public IPadsCaterogyPageObject(final WebDriver driver, final Wait<WebDriver> wait) {
    super(driver, wait);
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = ".//a[text()='Magic Mouse']")
  WebElement magicMouse;

  @FindBy(xpath = ".//input[@value='Add To Cart'][@type='submit']")
  WebElement addToCard;

  @FindBy(xpath = ".//h1[text()='iPads']")
  WebElement iPads;

  public void verifyPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(iPads));
    wait.until(ExpectedConditions.visibilityOf(addToCard));

  }

}
