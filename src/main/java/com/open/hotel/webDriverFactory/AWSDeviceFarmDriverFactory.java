package com.open.hotel.webDriverFactory;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    public WebDriver createNewDriver(String browser, String PlatformName, String myProjectARN, String testName,
                                     String buildId) throws MalformedURLException {

        RemoteWebDriver driver = null;
        if (browser.toUpperCase().contains("CH")) {
            //String myProjectARN = "arn:aws:devicefarm:us-west-2:788877024129:testgrid-project:6f526502-978f-4f73-94ec-997106844b4f";
            DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
            CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
                    .expiresInSeconds(300)
                    .projectArn(myProjectARN)
                    .build();
            CreateTestGridUrlResponse response = client.createTestGridUrl(request);
            URL testGridUrl = new URL(response.url());

            //DesiredCapabilities desired_capabilities = new DesiredCapabilities();
           // desired_capabilities.setCapability("browserName","chrome");
           // desired_capabilities.setCapability("browserVersion", "latest");
           /// desired_capabilities.setCapability("platform", "windows");

            //DesiredCapabilities desired_capabilities = DesiredCapabilities.chrome();
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            // You can now pass this URL into RemoteWebDriver.
            driver = new RemoteWebDriver(testGridUrl, browserOptions);
        }
        return driver;

    }

}