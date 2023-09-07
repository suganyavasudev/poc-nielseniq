package com.example.nielseniq.Utils;

import com.example.nielseniq.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class CommonMethods extends BasePage {

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    @Step("Method to move to/hover over specific element")
    public static void moveToElement(WebElement hoverEle) {
        Actions action = new Actions(driver);
        action.moveToElement(hoverEle).perform();
    }
    @Step("Method to move to/hover over specific element")
    public static void hoverAndClick(WebElement clickEle) {
        Actions action = new Actions(driver);
        action.moveToElement(clickEle).perform();
        action.click().build().perform();
    }

    @Step("Method to wait for page load")
    public static void waitForPageLoad(){
        baseWait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }


}
