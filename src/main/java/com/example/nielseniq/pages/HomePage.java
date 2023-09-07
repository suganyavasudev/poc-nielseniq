package com.example.nielseniq.pages;

import com.example.nielseniq.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.nielseniq.Utils.CommonMethods;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

import static org.testng.Assert.assertTrue;


public class HomePage extends BasePage {

    private final By headerLogo = By.cssSelector("#header-navbar a");

    private final By insightsMenu = By.xpath("//li/a[contains(@href,'insights')]"); //using href as it supports all locales
    private final By solutionsMenu = By.xpath("//li/a[contains(@href,'solutions')]");
    private final By industriesMenu = By.xpath("//li/a[contains(@href,'who-we-serve')]");
    private final By aboutMenu = By.xpath("//li/a[contains(@href,'about-us')]");
    private final By careersMenu = By.xpath("//li/a[contains(@href,'careers')]");
    private final By insightsLink = By.cssSelector("#sub-nav-insights h3 a");
    private final By solutionsLink = By.cssSelector("#sub-nav-solutions h3 a");
    private final By industriesLink = By.cssSelector("#sub-nav-industries h3 a");
    private final By aboutLink = By.cssSelector("#sub-nav-about h3 a");
    private final By careersLink = By.cssSelector("#sub-nav-careers h3 a");
    private final By search = By.cssSelector("li.nav-search-item a");
    private final By login = By.xpath("//a[contains(@href,'login')]");
    private final By locale = By.cssSelector("#dropdown-market-language");
    private final By privacyPolicyLink = By.xpath("//a[contains(@href,'privacy-policy')]");
    private final By termsAndConditionsLink = By.xpath("//a[contains(@href,'terms-of-use')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Method to verify landing/home page")
    public void verifyHomePage(){
        CommonMethods.waitForPageLoad();
        assertTrue(driver.findElement(headerLogo).isDisplayed());
    }

    @Step("Method to hover menu and click the link {page}")
    public void clickSubMenuLink(By menu, By subMenuLink, String page) {
        if(driver.getCurrentUrl().contains("en")) {
            CommonMethods.moveToElement(driver.findElement(menu));
            baseWait.until(ExpectedConditions.elementToBeClickable(subMenuLink));
        }
        navigateToPage(getActiveElement(driver.findElements(subMenuLink)), page);
    }
    @Step("Method to open Insights page")
    public void clickInsights() {
        clickSubMenuLink(insightsMenu, insightsLink, "Insights – NIQ");
    }
    @Step("Method to open Solutions page")
    public void clickSolutions() {
        clickSubMenuLink(solutionsMenu, solutionsLink, "Discover more of your business - NIQ");
    }

    @Step("Method to open Industries page")
    public void clickIndustries() {
        clickSubMenuLink(industriesMenu, industriesLink, "Who we serve - NIQ");
    }
    @Step("Method to open About page")
    public void clickAbout() {
        clickSubMenuLink(aboutMenu, aboutLink, "About us - The Full View - NIQ");
    }
    @Step("Method to open Careers page")
    public void clickCareers() {
        clickSubMenuLink(careersMenu, careersLink, "Careers - NIQ");
    }

    @Step("Method to open Privacy Policy page")
    public void clickPrivacyPolicy() {
        scrollElementIntoView(privacyPolicyLink);
        navigateToPage(getActiveElement(driver.findElements(privacyPolicyLink)),"Privacy Policy - NIQ");
    }

    @Step("Method to open Terms And Conditions page")
    public void clickTermsAndConditions() {
        scrollElementIntoView(termsAndConditionsLink);
        navigateToPage(getActiveElement(driver.findElements(termsAndConditionsLink)),"Terms of use - NIQ");
    }
    @Step("Method to open Login page")
    public void clickSearch() {
        navigateToPage(driver.findElement(search), driver.getTitle());
    }


}
