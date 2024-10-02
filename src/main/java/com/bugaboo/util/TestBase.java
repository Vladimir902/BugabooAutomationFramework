package com.bugaboo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(String browser, boolean headless) {
        // Initialize WebDriver using DriverFactory, passing the headless option
        driver = DriverFactory.createDriver(browser, headless);
        driver.manage().window().maximize();
        driver.get("https://www.bugaboo.com/us-en");  // Example URL, adjust based on your project

        // Initialize WebDriverWait with a timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Handle cookie decline if it's visible
        WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        cookieDecline.click();
    }


    public void takeScreenshot(String testName) {
        // Create timestamp for unique file naming
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        // Capture the screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Specify the screenshots directory
        File screenshotDir = new File("C:\\Users\\vmalakovski\\IdeaProjects\\TestingFramework\\screenshots");

        // Ensure the screenshots directory exists, create it if necessary
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs(); // Create directories if they do not exist
        }

        // Define the full path for the screenshot
        File destinationFile = new File(screenshotDir, fileName);

        try {
            // Save the screenshot to the specified directory
            FileHandler.copy(screenshotFile, destinationFile);
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Optional: Tear down method to quit the driver after each test
    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getName(); // Get the test name

        // Take screenshot if the test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(testName + "_FAILED");
        }

        // Quit the driver after the test
        DriverFactory.quitDriver();
    }

}
