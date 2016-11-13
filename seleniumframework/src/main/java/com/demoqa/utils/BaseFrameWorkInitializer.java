package com.demoqa.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class BaseFrameWorkInitializer {
	static Logger logger = Logger.getLogger(BaseFrameWorkInitializer.class);
	public static BaseFrameWorkInitializer baseFrameWorkInitializer;
	private WebDriver driver;
	private Wait<WebDriver> webdriverWait;
	private ReadPropertyData readProp;
	private ScreenCasting screenCasting;
	private boolean runInDebugMode;

	private BaseFrameWorkInitializer() {

	}

	public static BaseFrameWorkInitializer getInstance() {
		if (baseFrameWorkInitializer == null) {
			baseFrameWorkInitializer = new BaseFrameWorkInitializer();
		}
		return baseFrameWorkInitializer;
	}

	public void setDriverWait(final Wait<WebDriver> webdriverWait) {
		this.webdriverWait = webdriverWait;
	}

	public Wait<WebDriver> getWebDriverWait() {
		assertingNotNull(webdriverWait);
		return webdriverWait;
	}

	public void setDriver(final WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		assertingNotNull(driver);
		return driver;
	}

	private static void assertingNotNull(final Object object) {
		if (object == null) {
			logger.debug("the object being returned is null");
		}
	}

	public ReadPropertyData getReadProp() {
		assertingNotNull(readProp);
		return readProp;
	}

	public void setReadProp(final ReadPropertyData readProp) {
		this.readProp = readProp;
	}

	public ScreenCasting getScreenCasting() {
		assertingNotNull(screenCasting);
		return screenCasting;
	}

	public void setScreenCasting(final ScreenCasting screenCasting) {
		this.screenCasting = screenCasting;
	}

	public boolean isRunInDebugMode() {
		return runInDebugMode;
	}

	public void setRunInDebugMode(final boolean runInDebugMode) {
		this.runInDebugMode = runInDebugMode;
	}
}
