package com.open.hotel.pages.mobile.iosuikitcatalog;

import com.open.hotel.mobileutils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends IOSActions {

    IOSDriver driver;

    public HomePage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]")
    private WebElement UIView;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Views' AND name == 'Views' AND value == 'Views'")
    private WebElement views;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text View'`]")
    private WebElement alertViews;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Alert Controllers'`]")
    private WebElement alertControllers;

    public void UIView() {
        clickElement(UIView, "UIView", "UI KitCatalog Start Page");
    }

    public void Views() {
        clickElement(views, "Views", "UI KitCatalog Home Page");
    }

    public void alertControllers() {
        clickElement(alertControllers, "Alert Controllers", "UI KitCatalog Start Page");
    }
}
