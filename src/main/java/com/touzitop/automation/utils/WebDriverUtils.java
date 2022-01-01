package com.touzitop.automation.utils;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtils {
    public static WebDriver driver;

    //因为openBrowser是静态方法，所以日志对象也需要定义为静态的
    //public static Logger logger = Logger.getLogger(WebDriverUtils.class);

    public static void openBrowser(String browserName) {

        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "target/drivers/chromedriver.exe");
            ChromeDriver chromedriver = new ChromeDriver();
            driver = chromedriver;
            //logger.info("开打【chrome】浏览器");

        } else if (browserName.equals("ie")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //取消IE安全设置(忽略IE的Protected Mode的设置)
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略浏览器缩放的设置
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver", "src/drivers/IEDriverServer.exe");
            InternetExplorerDriver ieDriver = new InternetExplorerDriver();
            driver = ieDriver;
            //logger.info("开打【ie】浏览器");

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.ie.driver", "src/drivers/geckodriver.exe");
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            driver = firefoxDriver;
            //logger.info("开打【firefox】浏览器");
        }

    }
}
