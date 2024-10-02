package com.bugaboo;

import com.bugaboo.pages.FailedTestPage;
import com.bugaboo.pages.WishlistPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class WishListTest extends TestBase {

    WishlistPage wishlistPage;

    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean headless) {
        // Call the setup method from TestBase, passing the browser and headless parameters
        super.setUp(browser, headless);
        // Initialize the HomePage object after the browser is set up
        wishlistPage = new WishlistPage(driver);
    }


    @Test
    public void wishListTesting() {
        wishlistPage.checkWishlist();
    }
}
