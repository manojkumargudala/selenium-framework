package com.demoqa.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.demoqa.utils.DataUtils;
import com.demoqa.utils.ReadPropertyData;
import com.demoqa.utils.ReadPropertyDataImpl;
import com.demoqa.utils.SeleniumActions;
import com.demoqa.webdriver.SeleniumDriverObj;
import com.demoqa.webdriver.SeleniumDriverObjImpl;

@Listeners(value = { com.demoqa.listeners.MyTestngListener.class, com.demoqa.listeners.ExtentReporterNG.class })
public class BaseDriverInitilization {
	protected WebDriver driver;
	protected ReadPropertyData readProp;
	protected Wait<WebDriver> wait;

	@BeforeMethod
	public void myBaseDriverInitilization() {
		readProp = new ReadPropertyDataImpl("demoqaprops.properties");
		// readProp = new ReadPropertyDataImpl("demoqaprops.properties");
		SeleniumDriverObj selObj = new SeleniumDriverObjImpl();
		System.out.println("browser name " + readProp.readProperty("browser"));
		driver = selObj.getDriver(readProp.readProperty("browser"));
		wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
	}

	@AfterMethod
	public void takeScreenShot(final ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			String screenshotName = DataUtils.getRandomCaptureFullFileName(result.getName());
			result.setAttribute("screenShotFileName", screenshotName);
			screenshotName = "./" + screenshotName;
			SeleniumActions.captureScreenshot(driver, screenshotName);
		}
		driver.close();
		driver.quit();
	}

	// @AfterMethod
	// public void closeDriver() {
	// driver.close();
	// driver.quit();
	// }
}
