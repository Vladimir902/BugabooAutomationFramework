package com.bugaboo;

import com.bugaboo.pages.WishlistPage;
import com.bugaboo.util.TestBase;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
        wishlistPage.checkWishlist();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}


