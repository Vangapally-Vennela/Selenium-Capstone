package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capstone.utils.WaitUtils;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    private final WebDriver driver;

    // Page Elements
    //private final By pageTitle = By.className("title");
    @FindBy(className = "title")
    private WebElement pageTitle;

    //private final By productNames = By.cssSelector("[data-test='inventory-item-name']");
    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> productNames;

    //private final By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    @FindBy(css = "[data-test='inventory-item-price']")
    private List<WebElement> productPrices;

    //private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    //private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    @FindBy(css = "[data-test='shopping-cart-badge']")
    private List<WebElement> cartBadge;
    
    //private final By shoppingCartLink = By.className("shopping_cart_link");
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    private By getAddToCartButtonLocator(String productName) {

    String productId = productName
            .toLowerCase()
            .replace(" ", "-")
            .replace("(", "")
            .replace(")", "");

    return By.id("add-to-cart-" + productId);
}

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // Products page title
    public String getPageTitle() {

        //return driver.findElement(pageTitle).getText();
        return WaitUtils.waitForVisible(driver, pageTitle).getText();
    }

    // Product Count
    public int getProductCount() {

        //return driver.findElements(productNames).size();
        return productNames.size();
    }

    // Sort Products
    public void selectSortOption(String visibleText) {

        // Select select = new Select(driver.findElement(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(visibleText);
    }

    // Get all product names
    public List<String> getProductNames() {

        return productNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Get all product prices
    public List<Double> getProductPrices() {

        return productPrices
                .stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    // Verify a product exists
    public boolean isProductPresent(String productName) {

        return driver.findElements(By.xpath("//div[@data-test='inventory-item-name' and text()='"
+ productName + "']")
        ).size() > 0;
    }

    // Open specific product
    public void openProduct(String productName) {

    WaitUtils.waitForClickable(
            driver,
            By.xpath(
                    "//div[@data-test='inventory-item-name' and text()='"
                            + productName + "']")
    ).click();
}

    // Get product price from listing page
    public String getProductPrice(String productName) {

        return driver.findElement(
                By.xpath(
                        "//div[@data-test='inventory-item-name' and text()='"
                                + productName +
                                "']/ancestor::*[@data-test='inventory-item']" +
                                "//div[@data-test='inventory-item-price']")
        ).getText();
    }

    // Get product description from listing page
    public String getProductDescription(String productName) {

        return driver.findElement(
                By.xpath(
                        "//div[@data-test='inventory-item-name' and text()='"
                                + productName +
                                "']/ancestor::*[@data-test='inventory-item']" +
                                "//div[@data-test='inventory-item-desc']")
        ).getText();
    }

    // Cart item count
    public int getCartItemCount() {

        // if (driver.findElements(cartBadge).isEmpty()) {
        //     return 0;
        // }

        // return Integer.parseInt(driver.findElement(cartBadge).getText());
         if (cartBadge.isEmpty()) {
        return 0;
    }

    return Integer.parseInt(
            cartBadge.get(0)
                    .getText()
    );
    }

    public void addProductToShoppingCart(String productName) {

    driver.findElement(
            getAddToCartButtonLocator(productName)
    ).click();
}
public void removeProductFromProductsPage(
        String productName) {

    driver.findElement(
            By.xpath(
                    "//div[@data-test='inventory-item-name' and text()='"
                            + productName +
                            "']/ancestor::*[@data-test='inventory-item']//button")
    ).click();
}
public void navigateToShoppingCart() {

    shoppingCartLink.click();

    new WebDriverWait(driver,Duration.ofSeconds(10)).
        until(ExpectedConditions.urlContains("cart.html")
    );
}
}