package com.open.qa.util.threadLevelVariables;
import io.cucumber.java.Scenario;

import java.util.HashMap;

public class ScenarioVariable {


	Scenario scenario = null;

	public void setObject(Scenario scenario) {

		this.scenario = scenario;
	}

	public Scenario getObject()
	{
		return this.scenario;
	}

}
