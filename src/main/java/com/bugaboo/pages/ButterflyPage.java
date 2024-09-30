package com.bugaboo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ButterflyPage {

    WebDriver driver;

    @FindBy(id = "strollers")
    private WebElement strollersFind;

    @FindBy(className = "u-margin-bottom-4")
    private WebElement findButterflyPage;


    //Constructor
    public ButterflyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String findTheURL() {
        strollersFind.click();  // Click on the strollers link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("u-margin-bottom-4")));
        findButterflyPage.click();  // Click on the butterfly page link
        return driver.getCurrentUrl();  // Return the current URL
    }
}
