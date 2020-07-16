package com.open.hotel.pages;

import com.open.qa.web.uiUtils.UiUtils;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Login  extends UiUtils {
	public WebDriver driver = null;
	public By edt_UserName = By.id("username");
	public By edt_Password = By.id("password");
	public By ele_Login = By.id("login");
	public By ele_WelcomeText = By.xpath("/html/body/table[2]/tbody/tr[1]/td[1]");
	public By ele_LogOut = By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/a[4]");
	String page = "Login";
	public Login(){
		driver = ManagerDriver.getInstance().getWebDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how =How.ID, using = "username")
	WebElement UserName;

	@FindBy(how =How.ID, using = "password")
	WebElement Password;

	@FindBy(how =How.ID, using = "login")
	WebElement Login;

	@FindBy(how =How.XPATH, using = "/html/body/table[2]/tbody/tr[1]/td[1]")
	WebElement WelcomeText;

	@FindBy(how =How.XPATH, using = "/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")
	WebElement LogOut;

	public void lauchApplication(String url)throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.get(url);
	}

	public void login(String userName, String password) throws Exception {
		type(UserName, userName, "userName", this.page);
		type(Password, password, "password", this.page);
		clickElement(Login, "Login", this.page);
	}

	public void validateHomePage() {
		String actualText = WelcomeText.getText();
		Assert.assertEquals(actualText, "Welcome to AdactIn Group of Hotels");
	}
	
	public void LogOut() throws Exception {
		clickElement(LogOut, "LogOut", this.page);
	}

}