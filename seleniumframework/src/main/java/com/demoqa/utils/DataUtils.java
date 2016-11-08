package com.demoqa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
  public static String getRandomNumber() {
    SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSS");
    return ft.format(new Date());
  }

  private static String getRandomCaptureFileNamePath(final String testName) {
    String fileName = getRandomNumber();
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append("ScreenShots/");
    stringTotalName.append(testName);
    stringTotalName.append("-");
    stringTotalName.append(fileName);
    return stringTotalName.toString();

  }

  public static String getRandomCaptureFileName(final String testName) {
    StringBuffer stringTotalName = new StringBuffer();
    // stringTotalName.append("./");
    stringTotalName.append(getRandomCaptureFileNamePath(testName));
    stringTotalName.append(".png");
    return stringTotalName.toString();

  }

  public static String getScreenShotFullPath(final String fileName) {
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append(System.getProperty("user.dir"));
    // stringTotalName.append("/ScreenShots/");
    stringTotalName.append("/" + fileName);
    return stringTotalName.toString();

  }

  public static String getScreenCastFolderPath(final String folderName) {
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append(System.getProperty("user.dir"));
    stringTotalName.append("\\ScreenCast\\");
    stringTotalName.append("\\" + folderName);
    return stringTotalName.toString();

  }

  public static void main(final String[] args) {
    System.out.println(getRandomCaptureFileName("a"));
    System.out.println(getRandomCaptureFileNamePath("as"));
    System.out.println(getScreenShotFullPath(getRandomCaptureFileName("a")));
  }
}
