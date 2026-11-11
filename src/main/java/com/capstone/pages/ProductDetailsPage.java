package com.capstone.pages;

import com.capstone.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    private final WebDriver driver;

    //private final By productName =By.cssSelector("[data-test='inventory-item-name']");
    @FindBy(css = "[data-test='inventory-item-name']")
    private WebElement productName;

    //private final By productPrice =By.cssSelector("[data-test='inventory-item-price']");
    @FindBy(css = "[data-test='inventory-item-price']")
    private WebElement productPrice;

    //private final By productDescription =By.cssSelector("[data-test='inventory-item-desc']");
    @FindBy(css = "[data-test='inventory-item-desc']")
    private WebElement productDescription;

    //private final By addToCartButton =By.cssSelector("[data-test^='add-to-cart']");
    @FindBy(css = "[data-test^='add-to-cart']")
    private WebElement addToCartButton;

    //private final By removeButton =By.cssSelector("[data-test^='remove']");
    @FindBy(css = "[data-test^='remove']")
    private WebElement removeButton;

    //private final By backToProductsButton =By.id("back-to-products");
    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    //private final By cartBadge =By.cssSelector("[data-test='shopping-cart-badge']");
    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement cartBadge;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
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