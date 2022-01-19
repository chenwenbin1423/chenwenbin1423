package com.touzitop.automation.mail;

import java.io.*;

public class ReadHtml {

    public static String ReadString(String filePath) {

        File file = new File(filePath);
        // get HTML file stream
        StringBuffer htmlSb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "utf-8"));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();
            //删除临时文件
            //file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        //HTML文件字符串
        String htmlStr = htmlSb.toString();
        //返回经过清洁的html文本
        return htmlStr;
    }
}
