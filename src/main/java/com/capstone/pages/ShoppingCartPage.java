package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //private final By shoppingCartItems =By.cssSelector("[data-test='inventory-item']");
    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElement> shoppingCartItems;

    //private final By shoppingCartProductNames =By.cssSelector("[data-test='inventory-item-name']");
    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> shoppingCartProductNames;

    //private final By continueShoppingButton =By.id("continue-shopping");
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    //private final By checkoutButton =By.id("checkout");
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
        PageFactory.initElements( driver,this);
    }

    public boolean isProductPresentInShoppingCart(
            String productName) {

        return driver.findElements(
                By.xpath(
                        "//div[@data-test='inventory-item-name' and text()='"
                                + productName + "']")
        ).size() > 0;
    }

    public void removeProductFromShoppingCart(
            String productName) {

        By productLocator =
                By.xpath(
                        "//div[@data-test='inventory-item-name' and text()='"
                                + productName + "']"
                );

        By removeButtonLocator =
                By.xpath(
                        "//div[@data-test='inventory-item-name' and text()='"
                                + productName
                                + "']/ancestor::*[@data-test='inventory-item']//button"
                );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButtonLocator
                )
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        productLocator
                )
        );
    }

    public int getShoppingCartItemsCount() {

        //return driver.findElements(shoppingCartItems).size();
        return shoppingCartItems.size();
    }

    public List<String> getShoppingCartProductNames() {

        // if (driver.findElements(shoppingCartProductNames).isEmpty()) {

        //     return List.of();
        // }
        
        if (shoppingCartProductNames.isEmpty()) {

            return List.of();
        }

        // return driver.findElements(
        //                 shoppingCartProductNames)
        //         .stream()
        //         .map(WebElement::getText)
        //         .collect(Collectors.toList());
        return shoppingCartProductNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickContinueShopping() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueShoppingButton
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "inventory"
                )
        );
    }

    public void clickCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        ).click();
    }

    public void proceedToCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-one"
                )
        );
    }

    public boolean isShoppingCartPageDisplayed() {

        return driver.getCurrentUrl()
                .contains("cart");
    }
}