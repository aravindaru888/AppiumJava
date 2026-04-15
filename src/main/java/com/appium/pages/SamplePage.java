package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SamplePage extends BasePage {
    
    // Locators
    private By buttonElement = By.id("com.example.app:id/button_id");
    private By textField = By.id("com.example.app:id/text_input");
    
    public SamplePage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isButtonDisplayed() {
        return isElementDisplayed(buttonElement);
    }
    
    public void enterText(String text) {
        sendKeys(textField, text);
    }
}