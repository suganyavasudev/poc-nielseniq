package com.example.nielseniq;

import com.example.nielseniq.pages.NQPages;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    private static final ThreadLocal<NQPages> PAGES = new ThreadLocal<>();

    public NQPages pages() {
        return PAGES.get();
    }
    @BeforeMethod
    @Step("Start the application")
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://nielseniq.com/global/en/");
        PAGES.set(new NQPages(driver));
    }

    @Step("Stop the application")
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
