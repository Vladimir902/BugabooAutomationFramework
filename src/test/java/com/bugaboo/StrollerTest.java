package com.bugaboo;

import com.bugaboo.pages.StrollerPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class StrollerTest extends TestBase {

    private StrollerPage strollerPage;


    @DataProvider(name = "strollerColors")
    public Object[][] getStrollerColors() {
        return new Object[][] {
                {"//img[@alt='Desert Taupe sun canopy, Desert Taupe fabrics, black chassis']"},
                {"//img[@alt='Black sun canopy, Black fabrics, black chassis']"},
                {"//img[@alt='Grey Melange sun canopy, Grey Melange fabrics, black chassis']"}
        };
    }

    @BeforeMethod
    public void setUp() {
        super.setUp();
        strollerPage = new StrollerPage(driver);
    }

    @Test(dataProvider = "strollerColors")
    public void colorStrollerTest (String colorXPath) {
        try {
            // Check if the cookie dialog box is present and clickable, then accept it
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
            if (cookieAccept.isDisplayed()) {
                cookieAccept.click();
            }
        } catch (TimeoutException | NoSuchElementException e) {
            // Handle case where the cookie dialog does not appear
            System.out.println("Cookie dialog did not appear, continuing with test.");
        }

        // Navigate to the stroller item
        strollerPage.goToItem();

        try {
            // Select the color option
            WebElement colorImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(colorXPath)));
            colorImage.click();

            // Attempt to interact with the element, and catch if it becomes stale
            try {
                // Check if the element becomes stale
                if (ExpectedConditions.stalenessOf(colorImage).apply(driver)) {
                    System.out.println("Element became stale, waiting for it to reappear...");
                    colorImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(colorXPath)));
                }
            } catch (StaleElementReferenceException e) {
                // If the element is stale, wait for it and re-fetch it
                System.out.println("StaleElementReferenceException caught, re-fetching element...");
                colorImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(colorXPath)));
            }

            // Assert that the color image is displayed
            Assert.assertTrue(colorImage.isDisplayed(), "The color image is not displayed.");

        } catch (NoSuchElementException | TimeoutException e) {
            // Handle any issues with finding or clicking the element
            Assert.fail("Failed to find or interact with the color image: " + e.getMessage());
        }
    }





    @AfterMethod
    public void tearDown(ITestResult result) {
        super.tearDown(result);
    }
}