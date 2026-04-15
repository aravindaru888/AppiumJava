package com.appium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

public class WaitUtil {
    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForElement(AppiumDriver driver, By locator) {
        return waitForElement(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElement(AppiumDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeClickable(AppiumDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeClickable(AppiumDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForElementToDisappear(AppiumDriver driver, By locator) {
        return waitForElementToDisappear(driver, locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementToDisappear(AppiumDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void implicitWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}