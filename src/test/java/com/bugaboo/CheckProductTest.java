package com.bugaboo;

import com.bugaboo.pages.CheckProductPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bugaboo.listeners.RetryAnalyzer;

import java.time.Duration;


public class CheckProductTest extends TestBase {

    private CheckProductPage checkProductPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        checkProductPage = new CheckProductPage(driver);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void checkProductName() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click(); //Accept the cookies from dialog box

        checkProductPage.locatingImage();
        WebElement element = driver.findElement(By.cssSelector("h2.u-hc"));
        String actualText = element.getText(); //Locate the image, click on it and get text

        String expectedText = "Bugaboo";
        Assert.assertEquals(actualText, expectedText, "Texts do not match!");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        super.tearDown(result);
    }
}
