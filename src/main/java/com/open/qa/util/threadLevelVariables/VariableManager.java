package com.open.qa.util.threadLevelVariables;

public class VariableManager {
	
	private static VariableManager instance = new VariableManager();
	
	public static VariableManager getInstance()
	{
		return instance;
	}
    ThreadLocal<Variables> tc = new ThreadLocal<Variables>();

   	public Variables getVariablesManager() {

   		return tc.get();
   	}

    public void setVariablesManager(Variables tm) {

   		tc.set(tm);
    }

}
