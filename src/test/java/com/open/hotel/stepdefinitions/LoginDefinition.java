package com.open.hotel.stepdefinitions;

import com.open.hotel.config.Config;
import com.open.hotel.pages.Login;
import com.open.hotel.pages.MobileLogin;
import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.webDriverFactory.ManagerDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class LoginDefinition {
	
	public Login login;
	WebDriver driver;

	@Given("Open Browser")
	public void Open_Browser() throws MalformedURLException {
		String browser = System.getProperty("Browser");
		if (browser == null) {
			browser = Config.properties.getProperty("Browser");
		}
		String ExecutionMode = System.getProperty("ExecutionMode");
		if (ExecutionMode == null) {
			ExecutionMode = Config.properties.getProperty("ExecutionMode");
		}
		String RemoteURL = System.getProperty("RemoteURL");
		if (RemoteURL == null) {
			RemoteURL = Config.properties.getProperty("RemoteURL");
		}
		String testCaseID = VariableManager.getInstance().getVariables().getVar("testCaseID").toString();
		String testName = Config.properties.getProperty("BuildName") + "- Test Case Id : '" + testCaseID + "'";
		String buildId = Config.properties.getProperty("BuildId");
		driver = ManagerDriver.getInstance().getDriver(ExecutionMode, browser, RemoteURL, testName, buildId);
		VariableManager.getInstance().getVariables().setVar("driver", driver);
		this.login = new Login();
	}

	@Given("User is able Launch the hotel application")
	public void user_is_able_Launch_the_hotel_application () throws InterruptedException {
		login.lauchApplication();
	}
	
	@When("User enters the {string} and {string} and Click LogIn button")
	public void user_enters_the_and(String arg1, String arg2) throws Exception {
		login.login(arg1, arg2);
	}

	@Then("LogOut application")
	public void logout_application() throws Exception {
		login.LogOut();
	}

	@When("User enters the user name and password and Click LogIn button")
	public void user_enters_and_password() throws Exception {
		MobileLogin mobilrLogin = new MobileLogin();
		mobilrLogin.login();
	}

}