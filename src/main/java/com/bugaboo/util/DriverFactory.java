package com.bugaboo.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // ThreadLocal to manage WebDriver instances for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Method to initialize WebDriver based on the browser and headless option
    public static WebDriver createDriver(String browser, boolean headless, boolean disableNotifications) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver.set(new EdgeDriver(options));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver.get();
    }

    // Method to get the current WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Method to close and clean up the WebDriver instance
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}


