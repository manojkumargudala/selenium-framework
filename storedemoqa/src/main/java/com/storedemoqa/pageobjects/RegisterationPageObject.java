package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import ru.yandex.qatools.allure.annotations.Step;

public class RegisterationPageObject extends PageFooter {

  @FindBy(xpath = ".//*[@id='user_login']")
  public WebElement userLogin;
  @FindBy(xpath = ".//*[@id='user_email']")
  WebElement userEmail;
  @FindBy(linkText = "Register")
  WebElement linkTest;
  @FindBy(partialLinkText = "")
  WebElement partialLinkTextRegister;

  public RegisterationPageObject(final WebDriver driver, final Wait<WebDriver> wait) {
    PageFactory.initElements(driver, this);
  }

  @Step("Verify My account page is loaded")
  public void verifyPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(userLogin));
    wait.until(ExpectedConditions.visibilityOf(userEmail));
    wait.until(ExpectedConditions.visibilityOf(linkTest));
    wait.until(ExpectedConditions.visibilityOf(partialLinkTextRegister));
  }
}
