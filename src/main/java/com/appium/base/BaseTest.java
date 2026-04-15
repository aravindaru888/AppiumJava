package com.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.appium.utils.CapabilityManager;
import com.appium.utils.AppiumServerManager;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected AppiumDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() throws MalformedURLException {
        logger.info("Setting up Appium driver...");
        AppiumServerManager.startServer();
        
        DesiredCapabilities capabilities = CapabilityManager.getCapabilities();
        String appiumUrl = CapabilityManager.getAppiumUrl();
        
        driver = new AndroidDriver(new URL(appiumUrl), capabilities);
        logger.info("Appium driver initialized successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing Appium driver...");
            driver.quit();
            logger.info("Appium driver closed");
        }
    }

    protected AppiumDriver getDriver() {
        return driver;
    }
}