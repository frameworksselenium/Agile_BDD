package com.open.hotel.stepdefinitions;


import com.open.hotel.runner.TestNGRunner;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import com.open.qa.web.webDriverFactory.LocalDriverFactory;
import com.open.qa.web.webDriverFactory.RemoteDriverFactory;
import com.open.qa.web.webDriverFactory.ManagerDriver;
public class OpenBrowserSetpDefinition {

	public OpenBrowserSetpDefinition(){

	}

	@Given("Open application")
	public void logout_application() throws Exception {
		WebDriver driver = null;
		if (TestNGRunner.properties.getProperty("ExecutionMode").contains("Local")) {
			driver = LocalDriverFactory.getInstance().createNewDriver();
		} else if (TestNGRunner.properties.getProperty("ExecutionMode").contains("Remote")) {
			driver = RemoteDriverFactory.getInstance().createNewDriver();
		}
		ManagerDriver.getInstance().setWebDriver(driver);
	}

}