package com.demoqa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
  public static String getRandomNumber() {
    SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSS");
    return ft.format(new Date());
  }

  public static String getRandomCaptureFileName(final String testName) {
    String fileName = getRandomNumber();
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append("ScreenShots/");
    stringTotalName.append(testName);
    stringTotalName.append("-");
    stringTotalName.append(fileName);
    return stringTotalName.toString();

  }

  public static String getRandomCaptureFullFileName(final String testName) {
    StringBuffer stringTotalName = new StringBuffer();
    // stringTotalName.append("./");
    stringTotalName.append(getRandomCaptureFileName(testName));
    stringTotalName.append(".png");
    return stringTotalName.toString();

  }

  public static void main(final String[] args) {
    System.out.println(getRandomCaptureFileName("a"));
    System.out.println(getRandomCaptureFullFileName("as"));

  }
}
