package com.open.hotel.stepdefinitions;

import com.open.hotel.assertions.Assertions;
import com.open.hotel.dataParsers.CSVData;
import com.open.hotel.dataParsers.ExcelData;
import com.open.hotel.dataParsers.TableData;
import com.open.hotel.services.Payload;
import com.open.hotel.services.RestServices;
import com.open.hotel.threadVariables.VariableManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RestDefinitions {

    TableData tableData = new TableData();
    CSVData csvData = new CSVData();
    Payload payLoad = new Payload();
    RestServices restServices = new RestServices();
    ExcelData excelData = new ExcelData();
    Assertions assertions = new Assertions();

    //Post
    @Given("Customer {string} Read the data from CSV {string} for testcase ID {string} and Create the JSON request using JSON template {string}")
    public void CSV_Create_JSON_request(String customer, String csvFileName, String testCaseID, String template) {
        VariableManager.getInstance().getVariables().setVar("customerName", customer);
        VariableManager.getInstance().getVariables().setVar("csvFileName", csvFileName);

        Map<String, String> data = this.csvData.readData(csvFileName, testCaseID);
        String requestPayLoad = this.payLoad.payLoadPreparation(template, data);

        VariableManager.getInstance().getVariables().setVar("requestPayLoad", requestPayLoad);

    }

    @When("I submit the JSON POST request with endpoint {string}")
    public void I_submit_the_JSON_POST_request(String endPoint){
        String customerName = VariableManager.getInstance().getVariables().getVar("customerName").toString();
        String requestPayLoad = VariableManager.getInstance().getVariables().getVar("requestPayLoad").toString();
        String response = this.restServices.getResponseFromPostMethod(requestPayLoad, endPoint, customerName);
        VariableManager.getInstance().getVariables().setVar("response", response);
    }

    @When("Validate {string} from {string} node in JSON response - json path {string}")
    public void Validate_ExpectedValue_from_JSON_response(String expectedVal, String nodeName, String jsonPath) {
        String response = VariableManager.getInstance().getVariables().getVar("response").toString();
        String actualValue = this.restServices.getValueFromJsonFile(response, jsonPath);
        this.assertions.assertValues(nodeName, expectedVal, actualValue);
    }


    @Given("Customer {string} Read the data from excel {string} and sheet {string} for testcase ID {string} and Create the JSON request using JSON template {string}")
    public void Create_JSON_request(String customerName, String excelFileName, String sheetName, String testCaseID, String template) throws IOException {
        VariableManager.getInstance().getVariables().setVar("customerName", customerName);
        Map<String, String> data = this.excelData.readData(excelFileName, sheetName, "TestCaseid", testCaseID);

        String requestPayLoad = this.payLoad.payLoadPreparation(template, data);

        VariableManager.getInstance().getVariables().setVar("requestPayLoad", requestPayLoad);

    }

    //Get
    @Given("Customer {string} Read the data from CSV {string} for testcase ID {string}")
    public void CSV_Create_JSON_Get_request(String customer, String csvFileName, String testCaseID) {
        VariableManager.getInstance().getVariables().setVar("customerName", customer);
        VariableManager.getInstance().getVariables().setVar("csvFileName", csvFileName);
        HashMap<String, String> data = this.csvData.readData(csvFileName, testCaseID);
        VariableManager.getInstance().getVariables().setVar("data", data);

    }

    @Given("Customer {string} Read the data from excel {string} and sheet {string} for testcase ID {string}")
    public void Excel_Create_JSON_Get_request(String customerName, String excelFileName, String sheetName, String testCaseID) throws IOException {
        VariableManager.getInstance().getVariables().setVar("customerName", customerName);
        HashMap<String, String> data = this.excelData.readData(excelFileName, sheetName, "TestCaseid", testCaseID);
        VariableManager.getInstance().getVariables().setVar("data", data);

    }

    @When("I submit the JSON GET request with endpoint {string} with parameter {string}")
    public void I_submit_the_JSON_GET_request(String endPoint, String parameterName){
        String customerName = VariableManager.getInstance().getVariables().getVar("customerName").toString();
        Map<String, String> data = (HashMap<String, String>)VariableManager.getInstance().getVariables().getVar("data");

        String parameterValue = data.get(parameterName);

        String parameterEndPoint = endPoint + parameterValue;
        String response = this.restServices.getResponseFromGetMethod(parameterEndPoint, customerName);
        VariableManager.getInstance().getVariables().setVar("response", response);
    }

    ////Cucumber parameterization for Json ////////////////////////////////////
    @Given("Customer {string} Create JSON request using JSON template {string} with parameters")
    public void Create_JSON_request_using_JSON_template(String customerName, String template, DataTable dt) {
        Map<String, String> data = this.tableData.convertDataTableValuesToList(dt);
        VariableManager.getInstance().getVariables().setVar("customerName", customerName);
        String requestPayLoad = this.payLoad.payLoadPreparation(template, data);
        VariableManager.getInstance().getVariables().setVar("requestPayLoad", requestPayLoad);
    }

}
