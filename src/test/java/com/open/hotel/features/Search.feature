Feature: search room in hotel application

  @SmokeTest1
  Scenario: search room
    Given Open application
    Then User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123" and click on login button
    Then User naviaged to home page
	And user enters the required information in search hotel page
	And user clicks the search button
	Then user navigates to Select Hotel page
	And LogOut application