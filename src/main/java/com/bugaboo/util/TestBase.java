package com.bugaboo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    private ConfigReader configReader;

    // Add a parameter for config file path
    protected void setUpConfig(String configFilePath) {
        // Initialize ConfigReader with the specified config file
        configReader = new ConfigReader(configFilePath);

        // Get browser and headless values from the config file
        String browser = configReader.getBrowser();
        boolean headless = configReader.isHeadless();
        int implicitWait = configReader.getImplicitWait();
        int explicitWait = configReader.getImplicitWait();
        boolean disableNotifications = configReader.disableNotifications();

        // Initialize WebDriver using DriverFactory with browser and headless values
        driver = DriverFactory.createDriver(browser, headless, disableNotifications);
        driver.manage().window().maximize();

        // Set implicit wait from config file
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        // Initialize WebDriverWait with explicit wait from config file
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        // Navigate to the base URL from config file
        driver.get(configReader.getBaseURL());

        log.info("Browser launched and navigated to: " + configReader.getBaseURL());

        // Optionally handle browser-specific features like dismissing cookie notifications
        try {
            WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
            cookieDecline.click();
        } catch (Exception e) {
            log.warn("Cookie consent dialog not found or could not be clicked");
        }
    }


    /*@Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(String browser, boolean headless) { */

    @BeforeMethod
    public void setUp() {
        // Placeholder for actual setup implementation
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
            log.error("Screenshot was not saved");
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
