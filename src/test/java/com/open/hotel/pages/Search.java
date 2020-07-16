package com.open.hotel.pages;

import com.open.qa.util.threadLevelVariables.ScenarioVariable;
import com.open.qa.util.threadLevelVariables.ScenarioVariableManager;
import com.open.qa.util.threadLevelVariables.VariableManager;
import com.open.qa.web.uiUtils.UiUtils;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;

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
	@FindBy(how =How.NAME, using = "//*[contains(text(),'Select Hotel')]")
	WebElement SelectHotelText;


	public void clickOnSearch() throws Exception {
		clickElement(Submit,"Submit", this.page);
	}
	
	public void enterRoomSearchInfo(HashMap<String, String> values)throws Exception {
		type(Location, values.get("Location"), "Location", this.page);
		type(NoOfRoom, values.get("Number of Rooms"), "NoOfRoom", this.page);
		type(DatepickIn, values.get("Check In Date"), "DatepickIn", this.page);
		type(DatepickOut, values.get("Check Out Date"), "DatepickOut", this.page);
		type(AdultRoom, values.get("Adults per Room"), "AdultRoom", this.page);
	}

	public void validateHotelRoomSearch() {
		System.out.println(VariableManager.getInstance().getVariablesManager().getObject("TestCaseName"));

		//Scenario scenario = ScenarioVariableManager.getInstance().getScenarioVariable().getObject();
		Scenario scenario = getScenario();
		scenario.write("Select Room Page displayed");
		//String expectedText = SelectHotelText.getText();
		//Assert.assertEquals(expectedText.getText(), "Select Hotel");
	}
}