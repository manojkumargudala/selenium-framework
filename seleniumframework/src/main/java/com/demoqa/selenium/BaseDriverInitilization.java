package com.demoqa.selenium;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.DataUtils;
import com.demoqa.utils.GenericConstants;
import com.demoqa.utils.ReadPropertyData;
import com.demoqa.utils.ReadPropertyDataImpl;
import com.demoqa.webdriver.SeleniumDriverObj;
import com.demoqa.webdriver.SeleniumDriverObjImpl;

//@Listeners(value = { com.demoqa.listeners.MyTestngListener.class, com.demoqa.listeners.ExtentReporterNG.class })
public class BaseDriverInitilization {

	@BeforeMethod
	public void myBaseDriverInitilization() {
		ReadPropertyData readProp = new ReadPropertyDataImpl(GenericConstants.STORE_DEMO_PROP_FILE_NAME);
		SeleniumDriverObj selObj = new SeleniumDriverObjImpl();
		WebDriver driver = selObj.getDriver(readProp.readProperty("browser"));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		BaseFrameWorkInitializer.getInstance().setDriver(driver);
		BaseFrameWorkInitializer.getInstance().setDriverWait(wait);
		BaseFrameWorkInitializer.getInstance().setReadProp(readProp);
		BaseFrameWorkInitializer.getInstance().setRunInDebugMode(readProp.isRunningDebug());
	}

	@AfterMethod
	public void takeScreenShot(final ITestResult result) {
		WebDriver driver = BaseFrameWorkInitializer.getInstance().getDriver();
		takeScreenShotOnFailure(result, driver);
		driver.close();
		driver.quit();
		BaseFrameWorkInitializer.getInstance().removeThreadVairable();
	}

	private void takeScreenShotOnFailure(final ITestResult result, final WebDriver driver) {
		if (ITestResult.FAILURE == result.getStatus()) {

			String screenshotName = DataUtils.getRandomCaptureFileName(result.getName());
			result.setAttribute(GenericConstants.SCREEN_SHOT_REPORTER_ATTRIBUTE, screenshotName);
			screenshotName = "./" + screenshotName;
			SeleniumActions.captureScreenshot(driver, screenshotName);
		}
	}

	protected void loadBaseUrl() {
		ReadPropertyData readProp = BaseFrameWorkInitializer.getInstance().getReadProp();
		Reporter.log("Loaded the URL is " + readProp.readProperty("baseurl"));
		BaseFrameWorkInitializer.getInstance().getDriver().get(readProp.readProperty("baseurl"));
	}
}
