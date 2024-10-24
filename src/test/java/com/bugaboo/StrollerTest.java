package com.bugaboo;

import com.bugaboo.pages.StrollerPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


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
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorImage);

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

    @Test
    public void testText() {

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

        // Wait for the text to be present in the element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='pdp-header__intro pdp-header__grid-item pdp-header__intro--desktop']//h1[@class='pdp-header__title']")
        ));

        WebElement titleElement = driver.findElement(By.xpath("//div[@class='pdp-header__intro pdp-header__grid-item pdp-header__intro--desktop']//h1[@class='pdp-header__title']"));

        String expectedTitle = "Bugaboo Fox 5 bassinet and seat stroller";

        //Trim the empty spaces for better assertion
        String actualTitle = strollerPage.getProductTitle(titleElement).trim();

        Assert.assertEquals(actualTitle, expectedTitle, "The product title is not as expected.");

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        super.tearDown(result);
    }
}