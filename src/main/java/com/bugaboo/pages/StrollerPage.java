package com.bugaboo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class StrollerPage {

    WebDriver driver;


    @FindBy(xpath = "//a[@aria-label='Bugaboo Fox 5']")
    private WebElement addItem;

    @FindBy(xpath = "//img[@alt='Desert Taupe sun canopy, Desert Taupe fabrics, black chassis']")
    private WebElement changeColor;



    //Constructor
    public StrollerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void goToItem() {
        // Wait for the wish item to be visible
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement visibleWishItem = wait.until(ExpectedConditions.visibilityOf(addItem));

        // Scroll into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", visibleWishItem);
        // Click the wish item
        try {
            visibleWishItem.click();
        } catch (ElementNotInteractableException e) {
            // If click fails, attempt to click via JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", visibleWishItem);
        }
    }




    public void setNewColor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait up to 10 seconds
        wait.until(ExpectedConditions.visibilityOf(changeColor)); // wait for visibility
        changeColor.click(); // perform the click action once visible
    }



}

