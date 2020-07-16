package com.open.hotel.pages;

import com.open.qa.web.uiUtils.UiUtils;
import com.open.qa.web.webDriverFactory.ManagerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Search   extends UiUtils {
	WebDriverWait wait = null;
	
	public By lst_Location = By.name("location");
	public By lst_room_nos = By.name("room_nos");
	public By edt_datepick_in = By.name("datepick_in");
	public By edt_datepick_out = By.name("datepick_out");
	public By lst_adult_room = By.name("adult_room");
	public By ele_Submit = By.name("Submit");
	public By ele_continue = By.name("continue");
	public By ele_WelcomeText = By.xpath("/html/body/table[2]/tbody/tr[1]/td[1]");

	public void clickOnSearch() throws Exception {
		SafeAction(ele_Submit, "","ele_Submit");
	}
	
	public void enterRoomSearchInfo()
			throws Exception {
		
		Thread.sleep(500);
		SafeAction(lst_Location, "Sydney","lst_Location"); 
		Thread.sleep(500);
		//SafeAction(lst_room_nos, "2 - Two","lst_room_nos"); 
		//Thread.sleep(500);
		//SafeAction(edt_datepick_in, "","edt_datepick_in"); 
		//Thread.sleep(500);
		//SafeAction(edt_datepick_out, "","edt_datepick_out"); 
		//Thread.sleep(500);		
		//SafeAction(lst_adult_room, "2 - Two","lst_adult_room"); 
		//Thread.sleep(500);
	}

	public void clickSearchOnSearchPage() throws Exception {
		SafeAction(ele_WelcomeText, "","ele_WelcomeText");
		SafeAction(ele_Submit, "","ele_Submit");
	}

	public void validateHotelRoomSearch() {

		Assert.assertEquals(ManagerDriver.getInstance().getWebDriver().findElement(ele_WelcomeText).getText(), "Welcome to AdactIn Group of Hotels");
	}
}