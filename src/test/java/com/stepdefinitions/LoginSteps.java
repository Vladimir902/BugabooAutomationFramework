package com.stepdefinitions;

import com.bugaboo.pages.LoginPage;
import com.bugaboo.util.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps extends TestBase {

    private WebDriver driver;
    private LoginPage loginPage;


    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        WebDriverManager.chromedriver().setup(); // Set up ChromeDriver
        driver = new ChromeDriver(); // Create a new instance of ChromeDriver
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.bugaboo.com/us-en"); // Navigate to the home page
        loginPage = new LoginPage(driver); // Initialize the LoginPage class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieAccept.click();
    }


    @Then("the user navigates to the login page")
    public void theUserNavigatesToTheLoginPage() {
        loginPage.navigateToLogin();
    }


    @When("user enters the credentials")
    public void userEntersTheCredentials() {
        WebElement element = driver.findElement(By.id("loginEmail"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        loginPage.addUsername(); // Pass the username to the LoginPage method
        loginPage.addPassword();
    }

    @And("clicks on Login")
    public void clicksOnLogin() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the accounts page")
    public void theUserShouldBeRedirectedToTheAccountsPage() {
        // Wait for the redirect after login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.bugaboo.com/us-en/accounts/?registration=false"));

        // Get the current URL
        String currentURL = driver.getCurrentUrl();

        // Expected URL
        String expectedURL = "https://www.bugaboo.com/us-en/accounts/?registration=false";

        // Assert that the current URL matches the expected URL
        Assert.assertEquals(currentURL, expectedURL, "The user was not redirected to the expected accounts page!");
    }
}

