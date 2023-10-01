package com.open.hotel.assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;

import io.cucumber.java.Scenario;

public class Assertions {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    Scenario scenario = null;
    WebDriver driver = null;

    public Assertions() {
        scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
    }

    public void assertValues(String nodeName, String expectedValue, String actualValValue) {
        if (expectedValue.equalsIgnoreCase(actualValValue)) {
            String passString = "Thread ID:'" + Thread.currentThread().getId() + "' PASS:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(passString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(passString);
        } else {
            String failString = "Thread ID:'" + Thread.currentThread().getId() + "' FAIL:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(failString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(failString);
        }
    }

    public void assert2ValuesFromUI(String fieldName, String expectedValue, String actualValValue) {

        String status = null;
        if (expectedValue.equalsIgnoreCase(actualValValue)) {
            status = "PASS";
        } else {
            status = "FAIL";
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
        }
        String failString = "Thread ID:'" + Thread.currentThread().getId() + "' - " + status + ":: Field Name : '"
                + fieldName + "' --- Expected Value : '" + expectedValue + "' - Actual Value : '" + actualValValue
                + "'";
        scenario.attach(failString, "text/plain",
                DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
        log.info(failString);
        Assert.assertEquals(expectedValue, actualValValue);
    }

    public void assert2Values(String expectedValue, String actualValValue) {

        String status = null;
        if (expectedValue.equalsIgnoreCase(actualValValue)) {
            status = "PASS";
        } else {
            status = "FAIL";
        }
        String statusString = "Thread ID:'" + Thread.currentThread().getId() + "' - " + status + "::' --- Expected Value : '" + expectedValue + "' - Actual Value : '" + actualValValue
                + "'";
        scenario.attach(statusString, "text/plain",
                DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
        log.info(statusString);
        Assert.assertEquals(expectedValue, actualValValue);
    }

    public void assertEquals(String expected, String Actual) {
        if (expected.trim().equalsIgnoreCase(Actual.trim())) {
        }
        log.info("Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public void assertEquals(boolean expected, boolean Actual) {
        if (expected == Actual) {
        }
        log.info("Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public void assertEquals(boolean expected, boolean Actual,String customMessage) {
        log.info(customMessage);
        Assert.assertEquals(expected, Actual);
    }

    public void assertEquals(int expected, int Actual) {
        if (expected == Actual) {
        }
        log.info("Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public synchronized void assertEquals1(String expected, String Actual) {
        String status = "Fail";
        if (expected.trim().equalsIgnoreCase(Actual.trim())) {
            status = "Pass";
        }
        log.info(status + " Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public synchronized void assertEquals1(boolean expected, boolean Actual) {
        String status = "Fail";
        if (expected == Actual) {
            status = "Pass";
        }
        log.info(status + " Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public synchronized void assertEquals1(int expected, int Actual) {
        String status = "Fail";
        if (expected == Actual) {
            status = "Pass";
        }
        log.info(status + " Expected:-" + expected + ", Actual:-" + Actual);
        Assert.assertEquals(expected, Actual);
    }

    public Logger getLogger(){
        return this.log;
    }

    public void assertValuesLessThen(String nodeName, double expectedValue, double actualValValue) {
        if (expectedValue <actualValValue) {
            String passString = "Thread ID:'" + Thread.currentThread().getId() + "' PASS:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(passString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(passString);
        } else {
            String failString = "Thread ID:'" + Thread.currentThread().getId() + "' FAIL:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(failString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(failString);
        }
    }

    public void assertValuesLessThenEquals(String nodeName, double expectedValue, double actualValValue) {
        if (expectedValue <=actualValValue) {
            String passString = "Thread ID:'" + Thread.currentThread().getId() + "' PASS:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(passString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(passString);
        } else {
            String failString = "Thread ID:'" + Thread.currentThread().getId() + "' FAIL:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(failString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(failString);
        }
    }

    public void assertContains(String nodeName, String expectedValue, String actualValValue) {
        if (actualValValue.contains(expectedValue)) {
            String passString = "Thread ID:'" + Thread.currentThread().getId() + "' PASS:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(passString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(passString);
        } else {
            String failString = "Thread ID:'" + Thread.currentThread().getId() + "' FAIL:: Expected Node Name : '"
                    + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName
                    + "' and Values :'" + actualValValue + "'";
            scenario.attach(failString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(failString);
        }
    }
    public void assertStatus(String status,String description) {
        if (status.equals("Pass")) {

            String statusString = "Thread ID:'" + Thread.currentThread().getId() + "' PASS::" + description;
            scenario.attach(statusString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(statusString);
        } else if(status.equals("Fail")){
            String statusString = "Thread ID:'" + Thread.currentThread().getId() + "' FAIL::" + description ;
            scenario.attach(statusString, "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info(statusString);
            Assert.fail(description);
        }
    }
}
