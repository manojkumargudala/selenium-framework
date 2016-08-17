package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class PageHeader {
  WebDriver driver;
  Wait<WebDriver> wait;

  public PageHeader(final WebDriver driver, final Wait<WebDriver> wait) {
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }
}
