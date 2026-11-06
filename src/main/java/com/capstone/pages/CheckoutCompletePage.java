package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

    private final WebDriver driver;

    private final By confirmationMessage =
            By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationMessage() {

        return driver.findElement(confirmationMessage)
                .getText();
    }
}