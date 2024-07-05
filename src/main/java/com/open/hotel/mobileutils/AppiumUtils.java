package com.open.hotel.mobileutils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppiumUtils {

    public AppiumDriverLocalService service;

    WebDriver driver = null;
    Scenario scenario = null;

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    ;

    public AppiumUtils() {
        this.scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
    }
    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress(ipAddress).usingPort(port).withArgument(() -> "--allow-insecure", "chromedriver_autodownload").build();
        //AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort().withArgument(() -> "--allow-insecure","chromedriver_autodownload"));
        service.start();
        return service;
    }

    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public static Map<String, List<Map<String, String>>> readDataFromJson() {
        Map<String, List<Map<String, String>>> map = null;
        File jsonFile = new File("src/test/resources/config/MobileCommerce.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(jsonFile, new TypeReference<Map<String, List<Map<String, String>>>>() {
            });
        } catch (IOException e) {
            new RuntimeException(e.getMessage());
        }
        return map;
    }

    public static List<Map<String, String>> readElementFromMap(Map<String, List<Map<String, String>>> map, String os, String type, String appname) {

        List<Map<String, String>> filteredList = map.get(os).stream()
                .filter(m -> m.get("type").equals(type)).filter(m -> m.get("appname").equals(appname))
                .collect(Collectors.toList());
        return filteredList;
    }

    private void logAndAttach(String status, String message, Exception e) {
        String threadId = "Thread ID:'" + Thread.currentThread().getId() + "' ";
        String formattedMessage = threadId + "'" + status + "' " + message;
        scenario.attach(formattedMessage, "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
        log.info(formattedMessage);
        if (e != null) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            throw new RuntimeException(e);
        }
    }

    public void type(WebElement element, String value, String elementName, String page) {
        try {
            element.sendKeys(value);
            if (elementName.equals("Password")) {
                value = "";
            }
            logAndAttach("PASS", "Entered value '" + value + "' in '" + elementName + "' text box in " + page, null);
        } catch (Exception e) {
            logAndAttach("FAIL", e.getMessage(), e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page) {
        try {
            element.click();
            logAndAttach("PASS", "Clicked on '" + elementName + "' button in " + page, null);
        } catch (Exception e) {
            logAndAttach("FAIL", e.getMessage(), e);
        }
    }

}
