package com.open.hotel.services;
/*
import com.jayway.jsonpath.JsonPath;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.config.Config;
import com.open.hotel.threadVariables.VariableManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;*/

public class RestServices {

	/*org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

	public RestServices() {
	}

	public String getResponseFromPostMethod(String jsonRequest, String endPoint, String customerName) {
		String responseString = null;
		try {
			String region = Config.properties.getProperty("Environment");
			String url = Config.properties.getProperty("EndPointURL_" + region + "_" + customerName);
			log.info("end point url - " + url + endPoint);
			CloseableHttpClient client = HttpClients.createDefault();
		    HttpPost httpPost = new HttpPost(url + endPoint);
		    StringEntity entity = new StringEntity(jsonRequest);
		    httpPost.setEntity(entity);
		    if (url != null){
				String header = Config.properties.getProperty("Header_" + region + "_" + customerName);
				String[] headerValues = header.split(";");
				for(int i = 0; i < headerValues.length; ++i) {
					String[] values = headerValues[i].split(":");
					httpPost.setHeader(values[0],values[1]);	
				}
			}
		    CloseableHttpResponse response = client.execute(httpPost);
		    if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code :" +  response.getStatusLine().getStatusCode());
			}
			log.info("Response Status - " + response.getStatusLine().getStatusCode());
		    HttpEntity entity1 = response.getEntity();
		    responseString = EntityUtils.toString(entity1);
			log.info("Response  - " + responseString);

			client.close();
		}catch(Exception var1) {
			log.info("response failed " + var1 + System.lineSeparator());
		}
		return responseString;
	}

	public String getResponseFromGetMethod(String endPoint, String customerName) {
		String responseString = null;
		try {
			String region = Config.properties.getProperty("Environment");
			String url = Config.properties.getProperty("EndPointURL_" + region + "_" + customerName);
			log.info("end point url - " + url + endPoint);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url + endPoint);

			if (url != null){
				String header = Config.properties.getProperty("Header_" + region + "_" + customerName);
				String[] headerValues = header.split(";");
				for(int i = 0; i < headerValues.length; ++i) {
					String[] values = headerValues[i].split(":");
					httpGet.setHeader(values[0],values[1]);
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code :" +  response.getStatusLine().getStatusCode());
			}
			log.info("Response Status - " + response.getStatusLine().getStatusCode());
			HttpEntity entity1 = response.getEntity();
			responseString = EntityUtils.toString(entity1);
			log.info("Response  - " + responseString);

			httpClient.close();
		}catch(Exception var1) {
			log.info("response failed " + var1 + System.lineSeparator());
		}
		return responseString;
	}

	public String getValueFromJsonFile(String response, String jsonPath) {
		try {
			JSONParser parser = new JSONParser();
			Object jsonObject = parser.parse(response);
			String result = JsonPath.read(jsonObject, jsonPath).toString();
			return result;
		} catch (Exception var5) {
			log.info("Exception - " + var5);
			return null;
		}
	}*/

}
