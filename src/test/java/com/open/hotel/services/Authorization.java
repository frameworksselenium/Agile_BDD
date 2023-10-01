package com.open.hotel.services;

import java.util.HashMap;
import java.util.Map;
import com.open.hotel.threadVariables.VariableManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;

public class Authorization extends Base {

    String AuthEndPoint;
    String grant_type;
    String username;
    String password;
    String BasicAuthorization;
    String AuthContentType;
    Response response = null;
    public Authorization() {
        super();
        this.AuthEndPoint = prop.getProperty("WMS_Auth_EndPoint");
        this.BasicAuthorization = prop.getProperty("WMS_Auth_Basic_Authorization");
        this.AuthContentType = prop.getProperty("WMS_Auth_ContentType");
        this.grant_type = prop.getProperty("WMS_Auth_FormParams_GrantType");
        this.username = prop.getProperty("WMS_Auth_FormParams_UserName");
        this.password = prop.getProperty("WMS_Auth_FormParams_Password");
    }

    public Map<String, String> buildFormParameters() {

        Map<String, String> form = new HashMap<>();
        form.put("grant_type", grant_type);
        form.put("username", username);
        form.put("password", password);
        return form;
    }

    public Map<String, String> buildHeaderParameters() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", BasicAuthorization);
        //header.put("Content-Type", AuthContentType);
        return header;
    }

    public Authorization send() throws ParseException {
        RequestSpecification sp = RestAssured.given().relaxedHTTPSValidation().baseUri(authBaseURL);
        response = sp.headers(buildHeaderParameters()).formParams(buildFormParameters()).post(AuthEndPoint);
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed : HTTP error code :" + statusCode);
        }
        this.accessToken = response.body().jsonPath().getString("access_token");
        VariableManager.getInstance().getVariables().setVar("response", response);
        VariableManager.getInstance().getVariables().setVar("access_token", accessToken);
        return this;
    }

    public Authorization showResponse() throws ParseException {
        assertions.getLogger().info("======== Authorization Microservice Response : " + response.getBody().asString());
        assertions.getLogger().info("======== Access Token :                                         " + response.body().jsonPath().getString("access_token"));
        return this;
    }

}
