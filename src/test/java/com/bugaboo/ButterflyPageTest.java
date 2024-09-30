package com.bugaboo;

import com.bugaboo.pages.ButterflyPage;
import com.bugaboo.pages.HomePage;
import com.bugaboo.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ButterflyPageTest extends TestBase {

    ButterflyPage butterflyPage;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean headless) {
        // Call the setup method from TestBase, passing the browser and headless parameters
        super.setUp(browser, headless);
        // Initialize the HomePage object after the browser is set up
        butterflyPage = new ButterflyPage(driver);
    }
    @Test
    public void TestCorrectURL() {

    }


}
