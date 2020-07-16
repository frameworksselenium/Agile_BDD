package com.open.hotel.stepdefinitions;

import com.open.hotel.pages.Login;
import com.open.hotel.pages.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSetpDefinition {
	
	public Search search = new Search();

	@And("user enters the required information in search hotel page")
	public void user_enters_the_required_information_in_search_hotel_page() throws Throwable {
		search.enterRoomSearchInfo();
	}
	
	@And("user clicks the search button")
	public void user_clicks_the_search_button() throws Throwable {
		search.clickOnSearch();
	}
	
	@Then("user navigates to Select Hotel page")
	public void user_navigates_to_Select_Hotel_page() throws Throwable {
		search.validateHotelRoomSearch();
	}

}