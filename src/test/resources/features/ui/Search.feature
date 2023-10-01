Feature: search room in hotel application

  @Search @UI
  Scenario: 102:Search Hotel Room
    Given Open Browser
    Given User is able Launch the hotel application
    When User enters the "kmanubolu" and "PJ15U9" and Click LogIn button
	And user enters the required information and clicks the search button in search hotel page
      | UILables          | Values      |
      | Location          | Sydney      |
      | Hotels            | Hotel Creek |
      | Room Type         | Standard    |
      | Number of Rooms   | 1 - One     |
      | Check In Date     | 17/07/2020  |
      | Check Out Date    | 18/07/2020  |
      | Adults per Room   | 1 - One     |
      | Children per Room | 2 - Two     |
	#And LogOut application