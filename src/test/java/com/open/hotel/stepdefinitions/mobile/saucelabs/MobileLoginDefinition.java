package com.open.hotel.stepdefinitions.mobile.saucelabs;

import com.open.hotel.pages.mobile.saucelabs.MobileLogin;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class MobileLoginDefinition {
	
	@When("Mobile  - Login Android and IOS Sauce Labs App and Verify")
	public void Mobile_Login() throws Exception {
		MobileLogin mobilrLogin = new MobileLogin();
		mobilrLogin.login("standard_user", "secret_sauce");
	}

}