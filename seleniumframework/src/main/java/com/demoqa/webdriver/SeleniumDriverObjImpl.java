package com.demoqa.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.demoqa.listeners.MyWebDriverListerner;
import com.demoqa.utils.Browser;

public class SeleniumDriverObjImpl implements SeleniumDriverObj {
  WebDriver driver;
  EventFiringWebDriver eventFiringWebDriver;

  @Override
  public WebDriver getDriver(final String browserName) {
    if (!(browserName.equalsIgnoreCase("Random"))) {
      getBrowerSpecificDriver(browserName);
    } else {
      getBrowerSpecificDriver(Browser.getRandomBrowser().name());
    }
    driver.manage().window().maximize();
    this.eventFiringWebDriver = new EventFiringWebDriver(driver);
    MyWebDriverListerner handler = new MyWebDriverListerner();
    eventFiringWebDriver.register(handler);
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    return driver;
  }

  private void getBrowerSpecificDriver(final String browserName) {
    if (browserName.equalsIgnoreCase("FireFox")) {
      DesiredCapabilities cap = DesiredCapabilities.firefox();
      System.setProperty("webdriver.gecko.driver", getPath("geckodriver.exe"));
      driver = new FirefoxDriver(cap);
    }
    if (browserName.equalsIgnoreCase("IE")) {
      DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
      capabilities.setCapability(
          InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
      System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
      driver = new InternetExplorerDriver(capabilities);
    }
    if (browserName.equalsIgnoreCase("Chrome")) {
      System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("test-type");
      capabilities.setCapability("chrome.binary", "chromedriver.exe");
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      driver = new ChromeDriver(capabilities);
    }

  }

  private String getPath(final String browserImplExeFileName) {
    System.out.println("browser file name " + browserImplExeFileName);
    File file = new File(getClass().getClassLoader().getResource(browserImplExeFileName).getFile());
    String fileAbsPath = file.getAbsolutePath();
    System.out.println("absPath" + fileAbsPath);
    String fileCancialPath = file.getPath();
    System.out.println("fullPath" + fileCancialPath);
    try {
      String fileAbsPath1 = file.getCanonicalPath();
      System.out.println("fullCanc" + fileAbsPath1);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return fileCancialPath;
  }
}
