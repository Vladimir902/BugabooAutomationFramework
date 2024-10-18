package com.bugaboo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;

    @FindBy(className = "header__right-search-icon")
    private WebElement searchBox;

    @FindBy(id = "navigation-search")
    private WebElement enterValue;

    @FindBy(id = "dynamicbundles")
    private WebElement bundlesClick;
    
    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSearchBox() {
        searchBox.click();
    }

    public boolean isSearchBoxVisible() {
        return searchBox.isDisplayed();
    }

    // Method to enter values into the search box dynamically
    public void enterValuesIntoSearchBox(String value) {
        enterValue.sendKeys(value);  // Enter the text
        enterValue.sendKeys(Keys.ENTER);  // Press Enter to submit the search
    }

    public void clickShopBundles() {
        bundlesClick.click();
    }



}

