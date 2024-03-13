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

public class SauceLabsRemoteDriverFactory {
    private static SauceLabsRemoteDriverFactory instance = new SauceLabsRemoteDriverFactory();

    private SauceLabsRemoteDriverFactory() {
    }

    public static SauceLabsRemoteDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String RemoteURL) {

        WebDriver driver = null;
        if (browser.toUpperCase().contains("CH")) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "1");
            sauceOptions.put("name", "My Test");
            browserOptions.setCapability("sauce:options", sauceOptions);
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
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "1");
            sauceOptions.put("name", "My Test");
            browserOptions.setCapability("sauce:options", sauceOptions);
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
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "1");
            sauceOptions.put("name", "My Test");
            browserOptions.setCapability("sauce:options", sauceOptions);
            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, browserOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("SF")) {
            SafariOptions browserOptions = new SafariOptions();
            browserOptions.setPlatformName("macOS 13");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "1");
            sauceOptions.put("name", "My Test");
            browserOptions.setCapability("sauce:options", sauceOptions);
            try {
                URL url = new URL(RemoteURL);
                driver = new RemoteWebDriver(url, browserOptions);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

}