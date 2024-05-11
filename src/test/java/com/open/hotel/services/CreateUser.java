package com.open.hotel.services;

import com.jayway.jsonpath.JsonPath;
import com.open.hotel.config.Config;
import com.open.hotel.threadVariables.VariableManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

public class CreateUser extends BaseService {

    JSONObject jsonObject = null;
    String EndPoint;
    Response response = null;
    JSONParser parser = new JSONParser();
    JSONObject responseJSON;
    String payload = null;

    public CreateUser() {
        super();
        this.EndPoint = prop.getProperty("WMS_PurchaseOrder_Search_EndPoint");
    }

    public CreateUser getPayload() {
        try {
            Properties prop = Config.properties;
            String jsonFilePath = System.getProperty("user.dir") + "//src//test/resources//templates//" + "PurchaseOrderSearch.json";
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonFilePath));
            this.jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CreateUser updatePayload(String poNumber) {
        try {
            JsonPath.parse(jsonObject).set("$.Query", "PurchaseOrderId in ('" + poNumber + "')");
            payload = jsonObject.toJSONString();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CreateUser send() {
        Map<String, String> header = buildHeaderMap();
        try {
            RequestSpecification rsp = RestAssured.given().relaxedHTTPSValidation().baseUri(baseURL);
            response = rsp.headers(header).body(jsonObject).post(EndPoint);
            int statusCode = response.getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed : HTTP error code :" + statusCode);
            }
            responseJSON = (JSONObject) parser.parse(response.getBody().asString());
            VariableManager.getInstance().getVariables().setVar("response", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CreateUser showPayload() {
        assertions.getLogger().info("======== Request : " + payload);
        return this;
    }

    public CreateUser showResponse() {
        assertions.getLogger().info("======== Response : " + response.getBody().asString());
        return this;
    }

    public String getVendorIDBasedOnPO(String poNumber) throws ParseException {
        String vendorID = ((JSONArray) JsonPath.parse(responseJSON).read("$.data[?(@.PurchaseOrderId=='" + poNumber + "')].VendorId")).get(0).toString();
        String vendorID1 =  JsonPath.parse(responseJSON).read("$.data[?(@.PurchaseOrderId=='" + poNumber + "')].VendorId").toString();
        return vendorID;
    }

}
