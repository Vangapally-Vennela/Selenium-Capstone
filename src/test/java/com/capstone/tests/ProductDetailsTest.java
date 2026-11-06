package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.pages.ProductDetailsPage;
import com.capstone.pages.ProductsPage;
import com.capstone.utils.ProductData;
import com.capstone.utils.ProductInfo;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

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
            description = "Verify all expected products are displayed in product listing"
    )
    public void allProductsShouldExistInProductListing() {

        for (ProductInfo product : ProductData.PRODUCTS.values()) {

            Assert.assertTrue(
                    productsPage.isProductPresent(
                            product.getName()
                    ),
                    product.getName()
                            + " is not present in product listing."
            );
        }
    }

    @Test(
            priority = 2,
            groups = {"Smoke", "Regression"},
            description = "Verify correct product details page opens for selected product"
    )
    public void selectedProductShouldOpenCorrectProductDetailsPage() {

        ProductInfo expectedProduct =
                ProductData.PRODUCTS.get(
                        ProductData.BACKPACK
                );

        Assert.assertTrue(
                productsPage.isProductPresent(
                        expectedProduct.getName()
                ),
                "Product does not exist in listing page."
        );

        Assert.assertEquals(
                productsPage.getProductPrice(
                        expectedProduct.getName()
                ),
                expectedProduct.getPrice(),
                "Price mismatch on listing page."
        );

        Assert.assertTrue(
                productsPage.getProductDescription(
                        expectedProduct.getName()
                ).contains(
                        expectedProduct.getDescription()
                ),
                "Description mismatch on listing page."
        );

        productsPage.openProduct(
                expectedProduct.getName()
        );

        ProductDetailsPage detailsPage =
                new ProductDetailsPage(driver);

        Assert.assertEquals(
                detailsPage.getProductName(),
                expectedProduct.getName(),
                "Incorrect product opened."
        );

        Assert.assertEquals(
                detailsPage.getProductPrice(),
                expectedProduct.getPrice(),
                "Price mismatch on details page."
        );

        Assert.assertTrue(
                detailsPage.getProductDescription()
                        .contains(
                                expectedProduct.getDescription()
                        ),
                "Description mismatch on details page."
        );
    }

    @Test(
            priority = 3,
            groups = "Regression",
            description = "Verify product can be added to cart and user can navigate back to products page"
    )
    public void addProductToCartAndNavigateBackToProducts() {

        ProductInfo expectedProduct =
                ProductData.PRODUCTS.get(
                        ProductData.BACKPACK
                );

        productsPage.openProduct(
                expectedProduct.getName()
        );

        ProductDetailsPage detailsPage =
                new ProductDetailsPage(driver);

        detailsPage.addToCart();

        Assert.assertEquals(
                detailsPage.getCartItemCount(),
                ProductData.CART_COUNT,
                "Product was not added to cart."
        );

        detailsPage.clickBackToProducts();

        productsPage =
                new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                ProductData.CART_COUNT,
                "Cart count is incorrect."
        );

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE,
                "User was not redirected to Products page."
        );
    }
}