package com.open.hotel.stepdefinitions.mobile.iosuikitcatalog;

import com.open.hotel.pages.mobile.iosuikitcatalog.Views;
import com.open.hotel.pages.mobile.iosuikitcatalog.HomePage;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.When;
import org.testng.AssertJUnit;

public class IOSMobileLoginDefinition {
	
	@When("IOS Mobile - Login UI KitCatalog app and Verify")
	public void IOS_Mobile_Login() throws Exception {
		IOSDriver driver = (IOSDriver) VariableManager.getInstance().getVariables().getVar("driver");
		HomePage homePage = new HomePage(driver);
		homePage.UIView();
		homePage.Views();
		homePage.alertControllers();
		Views views = new Views(driver);
		views.fillTextLabel("Hello World");
		String actualMessage = views.getConfirmMessage();
		AssertJUnit.assertEquals(actualMessage, "A message needs to be a short, complete sentence.");
	}

}