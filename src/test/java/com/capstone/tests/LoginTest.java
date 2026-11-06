package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.pages.NavigationMenuPage;
import com.capstone.pages.ProductsPage;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private NavigationMenuPage navigationMenuPage;

    @BeforeMethod(alwaysRun = true)
    public void initializePageObjects() {

        loginPage = new LoginPage(driver);

        productsPage = new ProductsPage(driver);

        navigationMenuPage = new NavigationMenuPage(driver);
    }
    

    @Test(groups = {"Smoke", "Regression"})
    public void validLoginShouldNavigateToProductsPage() {

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE,
                "User should land on Products page after successful login."
        );
    }

    @Test(groups = "Regression")
    public void invalidPasswordShouldDisplayErrorMessage() {

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.INVALID_PASSWORD
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(TestData.INVALID_LOGIN_ERROR),
                "Expected invalid credentials error message."
        );
    }

    @Test(groups = "Regression")
    public void emptyUsernameAndPasswordShouldDisplayValidationMessage() {

        loginPage.login("", "");

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(TestData.USERNAME_REQUIRED_ERROR),
                "Expected username required validation message."
        );
    }

    @Test(groups = "Regression")
    public void emptyUsernameShouldDisplayValidationMessage() {

        loginPage.login(
                "",
                TestData.VALID_PASSWORD
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(TestData.USERNAME_REQUIRED_ERROR),
                "Expected username required validation message."
        );
    }

    @Test(groups = "Regression")
    public void emptyPasswordShouldDisplayValidationMessage() {

        loginPage.login(
                TestData.STANDARD_USER,
                ""
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(TestData.PASSWORD_REQUIRED_ERROR),
                "Expected password required validation message."
        );
    }

    @Test(groups = "Regression")
    public void lockedOutUserShouldNotBeAbleToLogin() {

        loginPage.login(
                TestData.LOCKED_OUT_USER,
                TestData.VALID_PASSWORD
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(TestData.LOCKED_USER_ERROR),
                "Expected locked-out user validation message."
        );
    }

    @Test(groups = "Regression")
    public void logoutAndBrowserBackShouldNotAllowAccess() {

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );

        Assert.assertEquals(
                productsPage.getPageTitle(),
                TestData.PRODUCTS_PAGE_TITLE
        );

        navigationMenuPage.openNavigationMenu();

        navigationMenuPage
                .logoutFromApplicationUsingNavigationMenu();

        driver.navigate().back();

        Assert.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "User should remain logged out after browser back navigation."
        );
    }
}