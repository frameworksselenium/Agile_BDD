package com.open.hotel.stepdefinitions;

import com.open.hotel.loadConfig.Config;
import com.open.hotel.pages.Login;
import com.open.hotel.pages.Search;
import com.open.hotel.uiUtils.UIUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.ParseException;
import java.util.*;

public class SetpDefinition {
	
	public Login login;
	public Search search;

	String testCaseName = null;
	String testCaseID = null;
	WebDriver driver = null;

	@Before()
	public void beforeScenario(Scenario scenario){
		testCaseName = scenario.getName().split(":")[1];
		testCaseID = scenario.getName().split(":")[0];
	}

	@After()
	public void afterScenario(Scenario scenario) throws ParseException {
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}

	@Given("Open Browser")
	public void Open_Browser() {
		String browser = Config.properties.getProperty("Browser");
		String driverPath = System.getProperty("user.dir");
		if (browser.toUpperCase().contains("CH")) {
			System.setProperty("webdriver.chrome.driver", driverPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("no-sandbox");
			options.addArguments("start-maximized");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			this.driver = new ChromeDriver(options);
		}
		login = new Login(this.driver, this.testCaseName, this.testCaseID);
		search = new Search(this.driver, this.testCaseName, this.testCaseID);
	}



	@Given("User is able Launch the hotel application using {string}")
	public void user_is_able_Launch_the_hotel_application_using(String arg1) throws InterruptedException {
		login.lauchApplication(arg1);
	}
	
	@When("User enters the {string} and {string} and Click LogIn button")
	public void user_enters_the_and(String arg1, String arg2) throws Exception {
		login.login(arg1, arg2);
	}

	@Then("LogOut application")
	public void logout_application() throws Exception {
		login.LogOut();
	}

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