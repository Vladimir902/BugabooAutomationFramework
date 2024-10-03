package com.bugaboo;

import com.bugaboo.pages.FailedTestPage;
import com.bugaboo.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bugaboo.util.RetryAnalyzer;




public class FailedTest extends TestBase {

    private FailedTestPage failedTestPage;

    @BeforeMethod
    public void setUp() {
        // Specify the path to the Chrome config file
        setUpConfig("config_edge.properties");

        // Initialize the HomePage object after the browser is set up
        failedTestPage = new FailedTestPage(driver);

    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void failedTest() {
        failedTestPage.locatingImage();
        WebElement element = driver.findElement(By.cssSelector("h2.u-hc"));
        String actualText = element.getText();
        String expectedText = "Bugaboo";
        Assert.assertEquals(actualText, expectedText, "Text does not match!");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Implement any cleanup logic if necessary
        super.tearDown(result);
    }
}
