package com.open.hotel.webDriverFactory;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

import java.net.MalformedURLException;
import java.net.URL;

public class AWSDeviceFarmDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static AWSDeviceFarmDriverFactory instance = new AWSDeviceFarmDriverFactory();

    private AWSDeviceFarmDriverFactory() {
    }

    public static AWSDeviceFarmDriverFactory getInstance() {
        return instance;
    }
    public WebDriver createNewDriver(String browser, String myProjectARN) throws MalformedURLException {
        //currently device form support only windows
        RemoteWebDriver driver = null;
        DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
        CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
                .expiresInSeconds(300)
                .projectArn(myProjectARN)
                .build();
        CreateTestGridUrlResponse response = client.createTestGridUrl(request);
        String RemoteURL = response.url();
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
                browserOptions.setCapability("ms:edgeChromium", true);
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