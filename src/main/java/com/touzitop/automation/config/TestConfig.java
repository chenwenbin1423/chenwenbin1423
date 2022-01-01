package com.touzitop.automation.config;

import com.touzitop.automation.utils.CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class TestConfig {
    private static final String CONFIG_DIR = "target/test-classes/config/";
    private static TestConfig testConfig;
    private static String requiredEnvironmentName;
    private static Properties properties;

    private static TestConfig getInstance() throws Exception {
        if (testConfig == null) {
            requiredEnvironmentName = System.getProperty("env", "local");
            properties = new Properties();
            populateCommonDetails();
            populateEnvPropertyDetails(requiredEnvironmentName);

            for (final Map.Entry entry : System.getProperties().entrySet()) {
                CommonMethods.logInfo("", "System prop key: " + entry.getKey().toString() + " has value: " + entry.getValue().toString());
            }
            for (final Map.Entry entry : properties.entrySet()) {
                CommonMethods.logInfo("", "Prop key: " + entry.getKey().toString() + " has value: " + entry.getValue().toString());
            }

            testConfig = new TestConfig();
        }
        return testConfig;
    }

    public static String valueFor(final String keyName) throws Exception {
        return getInstance().getProperty(keyName);
    }

    private static void populateCommonDetails() throws Exception {
        final String commonPropertiesFilePath = CONFIG_DIR + "common.properties";
        loadPropertiesFromFile(commonPropertiesFilePath);
    }

    private static void populateEnvPropertyDetails(final String requiredEnvironment) throws Exception {
        final String propertiesFilePath = CONFIG_DIR + String.format("%s.properties", requiredEnvironment);
        loadPropertiesFromFile(propertiesFilePath);
    }

    private static void loadPropertiesFromFile(final String filePath) throws Exception {
        final File propertiesFile = new File(filePath);
        if (!propertiesFile.exists()) {
            throw new FileNotFoundException(String.format("No properties file found at: %s", filePath));
        }
        final InputStream input = new FileInputStream(filePath);
        properties.load(input);
        input.close();
    }

    private String getProperty(final String keyName) {
        final String value = properties.getProperty(keyName);
        if (value == null) {
            throw new IllegalArgumentException(String.format("Key %s not configured for environment %s", keyName, requiredEnvironmentName));
        }
        return value;
    }
}
