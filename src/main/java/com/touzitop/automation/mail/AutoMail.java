package com.touzitop.automation.mail;

import com.touzitop.automation.utils.ReadProperties;

import javax.mail.internet.AddressException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class AutoMail {
    public static void main(String[] args) throws AddressException, FileNotFoundException,IOException {
        MailInfo mailInfo = new MailInfo();
        String sendTo[] = readMail("sendto");
        String ccList[] = readMail("cc");

        mailInfo.setMailServerHost(ReadProperties.getprop("mail","MailServerHost"));
        mailInfo.setMailServerPort(ReadProperties.getprop("mail","MailServerPort"));
        mailInfo.setValidate(true);
        mailInfo.setUsername(ReadProperties.getprop("mail","Username"));
        mailInfo.setPassword(ReadProperties.getprop("mail","Password"));//邮箱密码
        mailInfo.setFromAddress(ReadProperties.getprop("mail","FromAddress"));
        mailInfo.setToAddress(sendTo);
        mailInfo.setSubject(ReadProperties.getprop("mail", "Subject"));
        mailInfo.setCcAddress(ccList);

        String mailContent = ReadHtml.ReadString("./target/surefire-reports/html/overview.html");
        String cssValue = ReadHtml.ReadString("./target/surefire-reports/html/reportng.css");
        String changeStr = "<style type=\"text/css\">h1 {display : inline}" + cssValue + "</style>";
        mailContent = mailContent.replace("<link href=\"reportng.css\" rel=\"stylesheet\" type=\"text/css\" />", changeStr);
        String logoStr="<h1 style=\"color:red; font-size:50px;font-family:'楷体','楷体_GB2312';\">UI-Auto</h1><h1>自动化测试报告</h1>";
        mailContent = mailContent.replace("<h1>Test Results Report</h1>", logoStr);
        mailInfo.setContent(mailContent);
        SendMail.sendHtmlMail(mailInfo);//发送html格式邮件
    }

    public static String[] readMail(String mailType) throws FileNotFoundException, IOException {
        int i = 0;
        Properties props = new Properties();
        props.load(new FileInputStream("./target/test-classes/properties/" + mailType + ".properties"));
        String[] mailto = new String[props.size()];

        for (Enumeration enu = props.elements(); enu.hasMoreElements(); ) {

            String key = (String) enu.nextElement();
            mailto[i] = key;
            i++;
        }
        return mailto;
    }
}
