package com.open.hotel.webDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;

public class RemoteDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static RemoteDriverFactory instance = new RemoteDriverFactory();

    private RemoteDriverFactory() {
    }

    public static RemoteDriverFactory getInstance() {
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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
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
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("ED")) {
            EdgeOptions browserOptions = new EdgeOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
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
            SafariOptions browserOptions = new SafariOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
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