package com.capstone.base;

import com.capstone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        ChromeOptions options =
                new ChromeOptions();

        if (ConfigReader.getBoolean("headless")) {

            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        ConfigReader.getInt("implicitWait")
                )
        );

        driver.get(
                ConfigReader.get("url")
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
