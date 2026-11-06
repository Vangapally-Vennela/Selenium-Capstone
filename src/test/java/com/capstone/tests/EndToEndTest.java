package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.CheckoutCompletePage;
import com.capstone.pages.CheckoutInformationPage;
import com.capstone.pages.CheckoutOverviewPage;
import com.capstone.pages.LoginPage;
import com.capstone.pages.ProductsPage;
import com.capstone.pages.ShoppingCartPage;
import com.capstone.utils.ProductData;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {

    @Test(
            groups = {"Smoke", "Regression"},
            description = "Verify customer can complete purchase from login until order confirmation"
    )
    public void completePurchaseJourneyShouldDisplayOrderConfirmation() {

        // Login
        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );

        ProductsPage productsPage =
                new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE,
                "Products page should be displayed after login."
        );

        // Add Products
        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.addProductToShoppingCart(
                ProductData.BIKE_LIGHT
        );

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                2,
                "Cart badge should display two items."
        );

        // Navigate To Cart
        productsPage.navigateToShoppingCart();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("cart"),
                "User should be navigated to Cart page."
        );

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        Assert.assertTrue(
                shoppingCartPage.isProductPresentInShoppingCart(
                        ProductData.BACKPACK
                ),
                "Backpack should be present in cart."
        );

        Assert.assertTrue(
                shoppingCartPage.isProductPresentInShoppingCart(
                        ProductData.BIKE_LIGHT
                ),
                "Bike Light should be present in cart."
        );

        // Checkout
        shoppingCartPage.proceedToCheckout();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("checkout-step-one"),
                "Checkout Information page should be displayed."
        );

        // Customer Information
        CheckoutInformationPage checkoutInformationPage =
                new CheckoutInformationPage(driver);

        checkoutInformationPage.enterCheckoutInformation(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("checkout-step-two"),
                "Checkout Overview page should be displayed."
        );

        // Checkout Overview
        CheckoutOverviewPage checkoutOverviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertTrue(
                checkoutOverviewPage.getItemTotal()
                        .contains("Item total"),
                "Item total should be displayed."
        );

        Assert.assertTrue(
                checkoutOverviewPage.getTotalAmount()
                        .contains("Total"),
                "Total amount should be displayed."
        );

        // Finish Checkout
        checkoutOverviewPage.clickFinish();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("checkout-complete"),
                "Checkout Complete page should be displayed."
        );

        // Confirmation
        CheckoutCompletePage checkoutCompletePage =
                new CheckoutCompletePage(driver);

        Assert.assertEquals(
                checkoutCompletePage.getConfirmationMessage(),
                TestData.ORDER_SUCCESS_MESSAGE,
                "Order confirmation message mismatch."
        );
    }
}