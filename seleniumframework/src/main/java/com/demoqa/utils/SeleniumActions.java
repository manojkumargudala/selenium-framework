package com.demoqa.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Attachment;

public class SeleniumActions {
  @Attachment(value = "this is attachmate with source name \"{1}\"", type = "image/png")
  public static byte[] captureScreenshot(final WebDriver driver, final String screenshotName) {
    try {
      TakesScreenshot ts = (TakesScreenshot) driver;
      File source = ts.getScreenshotAs(OutputType.FILE);
      byte[] filesrc = ts.getScreenshotAs(OutputType.BYTES);
      FileUtils.copyFile(source, new File(screenshotName));
      System.out.println("Screenshot taken");
      return filesrc;
    } catch (Exception e) {
      System.out.println("Exception while taking screenshot " + e.getMessage());
    }
    return null;
  }
}
