package com.open.hotel.work;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class SafariBrowserTest {

    public static void main(String[] args) {
        /*MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 15");*/
        String RemoteURL = "https://krishnareddy_b7Ijmo:XsxCeMyayJ78MyKXafSQ@hub-cloud.browserstack.com/wd/hub";

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "safari");
        caps.setCapability("deviceName", "iPhone 15");

        /*DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:deviceName","iPhone 14 Pro Max");
        caps.setCapability("appium:browserName","safari");*/
        try {
            URL url = new URL(RemoteURL);
            WebDriver driver = new RemoteWebDriver(url, caps);
            driver.get("https://www.google.com/");
            driver.quit();
        } catch (MalformedURLException e) {
            System.out.println("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

