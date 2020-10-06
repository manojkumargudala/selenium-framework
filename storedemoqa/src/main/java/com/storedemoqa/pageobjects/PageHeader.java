package com.storedemoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

import com.demoqa.utils.BaseFrameWorkInitializer;

public class PageHeader {
	WebDriver driver;
	Wait<WebDriver> wait;

	public PageHeader() {
		this.driver = BaseFrameWorkInitializer.getInstance().getDriver();
		this.wait = BaseFrameWorkInitializer.getInstance().getWebDriverWait();
		Reporter.log("The page " + this.getClass() + " is getting loaded");
	}
}
