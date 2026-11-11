package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    private final WebDriver driver;

    //private final By itemTotal =By.cssSelector("[data-test='subtotal-label']");
    @FindBy(css = "[data-test='subtotal-label']")
    private WebElement itemTotal;

    //private final By taxAmount =By.cssSelector("[data-test='tax-label']");
    @FindBy(css = "[data-test='tax-label']")
    private WebElement taxAmount;

    //private final By totalAmount =By.cssSelector("[data-test='total-label']");
    @FindBy(css = "[data-test='total-label']")
    private WebElement totalAmount;

    //private final By finishButton =By.id("finish");
    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getItemTotal() {

        //return driver.findElement(itemTotal).getText();
        return itemTotal.getText();
    }

    public String getTaxAmount() {

        // return driver.findElement(taxAmount).getText();
        return taxAmount.getText();
    }

    public String getTotalAmount() {

        //return driver.findElement(totalAmount).getText();
        return totalAmount.getText();
    }

    public void clickFinish() {

        //driver.findElement(finishButton).click();
        finishButton.click();
    }
}
