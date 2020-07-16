package com.open.hotel.stepdefinitions;


import com.open.hotel.pages.Search;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchSetpDefinition {
	
	public Search search = new Search();

	@And("user enters the required information in search hotel page")
	public void user_enters_the_required_information_in_search_hotel_page(DataTable dt) throws Throwable {
		HashMap<String, String> val = new HashMap<String, String>();

		List<List<String>> list  = dt.asLists(String.class);
		List<Map<String, String>> map  = dt.asMaps(String.class, String.class);

		if(list.get(0).size() != 2){
			throw new RuntimeException("Failed data load");
		}
		for(int i=0; i<list.size();i++){
			val.put(list.get(i).get(0), list.get(i).get(1));
		}
		search.enterRoomSearchInfo(val);
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