package com.touzitop.automation.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;*/

public class HomePage extends BasePage {

    private String url = "https://www.touzitop.com";

    @FindBy(xpath = "//div[@class='before_login']//a[text()='首页']")
    public WebElement homepageBtn;

    @FindBy(css = "a[href='/login']")
    public WebElement openLoginBtn;

    @FindBy(xpath = "//a[text()='注册']")
    public WebElement openRegisterBtn;

    @FindBy(xpath = "//a[text()='影视项目']")
    public WebElement YSXMBtn;

    @FindBy(xpath = "//a[normalize-space(text()) = '新手培训']")
    public WebElement XSPXBtn;

    @FindBy(xpath = "//ul[@style='display: none'][.//a[text()='项目视频']]//a[text()='新手必学']")
    public WebElement XSBXBtn;

    @FindBy(xpath = "//a[text()='项目视频']")
    public WebElement XMSPBtn;

    @FindBy(xpath = "//a[text()='行业视频']")
    public WebElement HYSPBtn;

    @FindBy(xpath = "//a[text()='政策法规']")
    public WebElement ZCFGBtn;

    @FindBy(css = "a[href='/cooperation']")
    public WebElement cooperationBtn;

    @FindBy(xpath = "//a[text()='项目融资']")
    public WebElement XMRZBtn;

    @FindBy(xpath = "//a[text()='加盟代理']")
    public WebElement JMDLBtn;

    @FindBy(xpath = "//a[text()='机构入驻']")
    public WebElement JGRZBtn;

    @FindBy(xpath = "//a[normalize-space(text()) = '行业资讯']")
    public WebElement HYZXBtn;

    @FindBy(xpath = "//a[text()='影视资讯']")
    public WebElement YSTZBtn;

    @FindBy(xpath = "//a[text()='金融资讯']")
    public WebElement JRZXBtn;

    @FindBy(xpath = "//a[text()='企业新闻']")
    public WebElement QYXWBtn;

    @FindBy(xpath = "//a[text()='政策要闻']")
    public WebElement ZCYWBtn;

    @FindBy(id = "in-txt")
    public WebElement keywordInput;

    @FindBy(id = "soso")
    public WebElement sosoLink;

    @FindBy(css = ".before_login a[href='/about']")
    public WebElement aboutUsBtn;

    //创建构造函数，并且调用PageFactory.initElements()方法来初始化元素，即，将元素映射到我们定义好的变量
    public HomePage() throws InterruptedException {

        super.init(url);
        Thread.sleep(2000);
        PageFactory.initElements(driver, this);
        Thread.sleep(2000);
    }

    public void gotoLoginPage() throws InterruptedException {
        click(openLoginBtn);
        Thread.sleep(1000);
    }

    public void gotoRegisterPage() throws InterruptedException {
        click(openRegisterBtn);
        Thread.sleep(1000);
    }







    /*public void outputLinks() throws IOException {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH_mm_ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        String filename = "result" + formatter.format(date) + ".txt";
        File file = new File("./target/" + filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // if file doesn't exist, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (WebElement e: tvDownloadLinks) {
            Map<String, String> map = (Map<String, String>) this.getAllAttributes(e);
            //map.forEach((k,v) -> System.out.println("key=" + k + ", value=" + v));
            map.forEach((k,v) -> {
                if (v.contains("thunder://")){
                    System.out.println(v);
                    stringBuilder.append(v);
                    stringBuilder.append('\n');
                }
            });
            System.out.println(stringBuilder.toString());
        }
        try {
            fileOutputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }*/
}
