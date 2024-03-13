package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SauceLabsMobileBrowserDriverFactory {

    private static SauceLabsMobileBrowserDriverFactory instance = new SauceLabsMobileBrowserDriverFactory();

    private SauceLabsMobileBrowserDriverFactory() {
    }

    public static SauceLabsMobileBrowserDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        WebDriver driver = null;
        URL url = null;
        MutableCapabilities caps = null;
        MutableCapabilities sauceOptions = null;
        try {
            switch (Mobile_Application_Type) {

                case "Mobile_Android_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("browserName", "Chrome");
                    caps.setCapability("appium:deviceName", "Samsung Galaxy S9");
                    caps.setCapability("appium:automationName", "UiAutomator2");
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("build", "1");
                    sauceOptions.setCapability("name", "My Test");
                    caps.setCapability("sauce:options", sauceOptions);
                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "Mobile_ISO_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "iOS");
                    caps.setCapability("browserName", "Safari");
                    caps.setCapability("appium:deviceName", "iPhone 12");
                    caps.setCapability("appium:platformVersion", "16");
                    caps.setCapability("appium:automationName", "XCUITest");
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("build", "1");
                    sauceOptions.setCapability("name", "MyTest");
                    caps.setCapability("sauce:options", sauceOptions);
                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}