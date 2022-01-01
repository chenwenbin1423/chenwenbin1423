package com.touzitop.automation.browsers;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

public class FirefoxBrowser extends FirefoxDriver {

    public static FirefoxDriver configuredFirefoxBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);

        FirefoxBrowser driver = new FirefoxBrowser(profile);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    private FirefoxBrowser(final FirefoxProfile profile) {
        //super(profile);
    }
}
