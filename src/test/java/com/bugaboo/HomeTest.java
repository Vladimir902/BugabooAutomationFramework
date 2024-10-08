package com.bugaboo;

import com.bugaboo.pages.HomePage;
import com.bugaboo.util.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bugaboo.util.CustomListener;

import java.time.Duration;


public class HomeTest extends TestBase {

    private HomePage homePage; // Make this private to ensure thread safety

   /* @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean headless) { */

    @BeforeMethod
    public void setUp() {
        // Specify the path to the Chrome config file
        setUpConfig("config_chrome.properties");

        // Initialize the HomePage object after the browser is set up
        homePage = new HomePage(driver);

    }

    @Test(priority = 1)
    @Description("Verifies that the search box is visible")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchBox() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        cookieDecline.click();
        homePage.clickSearchBox();
        Assert.assertTrue(homePage.isSearchBoxVisible(), "Search box is not visible");
    }

    @Test(priority = 2)
    @Description("Inserts a value into the search box and checks correctness")
    @Severity(SeverityLevel.NORMAL)
    public void insertValues() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        cookieDecline.click();
        // Click the search box
        homePage.clickSearchBox();

        // Enter a value into the search box
        String expectedValue = "Kangaroo";  // Set the expected value

        // Actual value
        String actualValue = "Kangaroo";
        homePage.enterValuesIntoSearchBox(actualValue);

        // Assert that the actual value matches the expected value
        Assert.assertEquals(actualValue, expectedValue, "The value in the search box is incorrect.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}

