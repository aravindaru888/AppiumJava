package com.appium.tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.appium.base.BaseTest;
import com.appium.pages.SamplePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

public class SampleTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SampleTest.class);
    private SamplePage samplePage;

    @BeforeClass
    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
        samplePage = new SamplePage(driver);
    }

    @Test(priority = 1)
    public void testAppLaunch() {
        logger.info("Test 1: Verifying app launch");
        String appPackage = ((AndroidDriver) driver).getCurrentPackage();
        logger.info("Current package: " + appPackage);
        Assert.assertNotNull(appPackage, "App failed to launch");
    }

    @Test(priority = 2 ,enabled = false)
    public void testElementPresence() {
        logger.info("Test 2: Verifying element presence");
        Assert.assertTrue(samplePage.isButtonDisplayed(), "Element is not displayed");
        logger.info("Element found and displayed");
    }

    @Test(priority = 3 , enabled=false)
    public void testTextInput() {
        logger.info("Test 3: Testing text input");
        samplePage.enterText("Test Input");
        logger.info("Text input successful");
    }
}