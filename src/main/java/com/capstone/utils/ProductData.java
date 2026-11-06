package com.capstone.utils;

import java.util.Map;

public final class ProductData {

    private ProductData() {
    }

    public static final String BACKPACK = "Sauce Labs Backpack";

    public static final int CART_COUNT = 1;

    public static final Map<String, ProductInfo> PRODUCTS =
            Map.of(

                    "Sauce Labs Backpack",
                    new ProductInfo(
                            "Sauce Labs Backpack",
                            "$29.99",
                            "carry.allTheThings()"
                    ),

                    "Sauce Labs Bike Light",
                    new ProductInfo(
                            "Sauce Labs Bike Light",
                            "$9.99",
                            "A red light isn't the desired state"
                    ),

                    "Sauce Labs Bolt T-Shirt",
                    new ProductInfo(
                            "Sauce Labs Bolt T-Shirt",
                            "$15.99",
                            "Get your testing superhero on"
                    ),

                    "Sauce Labs Fleece Jacket",
                    new ProductInfo(
                            "Sauce Labs Fleece Jacket",
                            "$49.99",
                            "It's not every day that you come across"
                    ),

                    "Sauce Labs Onesie",
                    new ProductInfo(
                            "Sauce Labs Onesie",
                            "$7.99",
                            "Rib snap infant onesie"
                    ),

                    "Test.allTheThings() T-Shirt (Red)",
                    new ProductInfo(
                            "Test.allTheThings() T-Shirt (Red)",
                            "$15.99",
                            "This classic Sauce Labs t-shirt"
                    )
            );
public static final String BIKE_LIGHT =
        "Sauce Labs Bike Light";

public static final String ONESIE =
        "Sauce Labs Onesie";

public static final int SINGLE_PRODUCT_COUNT = 1;

public static final int MULTIPLE_PRODUCT_COUNT = 3;
}