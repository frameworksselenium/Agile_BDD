package com.open.hotel.pages;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.config.Config;
import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.uiUtils.UIUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Login  extends UIUtils {

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

	public Login(){
		this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
		PageFactory.initElements(this.driver, this);
	}
	public void lauchApplication() throws InterruptedException {
		String Env = System.getProperty("Environment");
		if (Env == null) {
			Env = Config.properties.getProperty("Environment");
		}
			String url = Config.properties.getProperty(Env);
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