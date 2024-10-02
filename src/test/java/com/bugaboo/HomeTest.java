package com.bugaboo;

import com.bugaboo.pages.HomePage;
import com.bugaboo.util.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class HomeTest extends TestBase {

    private HomePage homePage; // Make this private to ensure thread safety

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("edge") String browser, @Optional("false") boolean headless) {
        // Call the setup method from TestBase, passing the browser and headless parameters
        super.setUp(browser, headless);
        // Initialize the HomePage object after the browser is set up
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    @Description("Verifies that the search box is visible")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchBox() {
        homePage.clickSearchBox();
        Assert.assertTrue(homePage.isSearchBoxVisible(), "Search box is not visible");
    }

    @Test(priority = 2)
    @Description("Inserts a value into the search box and checks correctness")
    @Severity(SeverityLevel.NORMAL)
    public void insertValues() {
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

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}

