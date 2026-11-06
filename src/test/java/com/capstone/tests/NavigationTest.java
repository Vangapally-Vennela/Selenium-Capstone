package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.pages.NavigationMenuPage;
import com.capstone.pages.ProductsPage;
import com.capstone.utils.ProductData;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class NavigationTest extends BaseTest {

    private NavigationMenuPage navigationMenuPage;
    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void navigationTestSetup() {

        LoginPage loginPage =
                new LoginPage(driver);

        navigationMenuPage =
                new NavigationMenuPage(driver);

        productsPage =
                new ProductsPage(driver);

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );
    }

    @Test(
            groups = {"Smoke", "Regression"},
            description = "Verify navigation menu opens successfully"
    )
    public void navigationMenuShouldOpenSuccessfully() {

        navigationMenuPage.openNavigationMenu();

        Assert.assertTrue(
                navigationMenuPage
                        .isNavigationMenuDisplayed(),
                "Navigation menu should be displayed."
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify navigation menu displays expected options"
    )
    public void navigationMenuShouldDisplayExpectedOptions() {

        navigationMenuPage.openNavigationMenu();

        List<String> actualOptions =
                navigationMenuPage
                        .getNavigationMenuOptions();

        List<String> expectedOptions =
                Arrays.asList(
                        TestData.NAVIGATION_MENU_OPTIONS
                );

        Assert.assertEquals(
                actualOptions,
                expectedOptions,
                "Navigation menu options mismatch. "
                        + actualOptions
        );
    }

    @Test(
            groups = {"Smoke", "Regression"},
            description = "Verify All Items option navigates to Products page"
    )
    public void navigationAllItemsOptionShouldNavigateToProductsPage() {

        productsPage.openProduct(
                ProductData.BACKPACK
        );

        navigationMenuPage.openNavigationMenu();

        navigationMenuPage.navigateToAllItems();

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify About option redirects to Sauce Labs website"
    )
    public void navigationAboutOptionShouldNavigateToSauceLabsWebsite() {

        navigationMenuPage.openNavigationMenu();

        navigationMenuPage.navigateToAboutPage();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("saucelabs.com"),
                "User should be redirected to Sauce Labs website."
        );
    }

    @Test(
            groups = {"Smoke", "Regression"},
            description = "Verify Logout option redirects to Login page"
    )
    public void navigationLogoutOptionShouldNavigateToLoginPage() {

        navigationMenuPage.openNavigationMenu();

        navigationMenuPage
                .logoutFromApplicationUsingNavigationMenu();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("saucedemo.com"),
                "User should be redirected to login page."
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify Reset App State clears shopping cart"
    )
    public void navigationResetAppStateShouldClearShoppingCart() {

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                1
        );

        navigationMenuPage.openNavigationMenu();

        navigationMenuPage
                .resetApplicationStateUsingNavigationMenu();

        driver.navigate().refresh();

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                0,
                "Shopping cart should be cleared."
        );
    }
}