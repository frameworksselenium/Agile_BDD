package com.open.hotel.stepdefinitions;

import com.open.hotel.config.Config;
import com.open.hotel.pages.Login;
import com.open.hotel.threadVariables.VariableManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class LoginDefinition {
	
	public Login login;

	@Given("Open Browser")
	public void Open_Browser() {
		String browser = System.getProperty("Browser");
		if (browser == null) {
			browser = Config.properties.getProperty("Browser");
		}
		String ExecutionMode = System.getProperty("ExecutionMode");
		if (ExecutionMode == null) {
			ExecutionMode = Config.properties.getProperty("ExecutionMode");
		}
		String RemoteType = System.getProperty("RemoteType");
		if (RemoteType == null) {
			RemoteType = Config.properties.getProperty("RemoteType");
		}
		String RemoteURL = System.getProperty("RemoteURL");
		if (RemoteURL == null) {
			RemoteURL = Config.properties.getProperty("RemoteURL");
		}
		WebDriver driver;
		String driverPath = System.getProperty("user.dir");
		if(ExecutionMode.contains("Local")) {
			if (browser.toUpperCase().contains("CH")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("no-sandbox");
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
				driver = new ChromeDriver(options);
				VariableManager.getInstance().getVariables().setVar("driver", driver);
			}
		} else if (ExecutionMode.contains("Grid")) {
			// RemoteWebDriver driver = null;
			DesiredCapabilities cap = null;
			if (browser.toUpperCase().contains("CH")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("no-sandbox");
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
				cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				cap.setBrowserName("chrome");
				if (RemoteType.contains("VM")) {
					cap.setPlatform(Platform.WINDOWS);
				} else if (RemoteType.contains("AWS")) {
					cap.setPlatform(Platform.LINUX);
				}
			}
			try {
				driver = new RemoteWebDriver(new URL(RemoteURL), cap);
				VariableManager.getInstance().getVariables().setVar("driver", driver);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		login = new Login();
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
}