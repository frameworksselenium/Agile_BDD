package com.open.qa.web.webDriverFactory;

import org.openqa.selenium.WebDriver;

public class ManagerDriver {
	private static ManagerDriver instance = new ManagerDriver();
	ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static ManagerDriver getInstance() {
		return instance;
	}
	public WebDriver getWebDriver() {
		return driver.get();
	}
	public void setWebDriver(WebDriver webdriver) {
		driver.set(webdriver);
	}
}