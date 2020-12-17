package com.open.hotel.assertions;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.cucumber.java.Scenario;

public class Assertions {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    public void assertValues(String nodeName, String expectedValue, String actualValValue){
        Scenario scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        if(expectedValue.equalsIgnoreCase(actualValValue)){
            String passString = "PASS:: Expected Node Name : '" + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName + "' and Values :'" + actualValValue + "'";
            scenario.write(passString);
            log.info(passString);
        }else{
            String failString = "FAIL:: Expected Node Name : '" + nodeName + "' and Value :'" + expectedValue + "' - Actual Node Name : '" + nodeName + "' and Values :'" + actualValValue + "'";
            scenario.write(failString);
            log.info(failString);
        }
    }

}
