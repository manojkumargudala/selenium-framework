package com.demoqa.selenium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.DataUtils;
import com.demoqa.utils.ReadPropertyData;
import com.demoqa.utils.ReadPropertyDataImpl;
import com.demoqa.utils.ScreenCasting;
import com.demoqa.webdriver.SeleniumDriverObj;
import com.demoqa.webdriver.SeleniumDriverObjImpl;

@Listeners(value = {com.demoqa.listeners.MyTestngListener.class,
    com.demoqa.listeners.ExtentReporterNG.class})
public class BaseDriverInitilization {
  private WebDriver driver;
  private ReadPropertyData readProp;
  private Wait<WebDriver> wait;
  private ScreenCasting scrnCst;

  @BeforeMethod
  public void myBaseDriverInitilization() {
    readProp = new ReadPropertyDataImpl("demoqaprops.properties");
    // readProp = new ReadPropertyDataImpl("demoqaprops.properties");
    SeleniumDriverObj selObj = new SeleniumDriverObjImpl();
    driver = selObj.getDriver(readProp.readProperty("browser"));
    wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
        .ignoring(StaleElementReferenceException.class);
    scrnCst = new ScreenCasting();
    BaseFrameWorkInitializer.getInstance().setDriver(driver);
    BaseFrameWorkInitializer.getInstance().setDriverWait(wait);
    BaseFrameWorkInitializer.getInstance().setReadProp(readProp);
    BaseFrameWorkInitializer.getInstance().setScreenCasting(scrnCst);
    BaseFrameWorkInitializer.getInstance().setRunInDebugMode(readProp.isRunningDebug());

  }

  @AfterMethod
  public void takeScreenShot(final ITestResult result) throws IOException {
    if (BaseFrameWorkInitializer.getInstance().isRunInDebugMode()) {
      scrnCst.stopRecording();
    }
    WebDriver driver = BaseFrameWorkInitializer.getInstance().getDriver();
    if (ITestResult.FAILURE == result.getStatus()) {
      String screenshotName = DataUtils.getRandomCaptureFileName(result.getName());
      result.setAttribute("screenShotFileName", screenshotName);
      screenshotName = "./" + screenshotName;
      SeleniumActions.captureScreenshot(driver, screenshotName);
    }
    driver.close();
    driver.quit();
  }

  protected void loadBaseUrl() {
    Reporter.log("Loaded the URL is " + readProp.readProperty("baseurl"));
    driver.get(readProp.readProperty("baseurl"));
  }
}
