
Feature: login hotel application

  @all
  Scenario: 101:login to the hotel application
    Given Open Browser
    Given User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123" and Click LogIn button
	#And LogOut application
