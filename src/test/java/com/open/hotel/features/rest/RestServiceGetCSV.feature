Feature: send json request and get response

  @RestApiCSVGetRequest
  Scenario: 105:send json request for scenario 1 and get response CSV
    Given Customer "GoRest" Read the data from CSV "GetUser.csv" for testcase ID "TC01"
    When I submit the JSON GET request with endpoint "/users/" with parameter "id"
    Then Validate "ram40" from "name" node in JSON response - json path "$.data.name"
    Then Validate "ram40@15ce.com" from "email" node in JSON response - json path "data.email"
    Then Validate "Male" from "gender" node in JSON response - json path "data.gender"
    Then Validate "Active" from "status" node in JSON response - json path "data.status"

