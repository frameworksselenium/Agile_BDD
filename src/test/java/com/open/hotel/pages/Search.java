package com.open.hotel.pages;

import com.open.hotel.Logger.LoggerClass;
import com.open.hotel.uiUtils.UIUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class Search   extends UIUtils {

	String testCaseName = null;
	String testCaseID = null;
	WebDriverWait wait = null;
	WebDriver driver = null;
	org.apache.log4j.Logger log = null;
	String pageName = "Hotel Search Page";

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
	@FindBy(how =How.NAME, using = "//*[contains(text(),'Select Hotel')]")
	WebElement SelectHotelText;

	public Search(WebDriver driver,String testCaseName, String testCaseID){
		super(driver, testCaseName, testCaseID);
		this.testCaseName = testCaseName;
		this.testCaseID = testCaseID;
		log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), testCaseID);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void enterRoomSearchInfo(HashMap<String, String> values) {
		type(Location, values.get("Location"), "Location", this.pageName);
		type(NoOfRoom, values.get("Number of Rooms"), "NoOfRoom", this.pageName);
		type(DatepickIn, values.get("Check In Date"), "DatepickIn", this.pageName);
		type(DatepickOut, values.get("Check Out Date"), "DatepickOut", this.pageName);
		type(AdultRoom, values.get("Adults per Room"), "AdultRoom", this.pageName);
		clickElement(Submit, "Submit", this.pageName);
	}
}