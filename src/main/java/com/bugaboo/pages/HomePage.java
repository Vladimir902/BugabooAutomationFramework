package com.bugaboo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(className = "header__right-search-icon")
    private WebElement searchBox;

    @FindBy(id = "navigation-search")
    private WebElement enterValue;

    @FindBy(xpath = "(//div[@class='u-margin-top-32 u-button-group u-button-group--left']//a[@class='c-button--secondary button--icon button--transform'])[1]")
    private WebElement shopBundles;
    
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
        shopBundles.click();
    }


}

