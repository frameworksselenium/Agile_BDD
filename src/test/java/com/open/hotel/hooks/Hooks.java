package com.open.hotel.hooks;

import com.open.hotel.config.Config;
import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.threadVariables.Variables;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.ParseException;

public class Hooks {
	public AppiumDriverLocalService service;

	@Before()
	public void beforeScenario(Scenario scenario) throws Exception {
		String testCaseName = scenario.getName().split(":")[1];
		String testCaseID = scenario.getName().split(":")[0];
		Variables variables = new Variables();
		VariableManager.getInstance().setVariables(variables);

		VariableManager.getInstance().getVariables().setVar("testCaseName", testCaseName);
		VariableManager.getInstance().getVariables().setVar("testCaseID", testCaseID);
		VariableManager.getInstance().getVariables().setVar("scenario", scenario);

		if(!checkIfAppiumServerIsRunnning(4723)) {
			AppiumUtils appiumUtils = new AppiumUtils();
			service = appiumUtils.startAppiumServer(Config.properties.getProperty("AppiumServerIP"), Integer.parseInt(Config.properties.getProperty("AppiumServerPort")));
		}
	}

	@After()
	public void afterScenario(Scenario scenario) throws ParseException {
		WebDriver driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
		if (driver != null) {
			//driver.close();
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

}