package com.open.hotel.work;


import com.open.hotel.mobileutils.AppiumUtils;
import io.appium.java_client.AppiumBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class POM1 extends AppiumUtils {

	WebDriver driver = null;
	String pageName = "Login Page";

	//@iOSXCUITFindBy(accessibility = "test-Username")
	@AndroidFindBy(accessibility = "test-Username")
	private WebElement userName;

	//@iOSXCUITFindBy(accessibility = "test-Password")
	@AndroidFindBy(accessibility = "test-Password")
	private WebElement password;

	//@iOSXCUITFindBy(accessibility = "test-LOGIN")
	@AndroidFindBy(accessibility = "test-LOGIN")
	private WebElement login;
	By menuOption;
	public POM1(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void login(String userNameStr, String passwordStr) {
		WebElement username1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
		username1.click();
		username1.sendKeys("standard_user");
		//clickElement(userName, "Username", this.pageName);
		//type(userName, userNameStr, "Username", this.pageName);
		//clickElement(password, "Password", this.pageName);
		WebElement username2 = driver.findElement(menuOption);
		username2.sendKeys("MyData");
		password.click();
		password.sendKeys(passwordStr);
		type(password, passwordStr, "Password", this.pageName);
		clickElement(login, "Login", this.pageName);
	}

}