package com.capstone.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;

    public static ExtentReports getInstance() {

        if (extentReports == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(
                            "reports/ExtentReport.html"
                    );

            extentReports =
                    new ExtentReports();

            extentReports.attachReporter(
                    reporter
            );
        }

        return extentReports;
    }
}