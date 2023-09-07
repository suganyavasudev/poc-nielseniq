package com.example.nielseniq.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class NQPages {

    protected HomePage homePage;
    protected SearchPage searchPage;


    public NQPages(WebDriver driver) {
        this.homePage = new HomePage(driver);
        this.searchPage = new SearchPage(driver);
    }


}
