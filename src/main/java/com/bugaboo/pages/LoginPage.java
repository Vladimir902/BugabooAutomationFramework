package com.bugaboo.pages;

import com.bugaboo.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    WebDriver driver;


    @FindBy(xpath = "//ul[@class='usp-right__list']//li[contains(@class, 'usp__list-item')]//a[contains(@class, 'usp__list-icon-link')]")
    WebElement loginPage;

    @FindBy(id = "loginEmail")
    WebElement userName;

    @FindBy(id = "loginPassword")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@class='c-button--primary button--icon button--transform button--block login-form__submit']")
    WebElement login;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLogin() {
        loginPage.click();
    }

    public void addUsername() {
        ConfigReader configReader = new ConfigReader("config_chrome.properties");
        String username = configReader.getUsername(); // Fetching username from config.properties
        userName.sendKeys(username); // Sending the fetched username to the input field
    }

    public void addPassword() {
        ConfigReader configReader = new ConfigReader("config_chrome.properties");
        String password = configReader.getPassword(); // Fetching password from config.properties
        passwordInput.sendKeys(password); // Sending the fetched password to the input field
    }

    public void clickLogin() {
        login.click();
    }


}
