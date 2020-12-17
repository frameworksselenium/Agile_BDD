package com.open.hotel.threadVariables;

public class VariableManager {

    private static VariableManager instance = new VariableManager();

    public  static VariableManager getInstance() {
        return instance;
    }

    ThreadLocal<Variables> variables = new ThreadLocal<Variables>();
    public void setVariables(Variables variable) {
        variables.set(variable);
    }

    public Variables getVariables() {
        return variables.get();
    }

}
