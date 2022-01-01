package com.touzitop.automation.browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.touzitop.automation.config.TestConfig;

public class IeBrowser extends InternetExplorerDriver {

    public static IeBrowser configuredIeBrowser() throws Throwable {
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.id.driver", TestConfig.valueFor("WebDriverIeDriverPath"));
        IeBrowser browser = new IeBrowser(caps);
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        return browser;
    }

    private IeBrowser(final DesiredCapabilities capabilities) {
        super(capabilities);
    }
}
