package com.open.hotel.hooks;

import com.open.qa.web.uiUtils.UiUtils;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks extends UiUtils {

	@Before(order = 1)
	public void beforeScenario(Scenario scenario){

	}
			
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		WebDriver driver = ManagerDriver.getInstance().getWebDriver();
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}
}