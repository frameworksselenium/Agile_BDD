package com.open.qa.util.threadLevelVariables;

import java.util.HashMap;

public class Variables {

	HashMap<String,String> mh = new HashMap<String,String>(); 

	public void setObject(String key, String value) {
		mh.put(key, value);
	}

	public String getObject(String variableKeyName) {
		String retuValue = null;
		if(mh.containsKey(variableKeyName)) {
		  retuValue = mh.get(variableKeyName);
		}
		return retuValue;
	}
}
