package com.open.hotel.webDriverFactory;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class ManagerDriver {

    private static ManagerDriver instance = new ManagerDriver();

    private ManagerDriver() {

    }

    public static ManagerDriver getInstance() {
        return instance;
    }

    public WebDriver getDriver(String ExecutionMode, String browser, String PlatformName, String RemoteURL,
							   String testName, String buildId) throws MalformedURLException {
        WebDriver driver = null;
        switch (ExecutionMode) {
            case "Local":
                driver = LocalDriverFactory.getInstance().createNewDriver(browser, PlatformName);
                break;
            case "Remote":
                driver = RemoteDriverFactory.getInstance().createNewDriver(browser, PlatformName, RemoteURL, testName, buildId);
                break;
            case "SauceLabsRemote":
                driver = SauceLabsRemoteDriverFactory.getInstance().createNewDriver(browser, PlatformName, RemoteURL,testName, buildId);
                break;
            case "SauceLabsMobile":
                driver = SauceLabsMobileRemoteDriverFactory.getInstance().createNewDriver(RemoteURL, testName, buildId);
                break;
            case "AWSDeviceFarm":
                driver = AWSDeviceFarmDriverFactory.getInstance().createNewDriver(browser, PlatformName, RemoteURL, testName, buildId);
                break;
            default:
                throw new RuntimeException(String.format("Provide Correct Execution Mode"));
        }
        return driver;
    }

}
