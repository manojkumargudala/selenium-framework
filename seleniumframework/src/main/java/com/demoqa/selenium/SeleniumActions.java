package com.demoqa.selenium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumActions {
  public static byte[] captureScreenshot(final WebDriver driver, final String screenshotName) {
    try {
      TakesScreenshot ts = (TakesScreenshot) driver;
      File source = ts.getScreenshotAs(OutputType.FILE);
      byte[] filesrc = ts.getScreenshotAs(OutputType.BYTES);
      FileUtils.copyFile(source, new File(screenshotName));
      return filesrc;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
