Feature: send json request and get response

  @RestApiCSVPostRequest
  Scenario: 104:send json request for scenario 1 and get response CSV
    Given Customer "GoRest" Read the data from CSV "CreateUser.csv" for testcase ID "TC01" and Create the JSON request using JSON template "CreateUser_Request"
    When I submit the JSON POST request with endpoint "/users/"
    Then Validate "Krishna" from "name" node in JSON response - json path "data.name"
    Then Validate "Krishna@gmail.com" from "email" node in JSON response - json path "data.email"
    Then Validate "Male" from "gender" node in JSON response - json path "data.gender"
    Then Validate "Active" from "status" node in JSON response - json path "data.status"

