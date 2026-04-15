package com.appium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class AppiumServerManager {
    private static final Logger logger = LogManager.getLogger(AppiumServerManager.class);

    public static boolean isServerRunning(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            //System.out.println(AppiumDriver.getStatus());
            logger.info("Appium server is running on " + host + ":" + port);
            return true;
        } catch (IOException e) {
            logger.warn("Appium server is not running on " + host + ":" + port);
            return false;
        }
    }

    public static void startServer() {
        if (isServerRunning("127.0.0.1", 4723)) {
            logger.info("Server already running");
            return;
        }

        try {
            logger.info("Starting Appium server...");
            Runtime.getRuntime().exec("appium");
            Thread.sleep(3000);
            logger.info("Appium server started");
        } catch (IOException | InterruptedException e) {
            logger.error("Failed to start Appium server: " + e.getMessage());
        }
    }

    public static void stopServer() {
        try {
            logger.info("Stopping Appium server...");
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("taskkill /IM node.exe /F");
            } else {
                Runtime.getRuntime().exec("pkill -f appium");
            }
            logger.info("Appium server stopped");
        } catch (IOException e) {
            logger.error("Failed to stop Appium server: " + e.getMessage());
        }
    }
}