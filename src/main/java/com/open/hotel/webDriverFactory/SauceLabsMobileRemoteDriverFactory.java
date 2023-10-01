package com.open.hotel.webDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SauceLabsMobileRemoteDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static SauceLabsMobileRemoteDriverFactory instance = new SauceLabsMobileRemoteDriverFactory();

    private SauceLabsMobileRemoteDriverFactory() {
    }

    public static SauceLabsMobileRemoteDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String platformName, String RemoteURL, String testName,
                                     String buildId) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        String Mobile_PlatformName = Config.properties.getProperty("Mobile_PlatformName");
        String Mobile_BrowserName = Config.properties.getProperty("Mobile_BrowserName");
        String Mobile_Appium_deviceName = Config.properties.getProperty("Mobile_Appium_deviceName");
        String Mobile_Appium_PlatformVersion = Config.properties.getProperty("Mobile_Appium_PlatformVersion");
        String Mobile_Appium_AutomationName = Config.properties.getProperty("Mobile_Appium_AutomationName");
        String Mobile_Appium_Version = Config.properties.getProperty("Mobile_Appium_Version");
        String Mobile_Appium_App = Config.properties.getProperty("Mobile_Appium_App");

        AppiumDriver driver = null;
        MutableCapabilities caps = null;
        MutableCapabilities sauceOptions = null;
        URL url = null;
        try {
            switch (Mobile_Application_Type) {

                case "Android_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", Mobile_PlatformName);
                    caps.setCapability("browserName", Mobile_BrowserName);
                    caps.setCapability("appium:deviceName", Mobile_Appium_deviceName);
                    caps.setCapability("appium:platformVersion", Mobile_Appium_PlatformVersion);
                    caps.setCapability("appium:automationName", Mobile_Appium_AutomationName);
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("appiumVersion", Mobile_Appium_Version);
                    sauceOptions.setCapability("build", buildId);
                    sauceOptions.setCapability("name", testName);
                    caps.setCapability("sauce:options", sauceOptions);

                    url = new URL(RemoteURL);
                    driver = new AndroidDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "ISO_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", Mobile_PlatformName);
                    caps.setCapability("browserName", Mobile_BrowserName);
                    caps.setCapability("appium:deviceName", Mobile_Appium_deviceName);
                    caps.setCapability("appium:platformVersion", Mobile_Appium_PlatformVersion);
                    caps.setCapability("appium:automationName", Mobile_Appium_AutomationName);
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("appiumVersion", Mobile_Appium_Version);
                    sauceOptions.setCapability("build", buildId);
                    sauceOptions.setCapability("name", testName);
                    caps.setCapability("sauce:options", sauceOptions);

                    url = new URL(RemoteURL);
                    driver = new IOSDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "Android_Native":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", Mobile_PlatformName);
                    caps.setCapability("appium:app", "storage:" + Mobile_Appium_App); // The filename of the mobile app
                    caps.setCapability("appium:deviceName", Mobile_Appium_deviceName);
                    caps.setCapability("appium:platformVersion", Mobile_Appium_PlatformVersion);
                    caps.setCapability("appium:automationName", Mobile_Appium_AutomationName);
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("appiumVersion", Mobile_Appium_Version);
                    sauceOptions.setCapability("build", buildId);
                    sauceOptions.setCapability("name", testName);
                    caps.setCapability("sauce:options", sauceOptions);

                    url = new URL(RemoteURL);
                    driver = new AndroidDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "IOS_Native":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", Mobile_PlatformName);
                    caps.setCapability("appium:app", "storage:" + Mobile_Appium_App); // The filename of the mobile app
                    caps.setCapability("appium:deviceName", Mobile_Appium_deviceName);
                    caps.setCapability("appium:platformVersion", Mobile_Appium_PlatformVersion);
                    caps.setCapability("appium:automationName", Mobile_Appium_AutomationName);
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("appiumVersion", Mobile_Appium_Version);
                    sauceOptions.setCapability("build", buildId);
                    sauceOptions.setCapability("name", testName);
                    caps.setCapability("sauce:options", sauceOptions);

                    try {
                        url = new URL(RemoteURL);
                        driver = new IOSDriver(url, caps);
                    } catch (MalformedURLException e) {
                        log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;
                default:

            }
        } catch (Exception ex) {
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + ex.getMessage());
            throw new RuntimeException(ex);
        }
        return driver;

    }

}