package com.touzitop.automation.browsers;

import com.touzitop.automation.config.TestConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ChromeBrowser extends ChromeDriver {

    public static ChromeDriver configuredChromeBrowser() throws Throwable {
        System.setProperty("webdriver.chrome.driver", TestConfig.valueFor("WebDriverChromeDriverPath"));
        ChromeDriver browser = new ChromeBrowser();
        browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        return browser;
    }

    private ChromeBrowser() {
        super();
    }
}
