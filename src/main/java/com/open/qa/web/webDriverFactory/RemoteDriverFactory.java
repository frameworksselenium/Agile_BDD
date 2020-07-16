package com.open.qa.web.webDriverFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class RemoteDriverFactory {

	private static RemoteDriverFactory instance = new RemoteDriverFactory();

	public static RemoteDriverFactory getInstance() {
		return instance;
	}

	public WebDriver createNewDriver() {
		RemoteWebDriver driver = null;
		String browser = "CH";
		DesiredCapabilities cap = null;
			if (browser.toUpperCase().contains("CH")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("no-sandbox");
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("useAutomationExtension", false);
				cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WINDOWS);
			}
			if (browser.toUpperCase().contains("FF")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.WINDOWS);
			}
			if (browser.toUpperCase().contains("IE")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WINDOWS);
			}
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		return driver; // can be replaced with other browser drivers
	}
}