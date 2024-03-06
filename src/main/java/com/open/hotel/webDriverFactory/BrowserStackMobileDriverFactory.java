package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import java.time.Duration;

import io.restassured.response.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
                    caps.setCapability("browserstack:options", sauceOptions);

                    url = new URL(RemoteURL);
                    driver = new AndroidDriver(url, caps);
                    driver.manage().window().maximize();
                    break;

                case "IOS_Native":
                    /*caps = new MutableCapabilities();
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
                        throw new RuntimeException(e);
                    }*/


                    String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/" + Mobile_Appium_App;
                    String custID = new File(filePath).getName().replaceAll(".app| .ipa| .apk", "");

                    Response response = given().auth().preemptive().basic(userName, key)
                            .multiPart("file", new File(filePath), "multipart/form-data")
                            .multiPart("custom_id", custID)
                            .when().post(endPoint).then().extract().response();

                    String app_url = response.jsonPath().get("app_url");
                    System.out.println("Initializing capabilities");
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("appium:deviceName","iPhone 13");
                    capabilities.setCapability("platformName", "iOS");
                    capabilities.setCapability("appium:platformVersion","15");
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