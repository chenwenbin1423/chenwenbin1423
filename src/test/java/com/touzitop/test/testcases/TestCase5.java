package com.touzitop.test.testcases;

import com.touzitop.automation.page.GetTVLinks;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase5 {

    @Test
    public void test_01_login() throws InterruptedException, IOException {
        /*LoginPage loginPage = new LoginPage();
        loginPage.login();
        loginPage.quit();*/


        GetTVLinks getTVLinks = new GetTVLinks();
        getTVLinks.outputLinks();
        getTVLinks.quit();

    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test...");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test...");
    }


}
