package com.example.nielseniq.tests;

import com.example.nielseniq.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTests extends BaseTest {

    @Test(description = "This test validates Landing Page", priority = 1)
    @Description("Test to validate Home page UI")
    public void verifyHomePage() {

        pages().getHomePage().verifyHomePage();
        pages().getHomePage().clickInsights();
        pages().getHomePage().clickSolutions();
        pages().getHomePage().clickIndustries();
        pages().getHomePage().clickAbout();
        pages().getHomePage().clickCareers();
        pages().getHomePage().clickPrivacyPolicy();
        pages().getHomePage().clickTermsAndConditions();

        //Search operation
        pages().getHomePage().clickSearch();
        pages().getSearchPage().search("SMB");
        pages().getSearchPage().clickSearchResultCategory("insight");
        pages().getSearchPage().filterByDate("Past 3 Months");
        List<String> result = pages().getSearchPage().verifySearchResult();
        for (String s : result
             ) {
            System.out.println(s);
        }

    }
}
