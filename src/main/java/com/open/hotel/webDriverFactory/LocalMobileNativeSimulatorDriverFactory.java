package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class LocalMobileNativeSimulatorDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileNativeSimulatorDriverFactory instance = new LocalMobileNativeSimulatorDriverFactory();

    private LocalMobileNativeSimulatorDriverFactory() {
    }

    public static LocalMobileNativeSimulatorDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {
        String mobileApplicationType = Config.properties.getProperty("Mobile_Application_Type");
        String mobileExecution = Config.properties.getProperty("MobileExecution");
        String applicationName = Config.properties.getProperty("ApplicationName");

        Map<String, List<Map<String, String>>> data = AppiumUtils.readDataFromJson();
        Map<String, String>  configData = AppiumUtils.readElementFromMap(data, mobileApplicationType, mobileExecution, applicationName).get(0);
        String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/" + configData.get("appfilename");
        AppiumDriver driver = null;
        try {
            switch (mobileApplicationType) {
                case "Android":
                    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
                    uiAutomator2Options.setDeviceName("emulator-5554")
                            .setPlatformName("android")
                            .setApp(filePath)
                            .setAutomationName("UiAutomator2")
                            .setNoReset(true)
                            .setAppPackage(configData.get("packagename")) //if app already installed we no need to give below capabilities
                            .setAppActivity(configData.get("activityname"));
                    driver = new AndroidDriver(new URL(RemoteURL), uiAutomator2Options);
                    System.out.println("AndroidDriver is set");
                    break;
                case "IOS":
                    XCUITestOptions xcuiTestOptions = new XCUITestOptions();
                    xcuiTestOptions.setDeviceName("iPhone 15 Pro Max")
                            .setPlatformName("iOS")
                            .setPlatformVersion("17.0")
                            .setAutomationName("XCUITest")
                            //.setNoReset(true)
                            .setApp(filePath);
                            //.setBundleId(configData.get("bundleId")); //if u do not want ot install every time we can provide bundleId if already install app
                    driver = new IOSDriver(new URL(RemoteURL), xcuiTestOptions);
                    break;
                default:
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}
