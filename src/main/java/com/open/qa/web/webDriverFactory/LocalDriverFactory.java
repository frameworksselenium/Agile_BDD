package com.open.qa.web.webDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalDriverFactory {

	private static LocalDriverFactory instance = new LocalDriverFactory();

	public static LocalDriverFactory getInstance() {
		return instance;
	}

	public WebDriver createNewDriver() {
		WebDriver driver = null;
		String browser = "CH";
			String driverPath = "C:\\Selenium\\Drivers";
			if (browser.toUpperCase().contains("CH")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("no-sandbox");
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
			}
			if (browser.toUpperCase().contains("FF")) {
				driver = new FirefoxDriver();
			}
			if (browser.toUpperCase().contains("IE")) {
				System.setProperty("webdriver.ie.driver", driverPath + "\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		return driver;
	}
}