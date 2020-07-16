package com.open.qa.web.uiUtils;

import com.open.qa.web.webDriverFactory.LocalDriverFactory;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import com.open.qa.web.webDriverFactory.RemoteDriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class UiUtils {

    public WebDriver driver;
    public WebDriverWait wait = null;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports report;
    public static ExtentTest test;
    public static Properties ps = null;

    public static String getProperty(String propertyName){
        return System.getProperty(propertyName);
    }

    public UiUtils(){
        System.out.println("***************Coreutil Constructor******************");
    }

    public void initializeWebDriver() {
        try {
            htmlReporter = new ExtentHtmlReporter("./target/report.html");
            report = new ExtentReports();
            report.attachReporter(htmlReporter);
            test = report.createTest("BDD Project");
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+  "\\test\\resources\\Config\\Sys.properties");
            ps = new Properties();
            ps.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String browser = ps.getProperty("Browser");
        String url = ps.getProperty(ps.getProperty("Region"));
        switch(browser.toLowerCase()){
            case "ch":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+  "\\test\\resources\\driver\\chromedriver.exe");
                ChromeOptions co = new ChromeOptions();
                co.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                co.addArguments("disable-extensions");
                co.setExperimentalOption("useAutomationExtension", false);
                co.addArguments("--disable-infobars");
                co.addArguments("--start-maximized");
                if (ps.getProperty("ExecutionMode").contains("Remote")) {
                    driver = RemoteDriverFactory.getInstance().createNewDriver();
                }
                if (ps.getProperty("ExecutionMode").contains("Local")) {
                    driver = LocalDriverFactory.getInstance().createNewDriver();
                }
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+ "\\IEDriverServer.exe");
                driver = new  InternetExplorerDriver();
                break;
        }
        ManagerDriver.getInstance().setWebDriver(driver);
    }

    public static Boolean SafeAction(By element, String value, String ColumnName) throws Exception {
        WebElement ele =null;
        Boolean returnValue = true;
        Actions objActions = null;
        objActions = new Actions(ManagerDriver.getInstance().getWebDriver());
        JavascriptExecutor js = (JavascriptExecutor) ManagerDriver.getInstance().getWebDriver();
        String elementType = ColumnName.substring(0, 3);
        String objectName = ColumnName.substring(3);
        boolean elementClickable = WaitUntilClickable(element, Integer.valueOf(10000));
        if (elementClickable == true) {
            Boolean f = ManagerDriver.getInstance().getWebDriver().findElements(element).size() != 0;
            if (!f) {
                returnValue = false;
            } else {
                highlightElement(element);
                try {
                    ele = ManagerDriver.getInstance().getWebDriver().findElement(element);
                    returnValue = true;
                } catch (Exception e) {
                    returnValue = false;
                }
            }
        } else {
            returnValue = false;
        }
        if (returnValue) {
            switch (elementType.toUpperCase()) {
                case "EDT":
                    ele.clear();
                    ele.sendKeys(value);
                    ele.sendKeys(Keys.TAB);
                    test.pass("details");
                    returnValue = true;
                    break;
                case "BTN":
                    ele.click();
                    returnValue = true;
                    break;
                case "ELE":
                    Action objMouseClick1 = objActions.click(ele).build();
                    objMouseClick1.perform();
                    returnValue = true;
                    break;
                case "DBL":
                    objActions.click(ele);
                    Action objMousedblClick = objActions.doubleClick(ele).build();
                    objMousedblClick.perform();
                    returnValue = true;
                    break;
                case "LST":
                    Select listbox = new Select(ele);
                    listbox.selectByValue(value);
                    returnValue = true;
                    break;
                case "SCL":
                    ((JavascriptExecutor) ManagerDriver.getInstance().getWebDriver()).executeScript("arguments[0].scrollIntoView();", ManagerDriver.getInstance().getWebDriver().findElement(element));
                    returnValue = true;
                    returnValue = JavaScript(js);
                    break;
            }
        } else {
        }
        return returnValue;
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

    public static boolean WaitUntilClickable(By bylocator, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(ManagerDriver.getInstance().getWebDriver(), iWaitTime);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(bylocator));
            if (ManagerDriver.getInstance().getWebDriver().findElement((bylocator)).isDisplayed()) {
                bFlag = true;
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();

            bFlag = false;
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;
    }

}