package com.touzitop.automation.browsers;

import java.io.File;
import java.net.URL;

//import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class BrowserFactory implements DriverSource {
public class BrowserFactory{// implements DriverSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserFactory.class);

    //@Override
    public WebDriver newDriver() {
        try {

            BrowserFactory.LOGGER.info("working directory: {}", new File(".").getAbsolutePath());
            final String desiredBrowser = System.getProperty("browser");

            BrowserFactory.LOGGER.info("browser={}", desiredBrowser);

            WebDriver selectedDriver = null;

            switch (desiredBrowser) {
                case "ie":
                    selectedDriver = IeBrowser.configuredIeBrowser();
                    break;
                case "chrome":
                    selectedDriver = ChromeBrowser.configuredChromeBrowser();
                    break;
                case "firefox":
                    selectedDriver = FirefoxBrowser.configuredFirefoxBrowser();
                    break;
                case "grid":
                    final DesiredCapabilities capabilities = getCapabilities();
                    final String gridUrl = System.getProperty("gridUrl", "http;//alm-seleniumgrid.systems.uk.hsbc:9013/wd/hub/");
                    BrowserFactory.LOGGER.info("gridUrl={}", gridUrl);
                    System.out.println(gridUrl);
                    System.out.println(capabilities);
                    selectedDriver = new RemoteWebDriver(new URL(gridUrl), capabilities);
                    selectedDriver.manage().window().maximize();
                    break;
                default:
                    // This can never happen, browser is defaulted to chrome
                    // above
                    throw new IllegalStateException("browser is not defined");
            }
            return selectedDriver;
        } catch (final Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private DesiredCapabilities getCapabilities() {
        final String desiredCapabilities = System.getProperty("capabilities","chrome");
        final String desiredVersion = System.getProperty("capabilities.version","56");
        BrowserFactory.LOGGER.info("capabilities={}", desiredCapabilities);

        DesiredCapabilities capabilities = null;

        switch (desiredCapabilities) {
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                capabilities.setVersion(desiredVersion);
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
        }

        return capabilities;
    }

    //@Override
    public boolean takesScreenshots() {
        return true;
    }
}

