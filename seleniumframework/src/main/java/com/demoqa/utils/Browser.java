package com.demoqa.utils;

import java.util.Random;

/**
 * BaseColor enum.
 */
public enum Browser {
  Chrome, IE, Firefox;

  /**
   * Pick a random value of the BaseColor enum.
   * 
   * @return a random BaseColor.
   */
  public static Browser getRandomBrowser() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
