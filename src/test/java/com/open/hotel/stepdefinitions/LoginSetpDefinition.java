package com.open.hotel.stepdefinitions;

import com.open.hotel.pages.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSetpDefinition {
	
	public Login login = new Login();

	@Given("User is able Launch the hotel application using {string}")
	public void user_is_able_Launch_the_hotel_application_using(String arg1) throws InterruptedException {
		login.lauchApplication(arg1);
	}
	
	@When("User enters the {string} and {string} and click on login button")
	public void user_enters_the_and(String arg1, String arg2) throws Exception {
		login.login(arg1, arg2);
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