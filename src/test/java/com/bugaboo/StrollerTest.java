package com.bugaboo;

import com.bugaboo.pages.StrollerPage;
import com.bugaboo.pages.StrollerPage;
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

import java.time.Duration;


public class StrollerTest extends TestBase {

    private StrollerPage strollerPage;

    @BeforeMethod
    public void setUp() {
        // Specify the path to the Chrome config file
        super.setUp();

        // Initialize the HomePage object after the browser is set up
        strollerPage = new StrollerPage(driver);

    }


    @Test
    public void wishListTesting() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click();

        strollerPage.goToItem();
        strollerPage.setNewColor();
        WebElement colorImage = driver.findElement(By.xpath("//img[@alt='Desert Taupe sun canopy, Desert Taupe fabrics, black chassis']"));
        Assert.assertTrue(colorImage.isDisplayed(), "The color image is not displayed.");

    }





    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}