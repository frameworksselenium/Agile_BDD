@SmokeTest
Feature: search room in hotel application

  @SmokeTest
  Scenario: search room
    Given User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123"
    And User clicks the Log in button 
    Then User naviaged to home page 
	And user enters the required information in search hotel page
	And user clicks the search button
	Then user navigates to Select Hotel page
	And LogOut application