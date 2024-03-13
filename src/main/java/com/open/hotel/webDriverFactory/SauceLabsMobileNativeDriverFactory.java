package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import java.net.URL;

public class SauceLabsMobileNativeDriverFactory {

    private static SauceLabsMobileNativeDriverFactory instance = new SauceLabsMobileNativeDriverFactory();

    private SauceLabsMobileNativeDriverFactory() {
    }

    public static SauceLabsMobileNativeDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");

        AppiumDriver driver = null;
        MutableCapabilities caps = null;
        MutableCapabilities sauceOptions = null;
        URL url = null;
        try {
            switch (Mobile_Application_Type) {
                case "Mobile_Android_Native":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("appium:app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");  // The filename of the mobile app
                    caps.setCapability("appium:deviceName", "Samsung Galaxy S9");
                    caps.setCapability("appium:platformVersion", "10");
                    caps.setCapability("appium:automationName", "UiAutomator2");
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("build", "<your build id>");
                    sauceOptions.setCapability("name", "<your test name>");
                    caps.setCapability("sauce:options", sauceOptions);
                    driver = new AndroidDriver(new URL(RemoteURL), caps);
                    break;
                case "Mobile_IOS_Native":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "iOS");
                    caps.setCapability("appium:app", "storage:filename=iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");  // The filename of the mobile app
                    caps.setCapability("appium:deviceName", "iPhone.*");
                    caps.setCapability("appium:platformVersion", "16");
                    caps.setCapability("appium:automationName", "XCUITest");
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("build", "1");
                    sauceOptions.setCapability("name", "My Test");
                    caps.setCapability("sauce:options", sauceOptions);
                    driver = new IOSDriver(new URL(RemoteURL), caps);
                    System.out.println("Driver launched");
                    break;
                default:
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}