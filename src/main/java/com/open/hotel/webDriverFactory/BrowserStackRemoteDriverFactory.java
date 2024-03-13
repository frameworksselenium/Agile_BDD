package com.open.hotel.webDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserStackRemoteDriverFactory {
    private static BrowserStackRemoteDriverFactory instance = new BrowserStackRemoteDriverFactory();

    private BrowserStackRemoteDriverFactory() {
    }

    public static BrowserStackRemoteDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String RemoteURL) {

        RemoteWebDriver driver = null;
        if (browser.toUpperCase().contains("CH")) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, browserOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("FF")) {
            FirefoxOptions browserOptions = new FirefoxOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, browserOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("ED")) {
            EdgeOptions browserOptions = new EdgeOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            browserOptions.setCapability("ms:edgeChromium", true);

            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, browserOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("SF")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setPlatformName("mac");
            safariOptions.setBrowserVersion("latest");
            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, safariOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

}