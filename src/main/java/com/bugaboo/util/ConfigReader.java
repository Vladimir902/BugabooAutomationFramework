
package com.bugaboo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    // Constructor that takes a config file path
    public ConfigReader(String configFilePath) {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("An error occurred");
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chrome"); // Default to chrome if not specified
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false")); // Default to false
    }


    public String getBaseURL() {
        return properties.getProperty("baseURL", "https://www.bugaboo.com/us-en"); // Default to Bugaboo URL
    }


    public boolean isEnabledNotifications() {
        return Boolean.parseBoolean(properties.getProperty("enabledNotifications", "false"));
    }

    public String getUsername() {
        return properties.getProperty("username", "default_username"); // Default username if not specified
    }

    public String getPassword() {
        return properties.getProperty("password", "default_password"); // Default password if not specified
    }


}

