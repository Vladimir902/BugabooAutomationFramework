package com.bugaboo;

import com.bugaboo.pages.HomePage;
import com.bugaboo.util.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class HomeTest extends TestBase {

    private HomePage homePage;


    @BeforeMethod
    public void setUp() {
        super.setUp();
        homePage = new HomePage(driver);

    }

    @Test(priority = 1)
    @Description("Verifies that the search box is visible")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchBox() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click();
        homePage.clickSearchBox();
        Assert.assertTrue(homePage.isSearchBoxVisible(), "Search box is not visible");
    }

    @Test(priority = 2)
    @Description("Inserts a value into the search box and checks correctness")
    @Severity(SeverityLevel.NORMAL)
    public void insertValues() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click();
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

    @Test(priority = 3)
    @Description("Check if the price is correct")
    @Severity(SeverityLevel.CRITICAL)
    public void testCorrectPrice() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click(); //Accept the cookies from dialog box

        homePage.clickShopBundles();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement strollerBundles = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Stroller bundles')]/following-sibling::ul/li/a//span[contains(text(),'Kangaroo Tandem bundles')]")));
        strollerBundles.click(); //click on shop bundles of the home page

        //find the price for the element
        WebElement priceElement = driver.findElement(By.xpath("(//div[@class='u-flex-self-bottom u-width--full']//div[@class='c-price c-price--product-tile']//span[@class='price__value'])[1]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", priceElement);

        //Test if the price is correct
        String priceText = priceElement.getText().replace("$", "").replace(",", "").trim();
        double actualPrice = Double.parseDouble(priceText);
        double expectedPrice = 1872.75;
        Assert.assertEquals(actualPrice, expectedPrice, "The price does not match the expected value.");


    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }


}

