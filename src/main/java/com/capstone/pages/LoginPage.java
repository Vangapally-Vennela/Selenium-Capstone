package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    // Locators
//     private final By usernameField = By.id("user-name");

    @FindBy(id = "user-name")
    private WebElement usernameField;

//     private final By passwordField = By.id("password");
    @FindBy(id = "password")
    private WebElement passwordField;

    //private final By loginButton = By.id("login-button");

    @FindBy(id = "login-button")
    private WebElement loginButton;

    //private final By errorMessage = By.cssSelector("[data-test='error']");

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterUsername(String username) {

        // driver.findElement(usernameField)
        //         .clear();

        // driver.findElement(usernameField)
        //         .sendKeys(username);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {

        // driver.findElement(passwordField).clear();
        // driver.findElement(passwordField).sendKeys(password);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {

        // driver.findElement(loginButton).click();
        loginButton.click();
    }

    public void login(String username,String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {

        // return driver.findElement(errorMessage).getText();
        return errorMessage.getText();
    }

    public boolean isLoginButtonDisplayed() {

        //return driver.findElement(loginButton).isDisplayed();
        return loginButton.isDisplayed();
    }
}