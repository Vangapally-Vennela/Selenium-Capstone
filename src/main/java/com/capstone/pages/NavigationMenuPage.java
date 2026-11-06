package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.capstone.utils.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

public class NavigationMenuPage {

    private final WebDriver driver;

    private final By navigationMenuButton =
            By.id("react-burger-menu-btn");

    private final By navigationMenuCloseButton =
            By.id("react-burger-cross-btn");

    private final By navigationAllItemsOption =
            By.id("inventory_sidebar_link");

    private final By navigationAboutOption =
            By.id("about_sidebar_link");

    private final By navigationLogoutOption =
            By.id("logout_sidebar_link");

    private final By navigationResetAppStateOption =
            By.id("reset_sidebar_link");

    private final By navigationMenuOptions =
            By.cssSelector(".bm-item");

    public NavigationMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openNavigationMenu() {

        WaitUtils.waitForClickable(
                driver,
                navigationMenuButton
        ).click();

        WaitUtils.waitForVisible(
                driver,
                navigationAllItemsOption
        );
    }

    public void closeNavigationMenu() {

        WaitUtils.waitForClickable(
                driver,
                navigationMenuCloseButton
        ).click();
    }

    public boolean isNavigationMenuDisplayed() {

        return WaitUtils.waitForVisible(
                driver,
                navigationAllItemsOption
        ).isDisplayed();
    }

    public List<String> getNavigationMenuOptions() {

        WaitUtils.waitForVisible(
                driver,
                navigationAllItemsOption
        );

        return driver.findElements(
                        navigationMenuOptions)
                .stream()
                .map(option -> option.getText().trim())
                .collect(Collectors.toList());
    }

    public void navigateToAllItems() {

        WaitUtils.waitForClickable(
                driver,
                navigationAllItemsOption
        ).click();
    }

    public void navigateToAboutPage() {

        WaitUtils.waitForClickable(
                driver,
                navigationAboutOption
        ).click();
    }

    public void logoutFromApplicationUsingNavigationMenu() {

        WaitUtils.waitForClickable(
                driver,
                navigationLogoutOption
        ).click();
    }

    public void resetApplicationStateUsingNavigationMenu() {

        WaitUtils.waitForClickable(
                driver,
                navigationResetAppStateOption
        ).click();
    }
}