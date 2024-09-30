package com.bugaboo;

import com.bugaboo.pages.HomePage;
import com.bugaboo.util.DriverFactory;
import com.bugaboo.util.TestBase;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class HomeSteps extends TestBase {

    HomePage homePage;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("edge") String browser, @Optional("false") boolean headless) {
        // Call the setup method from TestBase, passing the browser and headless parameters
        super.setUp(browser, headless);
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


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName(); // Get the test name
            takeScreenshot(testName); // Take a screenshot with the test name as the filename
        }
        DriverFactory.quitDriver();
    }


}
