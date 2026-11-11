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

    private static WebDriverWait getWait(
            WebDriver driver) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")
                )
        );
    }

    public static WebElement waitForClickable(WebDriver driver,By locator) {

        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitForClickable(WebDriver driver,WebElement element) {

        return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisible(WebDriver driver,By locator) {

        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForVisible(WebDriver driver,WebElement element) {

        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForUrlContains(
            WebDriver driver,
            String text) {

        getWait(driver)
                .until(
                        ExpectedConditions
                                .urlContains(text)
                );
    }

//     public static String getText(
//             WebDriver driver,
//             By locator) {

//         return getWait(driver)
//                 .until(
//                         d -> d.findElement(locator)
//                                 .getText()
//                 );
//     }
        public static String getText(WebDriver driver,WebElement element) {

        return getWait(driver).until(ExpectedConditions.visibilityOf(element)).getText();

        }
}