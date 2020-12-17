package com.open.hotel.stepdefinitions;

import com.open.hotel.pages.Search;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.*;

public class SearchDefinition {

	public Search search;

	@And("user enters the required information and clicks the search button in search hotel page")
	public void user_enters_the_required_information_in_search_hotel_page(DataTable dt) {
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

}