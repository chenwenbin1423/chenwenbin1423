package com.touzitop.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LoginPage extends BasePage {

    private String url = "https://www.touzitop.com/login";

    @FindBy(xpath = "//li[text()='短信登录']")
    public WebElement SMSLoginBtn;

    @FindBy(xpath = "//*[@id='sms_mobile']")
    public WebElement SMSMobileInput;

    @FindBy(id = "code")
    public WebElement codeInput;

    @FindBy(xpath = "//li[text()='账号登录']")
    public WebElement accountLoginBtn;

    @FindBy(id = "mobile")
    public WebElement mobileInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(css = "i")
    public WebElement rememberChkBox;

    @FindBy(css = ".forget_password")
    public WebElement forgetPasswordLink;

    @FindBy(id = "loginForm")
    public WebElement loginFormButton;

    @FindBy(css = "div[class='layui-tab-item'] .bottom_height a")
    public WebElement RegisterLink;

    //创建构造函数，并且调用PageFactory.initElements()方法来初始化元素，即，将元素映射到我们定义好的变量
    public LoginPage() throws InterruptedException {
        //super.init();
       // Thread.sleep(2000);
        PageFactory.initElements(driver, this);
    }

    public void switchToAccountLogin() {
        click(accountLoginBtn);
    }

    public void loginInAccount() {
        mobileInput.sendKeys("13679183191");
        passwordInput.sendKeys("111111");
        click(loginFormButton);
    }


}
