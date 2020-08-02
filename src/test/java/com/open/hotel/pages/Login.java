package com.open.hotel.pages;

import com.open.hotel.Logger.LoggerClass;
import com.open.hotel.uiUtils.UIUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Login  extends UIUtils {

	String testCaseName = null;
	String testCaseID = null;

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

	public Login(WebDriver driver, String testCaseName, String testCaseID){
		super(driver, testCaseName,testCaseID);
		this.testCaseName = testCaseName;
		this.testCaseID = testCaseID;
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void lauchApplication(String url) throws InterruptedException {
		org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), testCaseID);
		driver.get(url);
		Thread.sleep(1000);
		log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' opened applicaion '" + url + "'");

	}

	public void login(String userName, String password) throws Exception {

		type(UserName, userName,"UserName", this.pageName);
		type(Password, password,"Password", this.pageName);
		clickElement(Login, "Login", this.pageName);
	}

	public void LogOut() throws Exception {

		clickElement(LogOut, "LogOut", this.pageName);
	}

}