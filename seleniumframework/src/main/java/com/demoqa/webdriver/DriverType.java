package com.demoqa.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.demoqa.utils.FilePathUtils;

public enum DriverType implements DriverSetup {
	FIREFOX {

		@Override
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", FilePathUtils.getDriverFullPath("geckodriver.exe"));
			return capabilities;
		}

		@Override
		public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
			FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
			return new FirefoxDriver(firefoxOptions);
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
			System.setProperty("webdriver.chrome.driver", FilePathUtils.getDriverFullPath("chromedriver"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--no-sandbox");
			capabilities.setCapability("chrome.binary", FilePathUtils.getDriverFullPath("chromedriver"));
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			return capabilities;
		}

		@Override
		public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.merge(capabilities);
			return new ChromeDriver(chromeOptions);
		}

	};

	public static DriverType getRandomBrowser() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
