package com.open.hotel.work;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class POM {

    public static void main(String[] args) throws Exception {

        //try {
            UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
            uiAutomator2Options.setDeviceName("emulator-5554")
                    .setPlatformName("android")
                    .setApp("/Users/krishnareddymanubolu/Documents/WorkSpace/Agile_BDD/src/test/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk")
                    .setAutomationName("UiAutomator2")
                    .setNoReset(true)
                    .setAppPackage("com.swaglabsmobileapp")
                    .setAppActivity("com.swaglabsmobileapp.MainActivity");
            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), uiAutomator2Options);

            POM1 mobilrLogin = new POM1(driver);
            mobilrLogin.login("standard_user", "secret_sauce");

       // }catch(Exception e){
      //      System.out.println("Error: "+e.getMessage());
       // }
}
}
