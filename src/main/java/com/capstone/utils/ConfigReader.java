package com.capstone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public final class ConfigReader {

    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream inputStream =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(CONFIG_FILE_NAME)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        CONFIG_FILE_NAME + " was not found on the classpath"
                );
            }

            PROPERTIES.load(inputStream);

        } catch (IOException e) {

            throw new UncheckedIOException(
                    "Unable to read " + CONFIG_FILE_NAME,
                    e
            );
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {

        String value = System.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            value = PROPERTIES.getProperty(key);
        }

        if (value == null || value.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Missing configuration value for key: "
                            + key
            );
        }

        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}