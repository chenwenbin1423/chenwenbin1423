package com.touzitop.automation.page;

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

public class GetTVLinks extends BasePage {

    private String url = "https://www.ygdy8.com/html/tv/hytv/20170205/53166.html";

    //@FindBy(xpath="//td/span/a[contains(text(),'三生三世十里桃花')]")
    //@FindBy(xpath="//font/a[contains(text(),'点击进入')]")
    @FindBy(xpath="(//td/span/a[contains(text(),'三生三世十里桃花')])")
    public List<WebElement> tvDownloadLinks;

    @FindBy(xpath = "//strong/font[contains(text(),'【下载地址】')]")
    public WebElement downloadTab;

    //创建构造函数，并且调用PageFactory.initElements()方法来初始化元素，即，将元素映射到我们定义好的变量
    public GetTVLinks() throws InterruptedException {

        //super.init();
        super.init(url);

        //super.getURL(url);
        Thread.sleep(2000);
        //if (waitElementVisible(By.xpath("//strong/font[contains(text(),'【下载地址】')]")).isDisplayed()){
            PageFactory.initElements(driver, this);
       // }

    }

    public void outputLinks() throws IOException {

        /*LocalDate time = LocalDate.now();
        int year = time.getYear();
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();*/

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH_mm_ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        //String filename = "test" + year + month + day + ".txt";
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
        /*Map<String,String> map = (Map<String, String>) this.getAllAttributes();

        map.forEach((k,v) ->
        System.out.println("key=" + k + ", value=" + v));
        map.forEach((k,v) -> {
            if (v.contains("thunder://")){
                System.out.println(v);
            }
        });*/
    }
}
