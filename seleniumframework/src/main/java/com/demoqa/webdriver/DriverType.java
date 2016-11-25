package com.demoqa.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.FilePathUtils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

public enum DriverType implements DriverSetup {
  FIREFOX {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
      DesiredCapabilities capabilities = DesiredCapabilities.firefox();
      System.setProperty("webdriver.gecko.driver",
          FilePathUtils.getDriverFullPath("geckodriver.exe"));
      return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
      return new FirefoxDriver(capabilities);
    }

    @Override
    public DesiredCapabilities addResponseCodeChecks(final DesiredCapabilities capabilities,
        final boolean isCheckRequestcode) {
      return DriverType.setBrowserProxy(isCheckRequestcode, capabilities);
    }

  },
  CHROME {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
      HashMap<String, String> chromePreferences = new HashMap<String, String>();
      chromePreferences.put("profile.password_manager_enabled", "false");
      capabilities.setCapability("chrome.prefs", chromePreferences);
      System.setProperty("webdriver.chrome.driver",
          FilePathUtils.getDriverFullPath("chromedriver.exe"));
      ChromeOptions options = new ChromeOptions();
      options.addArguments("test-type");
      capabilities.setCapability("chrome.binary",
          FilePathUtils.getDriverFullPath("chromedriver.exe"));
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
      return new ChromeDriver(capabilities);
    }

    @Override
    public DesiredCapabilities addResponseCodeChecks(final DesiredCapabilities capabilities,
        final boolean isCheckRequestcode) {
      return DriverType.setBrowserProxy(isCheckRequestcode, capabilities);

    }

  },
  IE {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
      DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
      capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
      capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
      capabilities.setCapability("requireWindowFocus", true);
      capabilities.setCapability(
          InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
      System.setProperty("webdriver.ie.driver",
          FilePathUtils.getDriverFullPath("IEDriverServer.exe"));
      return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
      return new InternetExplorerDriver(capabilities);
    }

    @Override
    public DesiredCapabilities addResponseCodeChecks(final DesiredCapabilities capabilities,
        final boolean isCheckRequestcode) {
      return DriverType.setBrowserProxy(isCheckRequestcode, capabilities);

    }

  },
  SAFARI {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
      DesiredCapabilities capabilities = DesiredCapabilities.safari();
      capabilities.setCapability("safari.cleanSession", true);
      return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
      return new SafariDriver(capabilities);
    }

    @Override
    public DesiredCapabilities addResponseCodeChecks(final DesiredCapabilities capabilities,
        final boolean isCheckRequestcode) {
      return DriverType.setBrowserProxy(isCheckRequestcode, capabilities);
    }

  },
  OPERA {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
      DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
      return capabilities;
    }

    @Override
    public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
      return new OperaDriver(capabilities);
    }

    @Override
    public DesiredCapabilities addResponseCodeChecks(final DesiredCapabilities capabilities,
        final boolean isCheckRequestcode) {
      return DriverType.setBrowserProxy(isCheckRequestcode, capabilities);
    }

  };

  private static DesiredCapabilities setBrowserProxy(final Boolean checkCodes,
      final DesiredCapabilities capabilities) {
    if (checkCodes) {
      Logger logger = Logger.getLogger(DriverSetup.class);
      logger.info("setting the proxy");
      BrowserMobProxy proxy = BaseFrameWorkInitializer.getInstance().getBrowserMobProxy();
      capabilities.setCapability(CapabilityType.PROXY, ClientUtil.createSeleniumProxy(proxy));
    }
    return capabilities;
  }

  public static DriverType getRandomBrowser() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
