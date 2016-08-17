package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class LoggedOutPageObject extends PageFooter {

  @FindBy(xpath = ".//p[contains(text(),'You are now logged out')]")
  WebElement loggedOutText;

  public LoggedOutPageObject(final WebDriver driver, final Wait<WebDriver> wait) {
    super(driver, wait);
    PageFactory.initElements(driver, this);
  }

  public void verifyPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(loggedOutText));

  }
}
