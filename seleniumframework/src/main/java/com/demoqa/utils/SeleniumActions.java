package com.demoqa.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumActions {
  public static void captureScreenshot(final WebDriver driver, final String screenshotName) {
    try {
      TakesScreenshot ts = (TakesScreenshot) driver;
      File source = ts.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(source, new File(screenshotName));
      System.out.println("Screenshot taken");
    } catch (Exception e) {
      System.out.println("Exception while taking screenshot " + e.getMessage());
    }
  }
}
