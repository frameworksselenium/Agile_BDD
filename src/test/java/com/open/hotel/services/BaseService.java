package com.open.hotel.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.open.hotel.assertions.Assertions;
import com.open.hotel.config.Config;
import com.open.hotel.threadVariables.VariableManager;

public class BaseService {

    String authBaseURL;
    String baseURL;

    public String contentType;
    String accessToken = null;

    public String authrization;
    Properties prop = Config.properties;
    String Env = prop.getProperty("Environment");
    Assertions assertions = new Assertions();

    public BaseService() {
        this.baseURL = prop.getProperty(Env + "_GoRest_BaseURL");
        this.authBaseURL = prop.getProperty(Env + "_GoRest_Auth_BaseURL");
        this.contentType = prop.getProperty("WMS_ContentType");
        this.authrization = "Bearer " + VariableManager.getInstance().getVariables().getVar("access_token");
    }

    public Map<String, String> buildHeaderMap() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", contentType);
        header.put("Authorization", authrization);
        return header;
    }

}
