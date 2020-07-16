Feature: login hotel application

  @SmokeTest
  Scenario: login to the hotel application
    Given Open application
    Then User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123" and click on login button
    Then User naviaged to home page
	And LogOut application
