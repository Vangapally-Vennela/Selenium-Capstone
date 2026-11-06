package com.capstone.pages;

import com.capstone.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private final WebDriver driver;

    private final By productName =
            By.cssSelector("[data-test='inventory-item-name']");

    private final By productPrice =
            By.cssSelector("[data-test='inventory-item-price']");

    private final By productDescription =
            By.cssSelector("[data-test='inventory-item-desc']");

    private final By addToCartButton =
            By.cssSelector("[data-test^='add-to-cart']");

    private final By removeButton =
            By.cssSelector("[data-test^='remove']");

    private final By backToProductsButton =
            By.id("back-to-products");

    private final By cartBadge =
            By.cssSelector("[data-test='shopping-cart-badge']");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {

    return WaitUtils.getText(
            driver,
            productName
    );
}

    public String getProductPrice() {

    return WaitUtils.getText(
            driver,
            productPrice
    );
}

   public String getProductDescription() {

    return WaitUtils.getText(
            driver,
            productDescription
    );
}

    public void addToCart() {

        WaitUtils.waitForClickable(
                driver,
                addToCartButton
        ).click();

        WaitUtils.waitForVisible(
                driver,
                removeButton
        );

        WaitUtils.waitForVisible(
                driver,
                cartBadge
        );
    }

    public int getCartItemCount() {

        return Integer.parseInt(
                WaitUtils.waitForVisible(
                        driver,
                        cartBadge
                ).getText()
        );
    }

    public void clickBackToProducts() {

        WaitUtils.waitForClickable(
                driver,
                backToProductsButton
        ).click();
    }
}