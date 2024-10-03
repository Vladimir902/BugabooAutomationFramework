package com.bugaboo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class WishlistPage {

    WebDriver driver;


    @FindBy(xpath = "//a[@aria-label='Bugaboo Fox 5']")
    private WebElement wishItem;


    //Constructor
    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void checkWishlist() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wishItem);
        wishItem.click();

    }


}
