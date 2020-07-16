package com.open.hotel.stepdefinitions;

import com.open.hotel.pages.Login;
import com.open.hotel.pages.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SetpDefinition {
	
public Login login = new Login();
public Search search = new Search();

	@And("user enters the required information in search hotel page")
	public void user_enters_the_required_information_in_search_hotel_page() throws Throwable {
		search.enterRoomSearchInfo();
	}
	
	@And("user clicks the search button")
	public void user_clicks_the_search_button() throws Throwable {
		search.clickSearchOnSearchPage();
	}
	
	@Then("user navigates to Select Hotel page")
	public void user_navigates_to_Select_Hotel_page() throws Throwable {
		search.validateHotelRoomSearch();
	}

	@Given("User is able Launch the hotel application using {string}")
	public void user_is_able_Launch_the_hotel_application_using(String arg1) throws InterruptedException {
		login.lauchApplication(arg1);
	}
	
	@When("User enters the {string} and {string}")
	public void user_enters_the_and(String arg1, String arg2) throws Exception {
		login.login(arg1, arg2);
	}

	@And("User clicks the Log in button")
	public void user_clicks_the_Log_in_button() throws Exception {
		login.clickOnSignIn();
	}

	@Then("User naviaged to home page")
	public void user_naviaged_to_home_page() {
		login.validateHomePage();
	}

	@Then("LogOut application")
	public void logout_application() throws Exception {
		login.LogOut();
	}

}