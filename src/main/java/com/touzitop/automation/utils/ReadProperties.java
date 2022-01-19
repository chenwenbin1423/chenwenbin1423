package com.touzitop.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    /**
     * 提取文件名称，属性名获取相应属性值
     * @param filename
     * @param propname
     * @return str
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getprop(String filename, String propname) throws FileNotFoundException, IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("./target/test-classes/properties/" + filename + ".properties"));
        String str = props.getProperty(propname);
        return str;
    }

    public static void main(String[] args) throws FileNotFoundException,IOException {
        System.out.println(getprop("test", "test"));
    }
}
