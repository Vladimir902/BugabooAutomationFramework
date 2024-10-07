package com.bugaboo;

import com.bugaboo.pages.WishlistPage;
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


public class WishListTest extends TestBase {

    private WishlistPage wishlistPage;

    @BeforeMethod
    public void setUp() {
        // Specify the path to the Chrome config file
        setUpConfig("config_headless.properties");

        // Initialize the HomePage object after the browser is set up
        wishlistPage = new WishlistPage(driver);

    }


    @Test
    public void wishListTesting() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieDecline = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        cookieDecline.click();
        wishlistPage.checkWishlist();
        wishlistPage.wishlistAdd();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait up to 10 seconds

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast__message")));

        String messageText = toastMessage.getText();

        System.out.println("Toast message: " + messageText);
        String expectedMessage = "The product has been added to your wishlist.";
        Assert.assertEquals(messageText, expectedMessage, "The text doesn't match.");

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}


