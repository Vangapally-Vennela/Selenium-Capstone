package com.capstone.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.capstone.utils.ExtentManager;
import com.capstone.utils.ScreenshotUtils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private final ExtentReports extentReports =
            ExtentManager.getInstance();

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        extentTest.set(
                extentReports.createTest(
                        result.getMethod().getMethodName()
                )
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(
                result.getThrowable()
        );

        try {

            Object testInstance =
                    result.getInstance();

            WebDriver driver =
                    (WebDriver)
                            testInstance.getClass()
                                    .getMethod("getDriver")
                                    .invoke(testInstance);

            String screenshotPath =
                    ScreenshotUtils.takeScreenshot(
                            driver,
                            result.getMethod()
                                    .getMethodName()
                    );

            extentTest.get().info(
                    "Failed URL: "
                            + driver.getCurrentUrl()
            );

            extentTest.get()
                    .addScreenCaptureFromPath(
                            screenshotPath
                    );

        } catch (Exception exception) {

            exception.printStackTrace();

            extentTest.get().warning(
                    "Unable to capture screenshot: "
                            + exception.getMessage()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip(
                "Test Skipped"
        );
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();
    }
}