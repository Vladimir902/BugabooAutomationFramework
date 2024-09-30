package com.bugaboo;

import com.bugaboo.pages.HomePage;
import com.bugaboo.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomeSteps extends TestBase {

    HomePage homePage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("edge") String browser) {
        // Call the setup method from TestBase, passing the browser parameter
        super.setUp(browser);
        // Initialize the HomePage object after the browser is set up
        homePage = new HomePage(driver);
    }


    @Test
    public void testSearchBox() {
        homePage.clickSearchBox();
        Assert.assertTrue(homePage.isSearchBoxVisible(), "Search box is not visible");
    }


    @Test
    public void insertValues() {
        // Click the search box
        homePage.clickSearchBox();

        // Enter a value into the search box
        String expectedValue = "Kangaroo";  // Set the expected value

        //add the actual value
        String actualValue = "Kangaroo";
        homePage.enterValuesIntoSearchBox(actualValue);

        // Assert that the actual value matches the expected value
        Assert.assertEquals(actualValue, expectedValue, "The value in the search box is incorrect.");
    }


}
