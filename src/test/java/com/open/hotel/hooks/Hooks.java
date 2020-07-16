package com.open.hotel.hooks;

import com.open.qa.web.uiUtils.UiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends UiUtils {

	@Before(order = 1)
	public void beforeScenario(Scenario scenario){

	}
			
	@After(order = 1)
	public void afterScenario(Scenario scenario) {

	}
}