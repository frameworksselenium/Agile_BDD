package com.open.qa.util.threadLevelVariables;

import io.cucumber.java.Scenario;
public class ScenarioVariableManager {
	
	private static ScenarioVariableManager instance = null;
	
	public static ScenarioVariableManager getInstance() {
		if(instance == null) {
			return new ScenarioVariableManager();
		}
		return instance;
	}
    ThreadLocal<ScenarioVariable> tc = new ThreadLocal<ScenarioVariable>();

   	public ScenarioVariable getScenarioVariable() {

   		return tc.get();
   	}

    public void setScenarioVariable(ScenarioVariable tm) {

   		tc.set(tm);
    }

}
