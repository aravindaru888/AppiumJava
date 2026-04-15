package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.appium.utils.WaitUtil;

public class BasePage {
    protected AppiumDriver driver;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElement(By locator) {
        logger.info("Finding element: " + locator);
        return WaitUtil.waitForElement(driver, locator);
    }

    protected void click(By locator) {
        logger.info("Clicking element: " + locator);
        WaitUtil.waitForElementToBeClickable(driver, locator).click();
    }

    protected void sendKeys(By locator, String text) {
        logger.info("Sending keys to element: " + locator + " with text: " + text);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        logger.info("Getting text from element: " + locator);
        return findElement(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            logger.error("Element not displayed: " + locator);
            return false;
        }
    }
}