package com.bugaboo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckProductPage {

    WebDriver driver;

    @FindBy(xpath = "//img[@alt='Bugaboo Fox 5 stroller']")
    private WebElement locateImage;

    //Constructor
    public CheckProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void locatingImage() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", locateImage );
        locateImage.click();
    }

}
