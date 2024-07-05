package com.open.hotel.pages.mobile.iosuikitcatalog;

import com.open.hotel.mobileutils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Views extends IOSActions {

    IOSDriver driver;

    public Views(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
    private WebElement textEntryMenu;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
    private WebElement confirmMenuItem;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement textBox;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement acceptPopUp;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
    private WebElement confirmMessage;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement submit;

    public void fillTextLabel(String name) {
        clickElement(textEntryMenu, "Text Entry", "UI KitCatalog Alert Controllers Page");
        type(textBox, name, "Text Box", "UI KitCatalog Alert Controllers Page");
        clickElement(acceptPopUp, "OK", "UI KitCatalog Alert Controllers Page");
    }

    public String getConfirmMessage() {
        clickElement(confirmMenuItem, "Confirm Message", "UI KitCatalog Alert Controllers Page");
        return confirmMessage.getText();
    }

}
