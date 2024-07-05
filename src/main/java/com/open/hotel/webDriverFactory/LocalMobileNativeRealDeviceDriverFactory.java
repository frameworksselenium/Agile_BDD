package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class LocalMobileNativeRealDeviceDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileNativeRealDeviceDriverFactory instance = new LocalMobileNativeRealDeviceDriverFactory();

    private LocalMobileNativeRealDeviceDriverFactory() {
    }

    public static LocalMobileNativeRealDeviceDriverFactory getInstance() {
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
                case "Android":
                    String filePath1 = System.getProperty("user.dir") + "/src/test/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";

                    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
                    uiAutomator2Options.setDeviceName("ZY22H2TXV5")
                            //.setPlatformVersion("13.0")
                            .setPlatformName("android")
                            //.setApp(filePath1)
                            .setAutomationName("UiAutomator2")
                            .setAppPackage("com.swaglabsmobileapp") //if app already installed we no need to give below capabilities
                            .setAppActivity("com.swaglabsmobileapp.MainActivity");
                    driver = new AndroidDriver(new URL(RemoteURL), uiAutomator2Options);
                    System.out.println("AndroidDriver is set");

                    break;
                case "IOS":
                    String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app";
                    caps = new MutableCapabilities();
                    caps.setCapability("appium:platformName", "iOS");
                    caps.setCapability("appium:deviceName", "iPhone 15 Pro");
                    caps.setCapability("appium:automationName", "XCUITest");
                    caps.setCapability("appium:udid", "");
                    caps.setCapability("appium:xcodeOrgId", "396BX93R4W");
                    caps.setCapability("appium:xcodesigninId", "iPhone Developer");
                    caps.setCapability("appium:updateWDABundleId", "com.facebook02.WebDriverAgentLib");
                    caps.setCapability("appium:bundleId", "com.dollartree.consumer");
                    driver = new IOSDriver(new URL(RemoteURL), caps);
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}
