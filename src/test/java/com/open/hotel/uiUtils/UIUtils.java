package com.open.hotel.uiUtils;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.config.Config;
import com.open.hotel.threadVariables.VariableManager;
//import com.sun.imageio.plugins.common.ImageUtil;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIUtils {
    WebDriver driver = null;
    Scenario scenario = null;
    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());;

    public UIUtils(){
        this.scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
    }

    public void type(WebElement element, String value, String elementName, String page){

        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            element.sendKeys(value);
            scenario.write("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '" + elementName + "' text box");
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '" + elementName + "' text box");
        }catch(Exception e){
            //scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");

            scenario.write("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            //scrollToElement(element);
            element.click();
            scenario.write("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button");
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button");
        }catch(Exception e){
            //scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
            scenario.write("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean WaitUntilClickable(WebElement element, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(driver, iWaitTime);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;
    }

    public void WaitUntilElementInvisible(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.invisibilityOf(element));
     }

    public void MouseMoveToElement(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(500);
        action.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();)", element);
    }

    public void highlightElement(WebElement element) throws Exception {
            String attributevalue = "border:10px solid green;";
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            String getattrib = element.getAttribute("style");
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
            Thread.sleep(100);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
    }

}
