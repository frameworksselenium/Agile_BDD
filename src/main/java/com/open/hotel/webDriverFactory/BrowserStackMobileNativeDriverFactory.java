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

public class BrowserStackMobileNativeDriverFactory {

    private static BrowserStackMobileNativeDriverFactory instance = new BrowserStackMobileNativeDriverFactory();

    private BrowserStackMobileNativeDriverFactory() {
    }

    public static BrowserStackMobileNativeDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {

        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");

        String endPoint = Config.properties.getProperty("FileUpload_url");
        String userName = Config.properties.getProperty("UserName");
        String key = Config.properties.getProperty("Key");
        AppiumDriver driver = null;
        MutableCapabilities caps = null;
        URL url = null;
        try {
            switch (Mobile_Application_Type) {
                case "Mobile_Android_Native":
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

                case "Mobile_IOS_Native":
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