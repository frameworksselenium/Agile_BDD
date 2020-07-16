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

public class Search   extends UiUtils {
	public WebDriver driver = null;

	String page = "Search";
	public Search(){
		driver = ManagerDriver.getInstance().getWebDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.NAME, using = "location")
	WebElement Location;
	@FindBy(how =How.NAME, using = "room_nos")
	WebElement NoOfRoom;
	@FindBy(how =How.NAME, using = "datepick_in")
	WebElement DatepickIn;
	@FindBy(how =How.NAME, using = "datepick_out")
	WebElement DatepickOut;
	@FindBy(how =How.NAME, using = "adult_room")
	WebElement AdultRoom;
	@FindBy(how =How.NAME, using = "Submit")
	WebElement Submit;
	@FindBy(how =How.NAME, using = "/html/body/table[2]/tbody/tr[1]/td[1]")
	WebElement WelcomeText;


	public void clickOnSearch() throws Exception {
		clickElement(Submit,"Submit", this.page);
	}
	
	public void enterRoomSearchInfo()throws Exception {
		type(Location, "", "Location", this.page);
		type(NoOfRoom, "", "NoOfRoom", this.page);
		type(DatepickIn, "", "NoOfRoom", this.page);
		type(DatepickOut, "", "NoOfRoom", this.page);
		type(AdultRoom, "", "NoOfRoom", this.page);
	}

	public void validateHotelRoomSearch() {

		Assert.assertEquals(WelcomeText.getText(), "Welcome to AdactIn Group of Hotels");
	}
}