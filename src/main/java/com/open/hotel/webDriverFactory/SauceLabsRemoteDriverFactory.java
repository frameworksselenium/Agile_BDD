package com.open.hotel.webDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;

import io.cucumber.java.Scenario;

public class SauceLabsRemoteDriverFactory {

    Scenario scenario = null;
    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static SauceLabsRemoteDriverFactory instance = new SauceLabsRemoteDriverFactory();

    private SauceLabsRemoteDriverFactory() {
        this.scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
    }

    public static SauceLabsRemoteDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String platformName, String RemoteURL, String testName,
                                     String buildId) {

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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("FF")) {
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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("ED")) {
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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("SF")) {
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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

}