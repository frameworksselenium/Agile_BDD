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
import java.net.URL;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BrowserStackMobileDriverFactory {

    private static BrowserStackMobileDriverFactory instance = new BrowserStackMobileDriverFactory();

    private BrowserStackMobileDriverFactory() {
    }

    public static BrowserStackMobileDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL, String testName, String buildId) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        String Mobile_PlatformName = Config.properties.getProperty("Mobile_PlatformName");
        String Mobile_BrowserName = Config.properties.getProperty("Mobile_BrowserName");
        String Mobile_Appium_deviceName = Config.properties.getProperty("Mobile_Appium_deviceName");
        String Mobile_Appium_PlatformVersion = Config.properties.getProperty("Mobile_Appium_PlatformVersion");
        String Mobile_Appium_AutomationName = Config.properties.getProperty("Mobile_Appium_AutomationName");
        String Mobile_Appium_Version = Config.properties.getProperty("Mobile_Appium_Version");
        String Mobile_Appium_App = Config.properties.getProperty("Mobile_Appium_App");

        String endPoint = Config.properties.getProperty("FileUpload_url");
        String userName = Config.properties.getProperty("UserName");
        String key = Config.properties.getProperty("Key");
        AppiumDriver driver = null;
        MutableCapabilities caps = null;
        URL url = null;
        try {
            switch (Mobile_Application_Type) {

                case "Android_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("appium:browserName", "chrome");
                    caps.setCapability("appium:deviceName", "Samsung Galaxy S23 Ultra");
                    caps.setCapability("appium:platformVersion", "13.0");
                    caps.setCapability("platformName", "android");

                    url = new URL(RemoteURL);
                    driver = new AndroidDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "ISO_Web":
                    caps = new MutableCapabilities();
                    caps.setCapability("appium:browserName", "safari");
                    caps.setCapability("appium:deviceName", "iPhone 15 Pro Max");
                    caps.setCapability("appium:platformVersion", "17");
                    caps.setCapability("platformName", "android");

                    url = new URL(RemoteURL);
                    driver = new IOSDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "Android_Native":
                    String filePath1 = System.getProperty("user.dir") + "/src/test/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
                    String custID1 = new File(filePath1).getName().replaceAll(".app| .ipa| .apk", "");

                    Response response1 = given().auth().preemptive().basic(userName, key)
                            .multiPart("file", new File(filePath1), "multipart/form-data")
                            .multiPart("custom_id", custID1)
                            .when().post(endPoint).then().extract().response();
                    String app_url1 = response1.jsonPath().get("app_url");
                    System.out.println("Initializing capabilities");
                    DesiredCapabilities capabilities1 = new DesiredCapabilities();
                    capabilities1.setCapability("appium:deviceName","Samsung Galaxy S22 Ultra");
                    capabilities1.setCapability("platformName", "android");
                    capabilities1.setCapability("appium:platformVersion","12.0");
                    capabilities1.setCapability("appium:app",app_url1);
                    url = new URL(RemoteURL);
                    driver = new AndroidDriver(url, capabilities1);
                    System.out.println("Driver launched");

                    break;

                case "IOS_Native":
                    String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa";
                    String custID = new File(filePath).getName().replaceAll(".app| .ipa| .apk", "");

                    Response response = given().auth().preemptive().basic(userName, key)
                            .multiPart("file", new File(filePath), "multipart/form-data")
                            .multiPart("custom_id", custID)
                            .when().post(endPoint).then().extract().response();
                    String app_url = response.jsonPath().get("app_url");
                    System.out.println("Initializing capabilities");
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("appium:deviceName","iPhone 14 Pro Max");
                    capabilities.setCapability("platformName", "iOS");
                    capabilities.setCapability("appium:platformVersion","16");
                    capabilities.setCapability("appium:app",app_url);
                    driver = new IOSDriver(new URL(RemoteURL), capabilities);
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