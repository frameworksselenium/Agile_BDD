package com.open.hotel.hooks;

import com.open.qa.util.threadLevelVariables.ScenarioVariable;
import com.open.qa.util.threadLevelVariables.ScenarioVariableManager;
import com.open.qa.util.threadLevelVariables.VariableManager;
import com.open.qa.util.threadLevelVariables.Variables;
import com.open.qa.web.uiUtils.UiUtils;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class Hooks extends UiUtils {

	@Before(order = 1)
	public void beforeScenario(Scenario scenario){
		//scenario.write("Before");
		Variables variables = new Variables();
		VariableManager.getInstance().setVariablesManager(variables);
		VariableManager.getInstance().getVariablesManager().setObject("TestCaseName","TC001");

		//ScenarioVariable scenarioVariable = new ScenarioVariable();
		//ScenarioVariableManager.getInstance().setScenarioVariable(scenarioVariable);
		//ScenarioVariableManager.getInstance().getScenarioVariable().setObject(scenario);
		setScenario(scenario);
	}
			
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		WebDriver driver = ManagerDriver.getInstance().getWebDriver();
		if(driver != null){
			driver.close();
			driver.quit();
		}
		//scenario.write("After");
	}

	@BeforeStep()
	public  void beforeStep(Scenario scenario){
		//scenario.write("BeforeStep");
	}

	@AfterStep()
	public  void afterStep(Scenario scenario){
		//scenario.write("AfterStep");
	}

}