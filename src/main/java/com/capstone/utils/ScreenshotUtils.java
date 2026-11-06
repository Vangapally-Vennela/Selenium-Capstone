package com.capstone.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenshotUtils {

    public static String takeScreenshot(
            WebDriver driver,
            String testName) {

        File screenshotFolder =
                new File("screenshots");

        if (!screenshotFolder.exists()) {

            screenshotFolder.mkdirs();

            System.out.println(
                    "Created screenshots folder: "
                            + screenshotFolder.getAbsolutePath()
            );
        }

        String fileName =
                testName + "_"
                        + LocalDateTime.now()
                        .toString()
                        .replace(":", "-")
                        .replace(".", "-")
                        + ".png";

        String filePath =
                screenshotFolder.getAbsolutePath()
                        + File.separator
                        + fileName;

        System.out.println("Taking screenshot...");

        if (driver == null) {

            throw new RuntimeException(
                    "WebDriver is null. Cannot capture screenshot."
            );
        }

        try {

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            File destinationFile =
                    new File(filePath);

            FileUtils.copyFile(
                    sourceFile,
                    destinationFile
            );

            System.out.println(
                    "Screenshot saved successfully: "
                            + destinationFile.getAbsolutePath()
            );

            return destinationFile.getAbsolutePath();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save screenshot",
                    e
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to capture screenshot",
                    e
            );
        }
    }
}