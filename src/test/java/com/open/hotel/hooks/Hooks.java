package com.open.hotel.hooks;

import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.threadVariables.Variables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class Hooks {

	@Before()
	public void beforeScenario(Scenario scenario) {
		String testCaseName = scenario.getName().split(":")[1];
		String testCaseID = scenario.getName().split(":")[0];
		Variables variables = new Variables();
		VariableManager.getInstance().setVariables(variables);
		VariableManager.getInstance().getVariables().setVar("testCaseName", testCaseName);
		VariableManager.getInstance().getVariables().setVar("testCaseID", testCaseID);
		VariableManager.getInstance().getVariables().setVar("scenario", scenario);
	}

	@After()
	public void afterScenario(Scenario scenario) throws ParseException {
		WebDriver driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}