package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private final WebDriver driver;

    private final By itemTotal =
            By.cssSelector("[data-test='subtotal-label']");

    private final By taxAmount =
            By.cssSelector("[data-test='tax-label']");

    private final By totalAmount =
            By.cssSelector("[data-test='total-label']");

    private final By finishButton =
            By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemTotal() {

        return driver.findElement(itemTotal)
                .getText();
    }

    public String getTaxAmount() {

        return driver.findElement(taxAmount)
                .getText();
    }

    public String getTotalAmount() {

        return driver.findElement(totalAmount)
                .getText();
    }

    public void clickFinish() {

        driver.findElement(finishButton)
                .click();
    }
}
