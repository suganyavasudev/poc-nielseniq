package com.example.nielseniq.pages;

import com.example.nielseniq.BasePage;
import com.example.nielseniq.Utils.CommonMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchPage extends BasePage {

    private final By search = By.cssSelector("li.nav-search-item a");
    private final By searchInput = By.cssSelector("#searchOverlayLabel+form input");
    private final By searchSubmit = By.cssSelector("#searchOverlayLabel+form button");
    private final By selectDate = By.cssSelector("button[data-id=\"select-date\"] div.filter-option-inner-inner");
    private final By dateDropdown = By.cssSelector("#bs-select-3");
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Method to search")
    public void search(String query) {
        driver.findElement(searchInput).sendKeys(query);
        driver.findElement(searchSubmit).click();
        CommonMethods.waitForPageLoad();
    }

    @Step("Method to verify search results by category {category}")
    public void clickSearchResultCategory(String category) {
        driver.findElement(By.xpath("//a[@data-post-type='"+category+"']")).click();
        CommonMethods.waitForPageLoad();
    }

    @Step("Method to filter search result by date range {dateRange}")
    public void filterByDate(String value) {
        baseWait.until(ExpectedConditions.elementToBeClickable(selectDate));
        driver.findElement(selectDate).click();
        baseWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dateDropdown)));
        List<WebElement> options = driver.findElement(dateDropdown).findElements(By.cssSelector("li span.text"));
        for (WebElement option : options)
        {
            if (option.getText().contains(value))
            {
                option.click();
                break;
            }
        }

        CommonMethods.waitForPageLoad();
    }

    @Step("Method to verify search result ")
    public List<String> verifySearchResult() {
        List<String> searchResults = new ArrayList<>();
        int totalDisplayed = Integer.parseInt(driver.findElement(By.tagName("h1")).getAttribute("data-posts-found"));
        List<WebElement> result = driver.findElements(By.cssSelector("article img"));
        assertEquals(totalDisplayed,result.size(),"Total no displayed is" + totalDisplayed + " not matching the actual results " + result.size());
        for (WebElement ele : result
             ) {
            searchResults.add(ele.getAttribute("alt"));
        }
        return searchResults;
    }



}
