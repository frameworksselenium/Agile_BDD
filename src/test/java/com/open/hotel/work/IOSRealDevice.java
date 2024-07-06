package com.open.hotel.work;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSRealDevice {

    //write a programme to print the numbers from 1 to 100
    public static void main(String[] args) throws MalformedURLException {

        try {
            MutableCapabilities caps = new MutableCapabilities();
            caps.setCapability("appium:platformName", "iOS");
            caps.setCapability("appium:deviceName", "iPhone 15 Pro");
            caps.setCapability("appium:automationName", "XCUITest");
            caps.setCapability("appium:udid", "00008130-000E05AE11E8001C");
            caps.setCapability("appium:xcodeOrgId", "396BX93R4W");
            caps.setCapability("appium:xcodesigninId", "iPhone Developer");
            caps.setCapability("appium:updateWDABundleId", "com.facebook02.WebDriverAgentLib");
            caps.setCapability("appium:bundleId", "com.dollartree.consumer");
            //caps.setCapability("appium:app", "/Users/krishnareddymanubolu/Documents/WorkSpace/Agile_BDD/src/test/resources/apps/iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");
            WebDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), caps);

            WebElement search = driver.findElement(AppiumBy.accessibilityId("Search Products"));
            search.click();
            WebElement enterSearch = driver.findElement(AppiumBy.accessibilityId("FDPlpSearchHeader:searchInput"));
            enterSearch.sendKeys("Jar");
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
}
}
