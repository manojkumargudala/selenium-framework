package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class PageFooter extends PageHeader {
  WebDriver driver;
  Wait<WebDriver> wait;
  @FindBy(xpath = ".//a[text()='SP Home']")
  WebElement spHome;
  @FindBy(xpath = ".//a[text()='Sample Page']")
  WebElement samplePage;
  @FindBy(xpath = ".//a[text()='Your Account']")
  WebElement yourAccount;

  public PageFooter(final WebDriver driver, final Wait<WebDriver> wait) {
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  public void clickSpHomeLink() {
    wait.until(ExpectedConditions.visibilityOf(spHome));
    spHome.click();
  }

  public SamplePageObject clickSamplePageLink() {
    SamplePageObject samplePageOject = new SamplePageObject(driver, wait);
    wait.until(ExpectedConditions.visibilityOf(samplePage));
    samplePage.click();
    return samplePageOject;
  }

  public YourAccountPageObject clickYourAccountLink() {
    YourAccountPageObject yourAccountPageObject = new YourAccountPageObject(driver, wait);
    wait.until(ExpectedConditions.elementToBeClickable(yourAccount));
    yourAccount.click();
    return yourAccountPageObject;
  }
}
