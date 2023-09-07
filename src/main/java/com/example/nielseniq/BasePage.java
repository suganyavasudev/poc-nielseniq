package com.example.nielseniq;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected static WebDriver driver;
    protected static FluentWait<WebDriver> baseWait;
    private final static int DEFAULT_TIMEOUT = 30;
    private final static int DEFAULT_POLLING = 5;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        baseWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(DEFAULT_POLLING))
                .ignoring(NoSuchElementException.class);
    }

    @Step("Method to wait for page load")
    public void waitForPageLoad(String page){
        baseWait.withTimeout(Duration.ofSeconds(10));
        baseWait.until(ExpectedConditions.titleIs(page));
        baseWait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    @Step("Method to navigate to other pages {page}")
    public void navigateToPage(WebElement link, String page){
        scrollElementIntoViewAndClick(link);
        waitForPageLoad(page);
    }

    @Step("Method to scroll to particular element and click on it based on web element")
    public void scrollElementIntoViewAndClick(final WebElement element) {
        scrollElementIntoView(element);
        element.click();
    }

    @Step("Method to scroll to particular element")
    public void scrollElementIntoView(final WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Method to scroll to particular element")
    public void scrollElementIntoView(final By loc) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(loc) );
    }

    @Step("Method to get active element from the list of similar elements")
    public WebElement getActiveElement(final List<WebElement> elements) {
        WebElement activeElement = null;
        for (WebElement element : elements) {
            if (element.isEnabled() && element.isDisplayed()) {
                activeElement = element;
                break;
            }
        }
        return activeElement;
    }
}
