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

    public WebDriver getDriver(String ExecutionMode, String browser, String RemoteURL,
							   String testName, String buildId) throws MalformedURLException {
        WebDriver driver = null;
        switch (ExecutionMode) {
            case "Local":
                driver = LocalDriverFactory.getInstance().createNewDriver(browser);
                break;
            case "LocalMobileBrowser":
                driver = LocalMobileBrowserDriverFactory.getInstance().createNewDriver(browser);
                break;
            case "LocalMobileNative":
                driver = LocalMobileNativeDriverFactory.getInstance().createNewDriver(RemoteURL);
                break;
            case "Remote":
                driver = RemoteDriverFactory.getInstance().createNewDriver(browser, RemoteURL);
                break;
            case "AWSDeviceFarm":
                driver = AWSDeviceFarmDriverFactory.getInstance().createNewDriver(browser, RemoteURL);
                break;
            case "BrowserStackMobileNative":
                driver = BrowserStackMobileNativeDriverFactory.getInstance().createNewDriver(RemoteURL);
                break;
            case "BrowserStackMobileBrowser":
                driver = BrowserStackMobileBrowserDriverFactory.getInstance().createNewDriver(RemoteURL);
                break;
            case "BrowserStackRemote":
                driver = BrowserStackRemoteDriverFactory.getInstance().createNewDriver(browser, RemoteURL);
                break;
            case "SauceLabsMobileNative":
                driver = SauceLabsMobileNativeDriverFactory.getInstance().createNewDriver(RemoteURL);
                break;
            case "SauceLabsMobileBrowser":
                driver = SauceLabsMobileBrowserDriverFactory.getInstance().createNewDriver(RemoteURL);
                break;
            case "SauceLabsRemote":
                driver = SauceLabsRemoteDriverFactory.getInstance().createNewDriver(browser, RemoteURL);
                break;
            default:
                throw new RuntimeException(String.format("Provide Correct Execution Mode"));
        }
        return driver;
    }

}
