package com.open.hotel.pages;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.uiUtils.UIUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileLogin extends UIUtils {

	org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

	WebDriver driver = null;
	String pageName = "Login Page";
	@FindBy(how = How.ID, using = "username")
	WebElement UserName;
	@FindBy(how =How.ID, using = "password")
	WebElement Password;
	@FindBy(how =How.ID, using = "login")
	WebElement Login;
	@FindBy(how =How.ID, using = "/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")
	WebElement LogOut;

	public MobileLogin(){
		this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
		PageFactory.initElements(this.driver, this);
	}

	public void login() throws Exception {

		WebElement username1 = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("test-Username")));
		username1.click();
		username1.sendKeys("standard_user");

		WebElement password = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("test-Password")));
		password.click();
		password.sendKeys("secret_sauce");

		WebElement login = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("test-LOGIN")));
		login.click();
	}


}