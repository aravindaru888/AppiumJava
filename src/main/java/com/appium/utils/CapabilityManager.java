package com.appium.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CapabilityManager {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String platformName = properties.getProperty("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("deviceName", "emulator-5554"));
        capabilities.setCapability(MobileCapabilityType.APP, properties.getProperty("appPath", "src/main/resources/apk/ApiDemos-debug.apk"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("automationName", "UiAutomator2"));
        capabilities.setCapability("appPackage", properties.getProperty("appPackage", "com.example.app"));
        capabilities.setCapability("appActivity", properties.getProperty("appActivity", ".MainActivity"));
        capabilities.setCapability("noReset", Boolean.parseBoolean(properties.getProperty("noReset", "false")));
        return capabilities;
    }

    public static String getAppiumUrl() {
        return properties.getProperty("appiumServerUrl", "http://127.0.0.1:4723");
    }
}