package com.capstone.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement waitForClickable(
            WebDriver driver,
            By locator) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement waitForVisible(
            WebDriver driver,
            By locator) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static void waitForUrlContains(
            WebDriver driver,
            String text) {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(
                ExpectedConditions.urlContains(text)
        );
    }
    public static String getText(
        WebDriver driver,
        By locator) {

    return new WebDriverWait(
            driver,
            Duration.ofSeconds(10)
    ).until(driver1 ->
            driver1.findElement(locator).getText()
    );
}
}