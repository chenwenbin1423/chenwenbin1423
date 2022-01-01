package com.touzitop.automation.page;

import com.touzitop.automation.utils.WebDriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    //private String URL = "http://passport.isoftstone.com/";
    private String URL = null;

    //private Logger logger = Logger.getLogger(WebDriverUtils.class);

    public void init() {
        System.setProperty("webdriver.chrome.driver","target/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.bin","C:/'Program Files'/Google/Chrome/Application/chrome.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    protected void init(String url) {
        System.setProperty("webdriver.chrome.driver","target/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.bin","C:/'Program Files'/Google/Chrome/Application/chrome.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        setPageUrl(url);
        driver.get(URL);
    }

    public String getPageUrl() {
        return URL;
    }

    public void setPageUrl(String url) {
        URL = url;
    }

    public void quit() {
        driver.quit();
    }

    // get all attributes in an element
    public Object getAllAttributes(WebElement ele){
        Object attrs = ((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", ele);
        return attrs;
    }


    public WebElement findElement(By by) {
        WebElement element = waitElementVisible(by);
        return element;
    }

    //等待元素可见
    public WebElement waitElementVisible(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webElement;
    }

    //等待元素可点击
    public WebElement waitElementClickable(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
    }

    //点击元素
    public void click(By by) {
        waitElementClickable(by).click();
        //TODO 预留，比如打印日志
        //logger.info("点击【" + by + "】元素");
    }

    //点击元素
    public void click(WebElement element) {

        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        //TODO 预留，比如打印日志
        //logger.info("点击【" + element + "】元素");
    }

    //输入数据
    public void type(By by, String inputData) {
        waitElementVisible(by).sendKeys(inputData);
        //TODO 预留，比如打印日志
        //logger.info("给元素【" + by + "】输入数据【" + "】");
    }

    public void action(){
        Actions action = new Actions(driver);
        //TODO
    }


}
