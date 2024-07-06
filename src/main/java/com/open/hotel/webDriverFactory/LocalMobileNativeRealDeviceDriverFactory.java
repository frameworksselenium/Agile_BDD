package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class LocalMobileNativeRealDeviceDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileNativeRealDeviceDriverFactory instance = new LocalMobileNativeRealDeviceDriverFactory();

    private LocalMobileNativeRealDeviceDriverFactory() {
    }

    public static LocalMobileNativeRealDeviceDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL)  {

        AppiumDriver driver = null;

        AppiumUtils appiumUtils = new AppiumUtils();
        if(!appiumUtils.checkIfAppiumServerIsRunnning(4723)) {
            AppiumDriverLocalService service = appiumUtils.startAppiumServer(Config.properties.getProperty("AppiumServerIP"), Integer.parseInt(Config.properties.getProperty("AppiumServerPort")));
            VariableManager.getInstance().getVariables().setVar("service", service);
        }
        String mobileApplicationType = Config.properties.getProperty("Mobile_Application_Type");
        String mobileExecution = Config.properties.getProperty("MobileExecution");
        String applicationName = Config.properties.getProperty("ApplicationName");

        Map<String, List<Map<String, String>>> data = AppiumUtils.readDataFromJson();
        Map<String, String>  configData = AppiumUtils.readElementFromMap(data, mobileApplicationType, mobileExecution, applicationName).get(0);
        String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/" + configData.get("appfilename");

        try {
            switch (mobileApplicationType) {
                case "Android":
                    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
                    uiAutomator2Options.setDeviceName(configData.get("devicename"))
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
                    MutableCapabilities capabilities = new MutableCapabilities();
                    capabilities.setCapability("deviceName", configData.get("devicename"));
                    capabilities.setCapability("platformName", "iOS");
                    capabilities.setCapability("platformVersion", "17.0");
                    capabilities.setCapability("automationName", "XCUITest");
                    //not able to install app using app capability so install app manually in real device and  use bundleId
                    //capabilities.setCapability("app", filePath);
                    capabilities.setCapability("appium:bundleId", configData.get("bundleId"));
                    //directly can not set below capabilities using xcuitest option so need to use MutableCapabilities
                    capabilities.setCapability("udid", configData.get("udid"));
                    capabilities.setCapability("xcodeOrgId", configData.get("xcodeOrgId"));
                    capabilities.setCapability("xcodeSigningId", configData.get("xcodeSigningId"));

                    driver = new IOSDriver(new URL(RemoteURL), capabilities);
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}
