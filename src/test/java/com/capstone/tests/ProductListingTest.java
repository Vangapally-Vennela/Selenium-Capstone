package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.pages.ProductsPage;
import com.capstone.utils.TestData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListingTest extends BaseTest {

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
            description = "Verify the expected number of products are displayed"
    )
    public void productCountShouldMatchExpectedCount() {

        Assert.assertEquals(
                productsPage.getProductCount(),
                TestData.PRODUCT_COUNT,
                "Incorrect product count displayed."
        );
    }

    @Test(
            priority = 2,
            groups = {"Smoke", "Regression"},
            description = "Verify products can be sorted alphabetically from A to Z"
    )
    public void productsShouldSortByNameAscending() {

        productsPage.selectSortOption(
                TestData.SORT_AZ
        );

        List<String> actualNames =
                productsPage.getProductNames();

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        Collections.sort(expectedNames);

        Assert.assertEquals(
                actualNames,
                expectedNames,
                "Products are not sorted in ascending order."
        );
    }

    @Test(
            priority = 3,
            groups = "Regression",
            description = "Verify products can be sorted alphabetically from Z to A"
    )
    public void productsShouldSortByNameDescending() {

        productsPage.selectSortOption(
                TestData.SORT_ZA
        );

        List<String> actualNames =
                productsPage.getProductNames();

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        expectedNames.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actualNames,
                expectedNames,
                "Products are not sorted in descending order."
        );
    }

    @Test(
            priority = 4,
            groups = "Regression",
            description = "Verify products can be sorted by price from low to high"
    )
    public void productsShouldSortByPriceLowToHigh() {

        productsPage.selectSortOption(
                TestData.SORT_LOW_HIGH
        );

        List<Double> actualPrices =
                productsPage.getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products are not sorted from low to high."
        );
    }

    @Test(
            priority = 5,
            groups = "Regression",
            description = "Verify products can be sorted by price from high to low"
    )
    public void productsShouldSortByPriceHighToLow() {

        productsPage.selectSortOption(
                TestData.SORT_HIGH_LOW
        );

        List<Double> actualPrices =
                productsPage.getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        expectedPrices.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products are not sorted from high to low."
        );
    }
}