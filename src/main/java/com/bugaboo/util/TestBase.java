package com.bugaboo.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class TestBase {

    protected WebDriver driver; // Make WebDriver protected so that subclasses can access it
    protected WebDriverWait wait; // To use in subclasses

    // Pass browser as a parameter from TestNG XML or method
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        // Initialize WebDriver based on the browser parameter
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.get("https://www.bugaboo.com/us-en");
        driver.manage().window().maximize();

        // Initialize WebDriverWait with a timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Handle cookie decline if it's visible
        WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        cookieDecline.click();
    }
}
