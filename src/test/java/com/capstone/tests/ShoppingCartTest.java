package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.pages.ProductsPage;
import com.capstone.pages.ShoppingCartPage;
import com.capstone.utils.ProductData;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {

        LoginPage loginPage =
                new LoginPage(driver);

        productsPage =
                new ProductsPage(driver);

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );
    }

    @Test(
            priority = 1,
            groups = {"Smoke", "Regression"},
            description = "Verify a single product can be added to the shopping cart"
    )
    public void singleProductShouldBeAddedToShoppingCart() {

        String productName =
                ProductData.BACKPACK;

        productsPage.addProductToShoppingCart(
                productName
        );

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                ProductData.SINGLE_PRODUCT_COUNT
        );

        productsPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        Assert.assertTrue(
                shoppingCartPage
                        .isProductPresentInShoppingCart(
                                productName
                        )
        );
    }

    @Test(
            priority = 2,
            groups = {"Smoke", "Regression"},
            description = "Verify multiple products can be added to the shopping cart"
    )
    public void multipleProductsShouldBeAddedToShoppingCart() {

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.addProductToShoppingCart(
                ProductData.BIKE_LIGHT
        );

        productsPage.addProductToShoppingCart(
                ProductData.ONESIE
        );

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                ProductData.MULTIPLE_PRODUCT_COUNT
        );

        productsPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        Assert.assertTrue(
                shoppingCartPage
                        .isProductPresentInShoppingCart(
                                ProductData.BACKPACK
                        )
        );

        Assert.assertTrue(
                shoppingCartPage
                        .isProductPresentInShoppingCart(
                                ProductData.BIKE_LIGHT
                        )
        );

        Assert.assertTrue(
                shoppingCartPage
                        .isProductPresentInShoppingCart(
                                ProductData.ONESIE
                        )
        );
    }

    @Test(
            priority = 3,
            groups = "Regression",
            description = "Verify a product can be removed from the shopping cart"
    )
    public void productShouldBeRemovedFromShoppingCart() {

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        Assert.assertEquals(
                shoppingCartPage.getShoppingCartItemsCount(),
                1,
                "Cart should contain one product before removal."
        );

        shoppingCartPage.removeProductFromShoppingCart(
                ProductData.BACKPACK
        );

        Assert.assertEquals(
                shoppingCartPage.getShoppingCartItemsCount(),
                0,
                "Cart should be empty after product removal."
        );
    }

    @Test(
            priority = 4,
            groups = "Regression",
            description = "Verify correct product details are displayed in the shopping cart"
    )
    public void shoppingCartShouldDisplayCorrectProductDetails() {

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        Assert.assertTrue(
                shoppingCartPage
                        .isProductPresentInShoppingCart(
                                ProductData.BACKPACK
                        )
        );
    }

    @Test(
            priority = 5,
            groups = "Regression",
            description = "Verify Continue Shopping navigates back to the Products page"
    )
    public void continueShoppingButtonShouldNavigateToProductsPage() {

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        shoppingCartPage.clickContinueShopping();

        ProductsPage productsPage =
                new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE
        );
    }
}