package com.bugaboo;

import com.bugaboo.pages.FailedTestPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class FailedTest extends TestBase {

    FailedTestPage failedTestPage;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean headless) {
        // Call the setup method from TestBase, passing the browser and headless parameters
        super.setUp(browser, headless);
        // Initialize the HomePage object after the browser is set up
        failedTestPage = new FailedTestPage(driver);
    }

    @Test
    public void failedTest() {
        failedTestPage.locatingImage();
        WebElement element = driver.findElement(By.cssSelector("h2.u-hc"));
        String actualText = element.getText();
        String expectedText = "Bugaboo";
        Assert.assertEquals(actualText, expectedText, "Text does not match!");

    }

}
