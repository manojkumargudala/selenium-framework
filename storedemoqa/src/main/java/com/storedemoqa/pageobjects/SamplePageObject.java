package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class SamplePageObject extends PageFooter {
  @FindBy(xpath = ".//h1[text()='Sample Page']")
  WebElement samplePageHeader;

  public SamplePageObject(final WebDriver driver, final Wait<WebDriver> wait) {
    PageFactory.initElements(driver, this);
  }

  public void verifyPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(samplePageHeader));

  }

}
