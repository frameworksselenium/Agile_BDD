package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.response.Response;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static io.restassured.RestAssured.given;

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
                    caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
                    caps.setCapability("appium:platformVersion", "14.0");
                    caps.setCapability("appium:automationName", "UiAutomator2");
                    sauceOptions = new MutableCapabilities();
                    //sauceOptions.setCapability("appiumVersion", "2.0.0-flutter2");
                    //sauceOptions.setCapability("username", "oauth-kmanubolu-810c2");
                    //sauceOptions.setCapability("accessKey", "*****987e");
                    sauceOptions.setCapability("build", "1");
                    sauceOptions.setCapability("name", "My Test");
                    caps.setCapability("sauce:options", sauceOptions);

                    driver = new AndroidDriver(new URL(RemoteURL), caps);

                    break;

                case "Mobile_IOS_Native":
                    caps = new MutableCapabilities();
                    caps.setCapability("platformName", "iOS");
                    // first need to upload ips file and give that file name
                    caps.setCapability("appium:app", "storage:iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");  // The filename of the mobile app
                    caps.setCapability("appium:deviceName", "iPhone 12");
                    caps.setCapability("appium:platformVersion", "16");
                    caps.setCapability("appium:automationName", "XCUITest");
                    sauceOptions = new MutableCapabilities();
                    //sauceOptions.setCapability("username", "oauth-kmanubolu-810c2");
                    //sauceOptions.setCapability("accessKey", "*****987e");
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