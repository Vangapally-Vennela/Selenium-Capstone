package com.capstone.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage {

    private final WebDriver driver;

    private final By firstNameField =
            By.id("first-name");

    private final By lastNameField =
            By.id("last-name");

    private final By postalCodeField =
            By.id("postal-code");

    private final By continueButton =
            By.id("continue");

    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        new WebDriverWait(
            driver,
            Duration.ofSeconds(10))
            .until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    firstNameField
                            )
            );

        driver.findElement(firstNameField)
                .sendKeys(firstName);
    }

    public void enterLastName(String lastName) {

        driver.findElement(lastNameField)
                .sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {

        driver.findElement(postalCodeField)
                .sendKeys(postalCode);
    }

    public void clickContinue() {

        driver.findElement(continueButton)
                .click();
    }

    public void enterCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    public String getValidationMessage() {

        return driver.findElement(errorMessage)
                .getText();
    }
}