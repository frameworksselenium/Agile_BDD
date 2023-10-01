@SmokeTest
Feature: Read data from excel and prepare json payload and send request and get response

  @RestApiExcelPostRequest
  Scenario: 106:Read data from excel and prepare json payload and send request and get response
    Given Customer "GoRest" Read the data from excel "TestData.xlsx" and sheet "CreateUser" for testcase ID "TC01" and Create the JSON request using JSON template "CreateUser_Request"
    When I submit the JSON POST request with endpoint "/users/"
    Then Validate "Krishna103" from "name" node in JSON response - json path "$.data.name"
    Then Validate "Krishna103@gmail.com" from "email" node in JSON response - json path "data.email"
    Then Validate "Male" from "gender" node in JSON response - json path "data.gender"
    Then Validate "Active" from "status" node in JSON response - json path "data.status"
