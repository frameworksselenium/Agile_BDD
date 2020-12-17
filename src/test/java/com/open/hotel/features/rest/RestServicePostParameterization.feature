Feature: send json request and get response

  @RestApiPostRequestParameterization
  Scenario: 108:send json request for scenario 1 and get response
    Given Customer "GoRest" Create JSON request using JSON template "CreateUser_Request" with parameters
      | NodeName          | Values      |
      | name              | raj1      |
      | gender            | Male |
      | status            | Active    |
      | email            | raj1@gmail.com    |
    When I submit the JSON POST request with endpoint "/users/"
    Then Validate "Krishna" from "name" node in JSON response - json path "$.data.name"
    Then Validate "Krishna@gmail.com" from "email" node in JSON response - json path "data.email"
    Then Validate "Male" from "gender" node in JSON response - json path "data.gender"
    Then Validate "Active" from "status" node in JSON response - json path "data.status"