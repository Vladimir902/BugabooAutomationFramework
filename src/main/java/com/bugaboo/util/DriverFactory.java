package com.bugaboo.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    // ThreadLocal to manage WebDriver instances for parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver createDriver(String browser, boolean headless, boolean enabledNotifications) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            if (enabledNotifications) {
                options.addArguments("--enable-notifications");
            } else {
                options.addArguments("--disable-notifications");
            }
            driver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            if (enabledNotifications) {
                // Edge supports notifications through Chrome options
                options.addArguments("--enable-notifications");
            } else {
                options.addArguments("--disable-notifications");
            }
            driver.set(new EdgeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            // Enable or disable notifications in Firefox
            if (enabledNotifications) {
                // Enabling notifications for Firefox
                options.addPreference("dom.webnotifications.enabled", true);
            } else {
                // Disabling notifications for Firefox
                options.addPreference("dom.webnotifications.enabled", false);
            }
            driver.set(new FirefoxDriver(options));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
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


