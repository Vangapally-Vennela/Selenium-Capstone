package com.capstone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capstone.utils.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

public class NavigationMenuPage {

    private final WebDriver driver;

    //private final By navigationMenuButton =By.id("react-burger-menu-btn");
    @FindBy(id = "react-burger-menu-btn")
    private WebElement navigationMenuButton;

    //private final By navigationMenuCloseButton =By.id("react-burger-cross-btn");
    @FindBy(id = "react-burger-cross-btn")
    private WebElement navigationMenuCloseButton;

    //private final By navigationAllItemsOption =By.id("inventory_sidebar_link");
    @FindBy(id = "inventory_sidebar_link")
    private WebElement navigationAllItemsOption;

    //private final By navigationAboutOption =By.id("about_sidebar_link");
    @FindBy(id = "about_sidebar_link")
    private WebElement navigationAboutOption;

    //private final By navigationLogoutOption =By.id("logout_sidebar_link");
    @FindBy(id = "logout_sidebar_link")
    private WebElement navigationLogoutOption;

    //private final By navigationResetAppStateOption =By.id("reset_sidebar_link");
    @FindBy(id = "reset_sidebar_link")
    private WebElement navigationResetAppStateOption;

    //private final By navigationMenuOptions =By.cssSelector(".bm-item");
    @FindBy(css = ".bm-item")
    private List<WebElement> navigationMenuOptions;

    public NavigationMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
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

        return navigationMenuOptions
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