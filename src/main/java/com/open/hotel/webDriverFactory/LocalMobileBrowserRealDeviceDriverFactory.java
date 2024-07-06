package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalMobileBrowserRealDeviceDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileBrowserRealDeviceDriverFactory instance = new LocalMobileBrowserRealDeviceDriverFactory();

    private LocalMobileBrowserRealDeviceDriverFactory() {
    }

    public static LocalMobileBrowserRealDeviceDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String RemoteURL) {
        AppiumUtils appiumUtils = new AppiumUtils();
        if(!appiumUtils.checkIfAppiumServerIsRunnning(4723)) {
            AppiumDriverLocalService service = appiumUtils.startAppiumServer(Config.properties.getProperty("AppiumServerIP"), Integer.parseInt(Config.properties.getProperty("AppiumServerPort")));
            VariableManager.getInstance().getVariables().setVar("service", service);
        }

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
