package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capstone.utils.WaitUtils;

public class CheckoutCompletePage {

    private final WebDriver driver;

    //private final By confirmationMessage =By.cssSelector("[data-test='complete-header']");
    @FindBy(css = "[data-test='complete-header']")
    private WebElement confirmationMessage;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    
    public boolean isPageDisplayed() {

        return WaitUtils.waitForVisible(
                driver,
                confirmationMessage
        ).isDisplayed();
    }

    public String getConfirmationMessage() {

        //return driver.findElement(confirmationMessage).getText();
         return WaitUtils.waitForVisible(
                driver,
                confirmationMessage
        ).getText();
    }
}