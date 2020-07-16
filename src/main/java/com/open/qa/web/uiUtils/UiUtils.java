package com.open.qa.web.uiUtils;

import com.open.qa.web.webDriverFactory.ManagerDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UiUtils {

    public WebDriver driver;
    public WebDriverWait wait = null;

    public UiUtils(){

    }

    public void type(WebElement element, String value, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(10000));
            if (elementClickable == true) {

            }
            element.sendKeys(value);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public void clickElement(WebElement element, String elementName, String page){
        try{
            boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(10000));
            element.click();
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public static void highlightElement(By locator) throws Exception {
        WebElement element = ManagerDriver.getInstance().getWebDriver().findElement(locator);
        String attributevalue = "border:10px solid green;";
        JavascriptExecutor executor = (JavascriptExecutor) ManagerDriver.getInstance().getWebDriver();
        String getattrib = element.getAttribute("style");
        executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
        Thread.sleep(100);
        executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
    }

    public static boolean JavaScript(JavascriptExecutor js) throws Exception {
        boolean status = false;
        for (int i = 1; i <= Integer.parseInt("10000"); i++) {
            Boolean isAjaxRunning = Boolean.valueOf(js.executeScript("return Ext.Ajax.isLoading();")
                    .toString());
            if (!isAjaxRunning.booleanValue()) {
                status = true;
                break;
            }
            Thread.sleep(1000);// wait for one secnod then check if ajax is// completed
        }
        return status;
    }

    public static boolean WaitUntilClickable(WebElement element, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(ManagerDriver.getInstance().getWebDriver(), iWaitTime);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;
    }

}