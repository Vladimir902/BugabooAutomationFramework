package com.bugaboo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void enterValuesIntoSearchBox(String value) {
        enterValue.sendKeys(value);
        enterValue.sendKeys(Keys.ENTER);
    }

    public void clickShopBundles() {
        bundlesClick.click();
    }



}

