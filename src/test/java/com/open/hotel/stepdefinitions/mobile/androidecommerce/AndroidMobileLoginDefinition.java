package com.open.hotel.stepdefinitions.mobile.androidecommerce;

import com.open.hotel.mobileutils.AppiumUtils;
import com.open.hotel.pages.mobile.androidecommerce.CartPage;
import com.open.hotel.pages.mobile.androidecommerce.FormPage;
import com.open.hotel.pages.mobile.androidecommerce.ProductCatalogue;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AndroidMobileLoginDefinition {

	@When("Android Mobile - Login eCommerce App and Add Items to Cart")
	public void Android_Mobile_Login() throws Exception {
		AndroidDriver driver = (AndroidDriver) VariableManager.getInstance().getVariables().getVar("driver");
		AppiumUtils appiumUtils = new AppiumUtils();
		List<HashMap<String, String>> data = appiumUtils.getJsonData(System.getProperty("user.dir") + "//src//test//resources//data//MobileCommerce.json");
		HashMap<String, String> input = data.get(0);

		FormPage formPage = new FormPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		//Thread.sleep(2000);
		//	WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

		Thread.sleep(6000);
		Set<String> contexts =driver.getContextHandles();
		for(String contextName :contexts)
		{
			System.out.println(contextName);
		}

		driver.context("WEBVIEW_com.androidsample.generalstore");//chrome driver
		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}

}