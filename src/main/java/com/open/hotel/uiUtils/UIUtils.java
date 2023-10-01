package com.open.hotel.uiUtils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.hotel.config.Config;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.cucumber.java.Scenario;

public class UIUtils {
    WebDriver driver = null;
    Scenario scenario = null;

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    ;

    public UIUtils() {
        this.scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
    }

    public void type(WebElement element, String value, String elementName, String page) {

        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            element.clear();
            element.sendKeys(value);
            if (elementName.equals("Password")) {
                value = "";
            }
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box");
        } catch (Exception e) {
            if (elementName.equals("Password")) {
                value = "";
            }
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void typeJavascript(WebElement element, String value, String elementName, String page) {
        try {
            JavascriptExecutor ex = (JavascriptExecutor) driver;
            ex.executeScript("arguments[0].value='" + value + "';", element);
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box " + " on page " + page);

        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page) {
        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            // scrollToElement(element);
            element.click();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean WaitUntilClickable(WebElement element, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(iWaitTime));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            bFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;
    }

    public void elementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void MouseMoveToElement(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(500);
        action.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void highlightElement(WebElement element) throws Exception {

        String highlightElements = Config.properties.getProperty("HighlightElements");
        if (highlightElements.equalsIgnoreCase("true")) {
            String attributevalue = "border:10px solid green;";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String getattrib = element.getAttribute("style");
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
            Thread.sleep(100);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
        }
    }

    public void closeWindowByTitle(String title, String page) {
        try {
            Set<String> handle = driver.getWindowHandles();
            for (String mywindows : handle) {
                String myTitle = driver.switchTo().window(mywindows).getTitle();
                if (myTitle.equalsIgnoreCase(title)) {
                    driver.switchTo().window(mywindows);
                    driver.close();
                    break;
                }
            }

        } catch (NoSuchWindowException e) {
            log.info("Exception: " + e);
            Assert.fail("Window is not present in page");
        }
    }

    public void switchIntoWindow(String title) {

        String parent = driver.getWindowHandle();
        VariableManager.getInstance().getVariables().setVar("parent", parent);
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            String winTitle = driver.getTitle();
            if (winTitle.equals(title)) {
                break;
            }
        }

    }

    public void switchIntoWindow(String[] title) {

        String parent = driver.getWindowHandle();
        VariableManager.getInstance().getVariables().setVar("parent", parent);
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            driver.switchTo().window(window);
            String winTitle = driver.getTitle();
            if ((!winTitle.equals(title[0])) && (!winTitle.equals(title[1])) && (!winTitle.equals(title[2]))) {
                break;
            }
        }

    }

    public void elementToBeClickable(String xPath, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    public void switchToIFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void waitFor(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearTextBox(WebElement element, String value, String elementName, String page) {

        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            element.clear();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForStaleElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForWebElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
        //.ignoring(ElementClickInterceptedException.class);

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForStaleElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> {
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForWebElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                //.ignoring(NoSuchElementException.class);
                .ignoring(ElementClickInterceptedException.class);

        return wait.until(driver -> {
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public void generalClickElement(WebElement element, String elementName, String page) {
        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            element.click();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void waitUntilElementInvisible(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    }

    public void selectValue(WebElement element, String value, String elementName, String page) {
        try {
            //WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            // scrollToElement(element);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            ;
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Selected value '" + elementName + "' in dropdown",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Selected value '" + elementName
                    + "' in dropdown");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public WebElement createWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void javaScriptClickElement(WebElement element,String elementName, String page) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button" + "On Page " + page);
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForElement(String xPath) {
        WebElement element = waitForWebElement(By.xpath(xPath));
        element = waitForStaleElement(By.xpath(xPath));
        elementToBeClickable(element);
        return element;
    }

    public WebElement waitForElement(WebElement ele) {
        waitFor(2000);
        WebElement element = waitForWebElement(ele);
        element = waitForStaleElement(ele);
        elementToBeClickable(element);
        return element;
    }

    public WebElement expandRootElement (String xpath) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        WebElement element = (WebElement) js.executeScript("return " + xpath + "");
        return element;
    }

    public Map<String, String> getAllKeysAndValuesFromJson(String module, String jsonFileName) throws IOException, ParseException {

        String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\templates\\" + module + "\\" + jsonFileName + ".json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(jsonFilePath));
        JSONObject jsonObject = (JSONObject) obj;
        ObjectMapper mapper = new ObjectMapper();
        String json = jsonObject.toString();
        Map<String, String> map = mapper.readValue(json, Map.class);
        return map;
    }

}
