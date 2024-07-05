package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class LocalMobileBrowserSimulatorDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileBrowserSimulatorDriverFactory instance = new LocalMobileBrowserSimulatorDriverFactory();

    private LocalMobileBrowserSimulatorDriverFactory() {
    }

    public static LocalMobileBrowserSimulatorDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String RemoteURL) {
        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        WebDriver driver = null;
        URL url = null;
        MutableCapabilities caps = null;
        try {
            switch (Mobile_Application_Type) {
                case "Android":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("appium:deviceName", "Samsung Galaxy S9");
                    caps.setCapability("appium:automationName", "UiAutomator2");
                    caps.setCapability("build", "1");
                    caps.setCapability("name", "My Test");
                    switch (browser) {
                        case "CH":
                            caps.setCapability("browserName", "Chrome");
                            break;
                        case "FF":
                            caps.setCapability("browserName", "Firefox");
                            break;
                        case "ED":
                            caps.setCapability("browserName", "Edge");
                            break;
                        case "SF":
                            caps.setCapability("browserName", "Safari");
                            break;
                    }
                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "ISO":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "iOS");
                    caps.setCapability("appium:deviceName", "iPhone 12");
                    caps.setCapability("appium:platformVersion", "16");
                    caps.setCapability("appium:automationName", "XCUITest");
                    caps.setCapability("build", "1");
                    caps.setCapability("name", "MyTest");
                    switch (browser) {
                        case "CH":
                            caps.setCapability("browserName", "Chrome");
                            break;
                        case "FF":
                            caps.setCapability("browserName", "Firefox");
                            break;
                        case "ED":
                            caps.setCapability("browserName", "Edge");
                            break;
                        case "SF":
                            caps.setCapability("browserName", "Safari");
                            break;
                    }
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
