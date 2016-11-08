package com.storedemoqa.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import ru.yandex.qatools.allure.annotations.Step;

public class YourAccountPageObject extends PageFooter {
  @FindBy(xpath = ".//*[@id='meta']/ul/li[1]/a")
  public WebElement registerLink;
  @FindBy(name = "")
  WebElement registerNameLink;
  @FindBy(linkText = "Register")
  WebElement linkTest;
  @FindBy(partialLinkText = "")
  WebElement partialLinkTextRegister;
  @FindBy(xpath = ".//input[@id='log']")
  WebElement loginputText;
  @FindBy(xpath = ".//input[@id='pwd']")
  WebElement pwdinputText;
  @FindBy(xpath = ".//input[@id='login'][@type='submit']")
  WebElement loginButton;
  @FindBy(xpath = ".//p[contains(text(),'Invalid login credentials')]")
  WebElement invalidCredentials;

  public YourAccountPageObject(final WebDriver driver, final Wait<WebDriver> wait) {
    PageFactory.initElements(driver, this);
  }

  @Step("Verify My account page is loaded")
  public void verifyPageLoaded() {
    wait.until(ExpectedConditions.visibilityOf(registerLink));
    wait.until(ExpectedConditions.visibilityOf(loginputText));
    wait.until(ExpectedConditions.visibilityOf(pwdinputText));
    wait.until(ExpectedConditions.visibilityOf(loginButton));
  }

  public RegisterationPageObject clickRegisterLink() {
    RegisterationPageObject registerPageObject = new RegisterationPageObject(driver, wait);
    registerLink.click();
    return registerPageObject;
  }

  public UserLoginHomePage login(final String userName, final String password,
      final boolean isValidUser) {
    System.out.println("user login ");
    UserLoginHomePage userLoginHomePage = new UserLoginHomePage(driver, wait);
    loginputText.sendKeys(userName);
    pwdinputText.sendKeys(password);
    pwdinputText.sendKeys(Keys.TAB);
    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    loginButton.click();
    System.out.println("user login after click ");

    if (!(isValidUser)) {
      wait.until(ExpectedConditions.visibilityOf(invalidCredentials));
    }
    return userLoginHomePage;
  }
}
