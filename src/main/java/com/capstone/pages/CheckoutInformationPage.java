package com.capstone.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage {

    private final WebDriver driver;

    //private final By firstNameField =By.id("first-name");
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    //private final By lastNameField =By.id("last-name");
    @FindBy(id = "last-name")
    private WebElement lastNameField;

    //private final By postalCodeField =By.id("postal-code");
    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    //private final By continueButton =By.id("continue");
    @FindBy(id = "continue")
    private WebElement continueButton;

    //private final By errorMessage =By.cssSelector("[data-test='error']");
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFirstName(String firstName) {
        new WebDriverWait(
            driver,
            Duration.ofSeconds(10))
            .until(
                    ExpectedConditions
                            .visibilityOf(
                                    firstNameField
                            )
            );

        // driver.findElement(firstNameField).sendKeys(firstName);
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {

        //driver.findElement(lastNameField).sendKeys(lastName);
        lastNameField.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {

        //driver.findElement(postalCodeField).sendKeys(postalCode);
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {

        //driver.findElement(continueButton).click();
        continueButton.click();
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

        //return driver.findElement(errorMessage).getText();
        return errorMessage.getText();
    }
}