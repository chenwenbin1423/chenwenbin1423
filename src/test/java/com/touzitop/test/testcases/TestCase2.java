package com.touzitop.test.testcases;

import com.touzitop.automation.page.HomePage;
import com.touzitop.automation.page.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase2 {

    @Test
    @Step("输入内容")
    @Description("测试使用密码登录功能是否正确")
    public void test_01_testUserLoginByAccount() throws InterruptedException {

        HomePage homePage = new HomePage();
        Thread.sleep(2000);
        homePage.gotoLoginPage();
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage();
        loginPage.switchToAccountLogin();
        loginPage.loginInAccount();

        loginPage.quit();

    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before home page and login page Test...");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After home page and login page Test...");
    }


}
