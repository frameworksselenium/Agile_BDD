package com.open.hotel.pages.mobile.saucelabs;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MobileLogin extends AppiumUtils {

	org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

	AppiumDriver driver = null;
	String pageName = "Login Page";

	//@iOSXCUITFindBy(accessibility = "test-Username")
	//@AndroidFindBy(accessibility = "test-Username")
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeTextField[@name='test-Username'] | //android.widget.EditText[@content-desc='test-Username']")
	private WebElement userName;

	//@iOSXCUITFindBy(accessibility = "test-Password")
	//@AndroidFindBy(accessibility = "test-Password")
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeSecureTextField[@name='test-Password'] | //android.widget.EditText[@content-desc='test-Password']")
	private WebElement password;

	//@iOSXCUITFindBy(accessibility = "test-LOGIN")
	//@AndroidFindBy(accessibility = "test-LOGIN")
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeOther[@name='test-LOGIN'] | //android.view.ViewGroup[@content-desc='test-LOGIN']")
	private WebElement login;

	public MobileLogin(){
		this.driver = (AppiumDriver) VariableManager.getInstance().getVariables().getVar("driver");
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void login(String userNameStr, String passwordStr) {
		clickElement(userName, "Username", this.pageName);
		type(userName, userNameStr, "Username", this.pageName);
		clickElement(password, "Password", this.pageName);
		type(password, passwordStr, "Password", this.pageName);
		clickElement(login, "Login", this.pageName);
	}

}