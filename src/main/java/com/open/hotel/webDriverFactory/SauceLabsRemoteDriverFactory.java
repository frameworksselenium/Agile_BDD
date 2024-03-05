package com.open.hotel.webDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    public WebDriver createNewDriver(String browser, String platformName, String RemoteURL, String testName, String buildId) {

        RemoteWebDriver driver = null;
        ChromeOptions browserOptions = null;
        if (browser.toUpperCase().contains("CH")) {
            browserOptions = new ChromeOptions();
            browserOptions.setPlatformName(platformName);
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", buildId);
            sauceOptions.put("name", testName);
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

        }
        if (browser.toUpperCase().contains("ED")) {

        }
        if (browser.toUpperCase().contains("SF")) {

        }
        return driver;
    }

}