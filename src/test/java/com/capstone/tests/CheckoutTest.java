package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.*;
import com.capstone.utils.ProductData;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void setupCheckoutPrecondition() {

        LoginPage loginPage =
                new LoginPage(driver);

        productsPage =
                new ProductsPage(driver);

        loginPage.login(
                TestData.STANDARD_USER,
                TestData.VALID_PASSWORD
        );

        productsPage.addProductToShoppingCart(
                ProductData.BACKPACK
        );

        productsPage.navigateToShoppingCart();
    }

    private CheckoutInformationPage
    navigateToCheckoutInformationPage() {

        ShoppingCartPage shoppingCartPage =
                new ShoppingCartPage(driver);

        shoppingCartPage.proceedToCheckout();

        return new CheckoutInformationPage(driver);
    }

    private CheckoutOverviewPage
    navigateToCheckoutOverviewPage() {

        CheckoutInformationPage informationPage =
                navigateToCheckoutInformationPage();

        informationPage.enterCheckoutInformation(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );

        return new CheckoutOverviewPage(driver);
    }

    @Test(
            groups = {"Smoke", "Regression"},
            description = "Verify customer can successfully complete checkout"
    )
    public void completeCheckoutFlowShouldDisplayOrderConfirmation() {

        CheckoutOverviewPage overviewPage =
                navigateToCheckoutOverviewPage();

        overviewPage.clickFinish();

        CheckoutCompletePage completePage =
                new CheckoutCompletePage(driver);

        Assert.assertEquals(
                completePage.getConfirmationMessage(),
                TestData.ORDER_SUCCESS_MESSAGE
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify validation message when checkout form is submitted empty"
    )
    public void emptyCheckoutFormShouldDisplayFirstNameRequiredValidation() {

        CheckoutInformationPage informationPage =
                navigateToCheckoutInformationPage();

        informationPage.clickContinue();

        Assert.assertTrue(
                informationPage
                        .getValidationMessage()
                        .contains(
                                TestData.FIRST_NAME_REQUIRED
                        )
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify validation message when first name is missing"
    )
    public void missingFirstNameShouldDisplayValidationMessage() {

        CheckoutInformationPage informationPage =
                navigateToCheckoutInformationPage();

        informationPage.enterCheckoutInformation(
                "",
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );

        Assert.assertTrue(
                informationPage
                        .getValidationMessage()
                        .contains(
                                TestData.FIRST_NAME_REQUIRED
                        )
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify validation message when last name is missing"
    )
    public void missingLastNameShouldDisplayValidationMessage() {

        CheckoutInformationPage informationPage =
                navigateToCheckoutInformationPage();

        informationPage.enterCheckoutInformation(
                TestData.FIRST_NAME,
                "",
                TestData.POSTAL_CODE
        );

        Assert.assertTrue(
                informationPage
                        .getValidationMessage()
                        .contains(
                                TestData.LAST_NAME_REQUIRED
                        )
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify validation message when postal code is missing"
    )
    public void missingPostalCodeShouldDisplayValidationMessage() {

        CheckoutInformationPage informationPage =
                navigateToCheckoutInformationPage();

        informationPage.enterCheckoutInformation(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                ""
        );

        Assert.assertTrue(
                informationPage
                        .getValidationMessage()
                        .contains(
                                TestData.POSTAL_CODE_REQUIRED
                        )
        );
    }

    @Test(
            groups = "Regression",
            description = "Verify checkout overview displays order summary"
    )
    public void checkoutOverviewShouldDisplayOrderSummary() {

        CheckoutOverviewPage overviewPage =
                navigateToCheckoutOverviewPage();

        Assert.assertTrue(
                overviewPage.getItemTotal()
                        .contains("Item total")
        );

        Assert.assertTrue(
                overviewPage.getTaxAmount()
                        .contains("Tax")
        );

        Assert.assertTrue(
                overviewPage.getTotalAmount()
                        .contains("Total")
        );
    }
}