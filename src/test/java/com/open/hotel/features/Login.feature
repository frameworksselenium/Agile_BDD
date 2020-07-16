@SmokeTest
Feature: login hotel application

  @SmokeTest
  Scenario: login to the hotel application
    Given User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123"
    And User clicks the Log in button 
    Then User naviaged to home page 
	And LogOut application
