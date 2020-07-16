Feature: search room in hotel application

  @SmokeTest
  Scenario: search room
    Given Open application
    Then User is able Launch the hotel application using "http://adactin.com/HotelApp/index.php"
    When User enters the "kmanubolu" and "India@123" and click on login button
    #Then User naviaged to home page
    And user enters the required information in search hotel page
      | UILables          | Values      |
      | Location          | Sydney      |
      | Hotels            | Hotel Creek |
      | Room Type         | Standard    |
      | Number of Rooms   | 1 - One     |
      | Check In Date     | 17/07/2020  |
      | Check Out Date    | 18/07/2020  |
      | Adults per Room   | 1 - One     |
      | Children per Room | 2 - Two     |
    And user clicks the search button
    Then user navigates to Select Hotel page
    And LogOut application