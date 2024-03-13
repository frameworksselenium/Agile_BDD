package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import io.restassured.response.Response;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.restassured.RestAssured.given;

public class BrowserStackMobileBrowserDriverFactory {

    private static BrowserStackMobileBrowserDriverFactory instance = new BrowserStackMobileBrowserDriverFactory();

    private BrowserStackMobileBrowserDriverFactory() {
    }

    public static BrowserStackMobileBrowserDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        WebDriver driver = null;
        URL url = null;
        MutableCapabilities caps = null;
        try {
            switch (Mobile_Application_Type) {

                case "Mobile_Android_Web":
                    caps = new MutableCapabilities();
                    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                    browserstackOptions.put("browserName", "chrome");
                    browserstackOptions.put("deviceName", "Samsung Galaxy S23 Ultra");
                    browserstackOptions.put("realMobile", "true");
                    browserstackOptions.put("osVersion", "13.0");
                    caps.setCapability("bstack:options", browserstackOptions);

                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                        driver.manage().window().maximize();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "Mobile_ISO_Web":
                    caps = new MutableCapabilities();
                    HashMap<String, Object> browserstackOptions1 = new HashMap<String, Object>();
                    browserstackOptions1.put("browserName", "safari");
                    browserstackOptions1.put("deviceName", "iPhone 15 Pro Max");
                    browserstackOptions1.put("realMobile", "true");
                    browserstackOptions1.put("osVersion", "17");
                    caps.setCapability("bstack:options", browserstackOptions1);
                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                        driver.manage().window().maximize();
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