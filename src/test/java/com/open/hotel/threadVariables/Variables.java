package com.open.hotel.threadVariables;

import java.util.HashMap;

public class Variables {

    HashMap <Object, Object> var = new HashMap<>();

    public Object getVar(Object key) {
        Object returnValue = null;
        if (var.containsKey(key)) {
            returnValue = var.get(key);
        }
        return returnValue;
    }
    public void setVar(Object key, Object value) {
        var.put(key, value);
    }
}
